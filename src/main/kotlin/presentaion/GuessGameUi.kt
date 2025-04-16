package org.example.presentaion

import org.example.domain.FoodException
import org.example.domain.usecase.GuessGameUC

class GuessGameUi(private val useCase: GuessGameUC) {
    
    fun startGame() {
        println("--- MEAL PREPARATION TIME GUESSING GAME ---")

        val randomFood = useCase.getRandomFood()
        
        println("Guess the preparation time (in minutes) for: ${randomFood.name ?: "Unnamed recipe"}")
        
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
                    println("Correct! ${food.name} takes ${food.minutes} minutes to prepare.")
                    gameRunning = false
                },
                onFailure = { error ->
                    when (error) {
                        is FoodException.GuessException.TooLow -> {
                            attempts = error.attemptsLeft
                            println("Too low! Try again. ($attempts attempts left)")
                        }
                        
                        is FoodException.GuessException.TooHigh -> {
                            attempts = error.attemptsLeft
                            println("Too high! Try again. ($attempts attempts left)")
                        }
                        
                        is FoodException.GuessException.GameOver -> {
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
