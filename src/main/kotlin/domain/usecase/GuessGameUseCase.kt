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
            ?: throw MealException.GuessException.NoMealAvailable("No food Available.")
        return randomMeal
    }

    fun makeGuess(guessedTime: Int): Result<Meal> {
        val food = randomMeal
        return when {
            guessedTime == food.minutesForPreparation -> {
                attemptsLeft = 3
                Result.success(food)
            }

            attemptsLeft <= 1 -> {
                attemptsLeft = 3
                Result.failure(MealException.GuessException.GameOver(food.minutesForPreparation))
            }

            guessedTime < food.minutesForPreparation -> {
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