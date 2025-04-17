package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GuessGameUseCase

class GuessGameConsoleUi(private val useCase: GuessGameUseCase) {
    
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
            
            val result = useCase.makeGuess(guessedTime)
            
            result.fold(
                onSuccess = { food ->
                    println("Correct! ${food.mealName} takes ${food.minutesForPreparation} minutes to prepare.")
                    gameRunning = false
                },
                onFailure = { error ->
                    when (error) {
                        is MealException.GuessException.TooLow -> {
                            attempts = error.attemptsLeft
                            println("Too low! Try again. ($attempts attempts left)")
                        }
                        
                        is MealException.GuessException.TooHigh -> {
                            attempts = error.attemptsLeft
                            println("Too high! Try again. ($attempts attempts left)")
                        }
                        
                        is MealException.GuessException.GameOver -> {
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
