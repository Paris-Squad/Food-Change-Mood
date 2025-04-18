package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.FoodException
import org.example.domain.repository.MealRepository
import kotlin.random.Random

class GuessGameUseCase(private val repository: MealRepository) {

    private var attemptsLeft = 3
    private lateinit var randomFood: Meal

    fun getRandomFood(): Meal {
        randomFood = repository.getMeals().takeIf { it.isNotEmpty() }?.let { it[Random.nextInt(it.size)] }
            ?: throw FoodException.GuessException.NoFoodAvailable("No food Available.")
        return randomFood
    }

    fun makeGuess(guessedTime: Int): Result<Meal> {
        val food = randomFood
        return when {
            guessedTime == food.minutesForPreparation -> {
                attemptsLeft = 3
                Result.success(food)
            }

            attemptsLeft <= 1 -> {
                attemptsLeft = 3
                Result.failure(FoodException.GuessException.GameOver(food.minutesForPreparation))
            }

            guessedTime < food.minutesForPreparation -> {
                attemptsLeft--
                Result.failure(FoodException.GuessException.TooLow(attemptsLeft))
            }

            else -> {
                attemptsLeft--
                Result.failure(FoodException.GuessException.TooHigh(attemptsLeft))
            }
        }
    }
}