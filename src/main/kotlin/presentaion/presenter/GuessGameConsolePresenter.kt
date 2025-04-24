package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetRandomMealUseCase
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer

class GuessGameConsolePresenter(
    private val getRandomMeal: GetRandomMealUseCase, printer: Printer, private val reader: InputReader
) : BasePresenter(printer) {

    private var meal: Meal? = null
    private var attempts = 3
    private var gameActive = false

    fun startGame() {
        printer.displayLn("--- MEAL PREPARATION TIME GUESSING GAME ---")
        val randomMealResult = getRandomMeal.invoke()

        randomMealResult.fold(
            onSuccess = { randomMeal ->
                meal = randomMeal
                gameActive = true
                attempts = 3
                printer.displayLn("Guess the preparation time (in minutes) for: ${randomMeal.mealName ?: "Unnamed recipe"}")
                printer.displayLn("Your guess (attempts left: $attempts): ")
            }, onFailure = ::handleException
        )
    }

    fun isGameActive(): Boolean = gameActive

    fun processGuess(input: String): Boolean {
        val guessedTime = reader.readInt()

        if (guessedTime == null) {
            printer.displayLn("Please enter a valid number.")
            printer.displayLn("Your guess (attempts left: $attempts): ")
            return true
        }

        val actualTime = meal?.minutesForPreparation
        if (actualTime == null) {
            printer.displayLn("An error occurred: Meal preparation time is missing")
            gameActive = false
            return false
        }

        when {
            guessedTime == actualTime -> {
                printer.displayLn("Correct! ${meal?.mealName} takes $actualTime minutes to prepare.")
                gameActive = false
            }

            guessedTime < actualTime -> {
                attempts--
                if (attempts > 0) {
                    printer.displayLn("Too low! Try again. ($attempts attempts left)")
                    printer.displayLn("Your guess (attempts left: $attempts): ")
                } else {
                    printer.displayLn("Game over! The correct answer was $actualTime minutes.")
                    gameActive = false
                }
            }

            else -> {
                attempts--
                if (attempts > 0) {
                    printer.displayLn("Too high! Try again. ($attempts attempts left)")
                    printer.displayLn("Your guess (attempts left: $attempts): ")
                } else {
                    printer.displayLn("Game over! The correct answer was $actualTime minutes.")
                    gameActive = false
                }
            }
        }

        return gameActive
    }

}
