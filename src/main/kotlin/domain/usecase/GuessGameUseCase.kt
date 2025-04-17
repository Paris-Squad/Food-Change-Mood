package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.FoodRepository
import org.example.model.Meal
import kotlin.random.Random

class GuessGameUseCase(private val repository: FoodRepository) {

    private var attemptsLeft = 3
    private lateinit var randomFood: Meal

    fun getRandomFood(): Meal {
        randomFood = repository.getFood().takeIf { it.isNotEmpty() }?.let { it[Random.nextInt(it.size)] }
            ?: throw MealException.GuessException.NoMealAvailable("No food Available.")
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