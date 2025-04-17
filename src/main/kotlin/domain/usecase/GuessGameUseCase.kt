package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.model.Meal
import kotlin.random.Random

class GuessGameUseCase(private val repository: MealRepository) {

    private var attemptsLeft = 3
    private lateinit var randomMeal: Meal

    fun getRandomMeal(): Meal {
        randomMeal = repository.getMeals().takeIf { it.isNotEmpty() }?.let { it[Random.nextInt(it.size)] }
            ?: throw MealException.GuessException.NoMealAvailable("No meals Available.")
        return randomMeal
    }

    fun makeGuess(guessedTime: Int): Result<Meal> {
        val meal = randomMeal
        return when {
            guessedTime == meal.minutesForPreparation -> {
                attemptsLeft = 3
                Result.success(meal)
            }

            attemptsLeft <= 1 -> {
                attemptsLeft = 3
                Result.failure(MealException.GuessException.GameOver(meal.minutesForPreparation))
            }

            guessedTime < meal.minutesForPreparation -> {
                attemptsLeft--
                Result.failure(MealException.GuessException.TooLow(attemptsLeft))
            }

            else -> {
                attemptsLeft--
                Result.failure(MealException.GuessException.TooHigh(attemptsLeft))
            }
        }
    }
}