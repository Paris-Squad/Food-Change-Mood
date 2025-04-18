package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GuessPreparationTimeGameUseCase

class GuessGameConsoleUi(private val useCase: GuessPreparationTimeGameUseCase) {

    fun startGame() {
        println("--- MEAL PREPARATION TIME GUESSING GAME ---")
        val randomFood = useCase.getRandomMeal()
        println("Guess the preparation time (in minutes) for: ${randomFood.mealName ?: "Unnamed recipe"}")

        var gameRunning = true
        var attempts = 3

        while (gameRunning) {
            print("Your guess (attempts left: $attempts): ")

            val input = readln()
            val guessedTime = input.toIntOrNull()

            if (guessedTime == null) {
                println("Please enter a valid number.")
                continue
            }

            useCase.makeGuess(guessedTime).fold(
                onSuccess = { meal ->
                    println("Correct! ${meal.mealName} takes ${meal.minutesForPreparation} minutes to prepare.")
                    gameRunning = false
                },
                onFailure = { error ->
                    when (error) {
                        is MealException.GuessTooLowException -> {
                            attempts = error.attemptsLeft
                            println("Too low! Try again. ($attempts attempts left)")
                        }

                        is MealException.GuessTooHighException -> {
                            attempts = error.attemptsLeft
                            println("Too high! Try again. ($attempts attempts left)")
                        }

                        is MealException.GameOver -> {
                            println("Game over! The correct answer was ${error.correctTime} minutes.")
                            gameRunning = false
                        }

                        else -> {
                            println("An error occurred: ${error.message}")
                            gameRunning = false
                        }
                    }
                }
            )
        }
    }

}
