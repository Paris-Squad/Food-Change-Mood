package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food
import kotlin.random.Random

class GuessGameUseCase(private val repository: FoodRepository) {

    private var attemptsLeft = 3
    private lateinit var randomFood: Food

    fun getRandomFood(): Food {
        randomFood = repository.getFood().takeIf { it.isNotEmpty() }?.let { it[Random.nextInt(it.size)] }
            ?: throw FoodException.GuessException.NoFoodAvailable("No food Available.")
        return randomFood
    }

    fun makeGuess(guessedTime: Int): Result<Food> {
        val food = randomFood
        return when {
            guessedTime == food.minutes -> {
                attemptsLeft = 3
                Result.success(food)
            }

            attemptsLeft <= 1 -> {
                attemptsLeft = 3
                Result.failure(FoodException.GuessException.GameOver(food.minutes))
            }

            guessedTime < food.minutes -> {
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