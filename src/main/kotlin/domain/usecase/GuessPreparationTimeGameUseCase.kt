package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import domain.model.Meal
import kotlin.random.Random

class GuessPreparationTimeGameUseCase(private val mealRepository: MealRepository) {

    private var attemptsLeft = 3
    private lateinit var randomMeal: Meal

    fun getRandomMeal(): Meal {
        randomMeal = mealRepository.getMeals().takeIf { it.isNotEmpty() }?.let { it[Random.nextInt(it.size)] }
            ?: throw MealException.NoMealsFoundException("No Meals Available.")
        return randomMeal
    }

    fun makeGuess(guessedTime: Int): Result<Meal> {
        if (!::randomMeal.isInitialized) throw MealException.NoMealsFoundException("No Meals Available.")
        val meal = randomMeal
        return when {
            guessedTime == meal.minutesForPreparation -> {
                attemptsLeft = 3
                Result.success(meal)
            }

            attemptsLeft <= 1 -> {
                attemptsLeft = 3
                Result.failure(MealException.GameOver(meal.minutesForPreparation))
            }

            guessedTime < meal.minutesForPreparation -> {
                attemptsLeft--
                Result.failure(MealException.GuessTooLowException(attemptsLeft))
            }

            else -> {
                attemptsLeft--
                Result.failure(MealException.GuessTooHighException(attemptsLeft))
            }
        }
    }
}