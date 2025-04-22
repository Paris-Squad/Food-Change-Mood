package org.example.presentaion.presenter

import org.example.domain.usecase.GetRandomMealUseCase
import domain.model.Meal

class GuessGameConsolePresenter(private val getRandomMealUseCase: GetRandomMealUseCase) {

    private var meal: Meal? = null
    private var attempts = 3
    private var gameActive = false

    fun startGame() {
        println("--- MEAL PREPARATION TIME GUESSING GAME ---")
        val randomMealResult = getRandomMealUseCase.invoke()

        randomMealResult.fold(
            onSuccess = { randomMeal ->
                meal = randomMeal
                gameActive = true
                attempts = 3
                println("Guess the preparation time (in minutes) for: ${randomMeal.mealName ?: "Unnamed recipe"}")
                println("Your guess (attempts left: $attempts): ")
            },
            onFailure = { error ->
                println("Failed to start game: ${error.message}")
            }
        )
    }

    fun isGameActive(): Boolean = gameActive

    fun processGuess(input: String): Boolean {
        val guessedTime = input.toIntOrNull()

        if (guessedTime == null) {
            println("Please enter a valid number.")
            println("Your guess (attempts left: $attempts): ")
            return true
        }

        val actualTime = meal?.minutesForPreparation
        if (actualTime == null) {
            println("An error occurred: Meal preparation time is missing")
            gameActive = false
            return false
        }

        when {
            guessedTime == actualTime -> {
                println("Correct! ${meal?.mealName} takes $actualTime minutes to prepare.")
                gameActive = false
            }
            guessedTime < actualTime -> {
                attempts--
                if (attempts > 0) {
                    println("Too low! Try again. ($attempts attempts left)")
                    println("Your guess (attempts left: $attempts): ")
                } else {
                    println("Game over! The correct answer was $actualTime minutes.")
                    gameActive = false
                }
            }
            else -> {
                attempts--
                if (attempts > 0) {
                    println("Too high! Try again. ($attempts attempts left)")
                    println("Your guess (attempts left: $attempts): ")
                } else {
                    println("Game over! The correct answer was $actualTime minutes.")
                    gameActive = false
                }
            }
        }

        return gameActive
    }

}
