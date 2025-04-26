package org.example.presentaion.presenter.guessPreparationTime

import domain.model.Meal
import org.example.domain.usecase.GetRandomMealUseCase
import org.example.domain.usecase.GuessFoodPreparationTimeUseCase
import org.example.presentaion.model.GuessPreparationTimeState

class GuessPreparationTimeGamePresenter(
    private val randomMealUseCase: GetRandomMealUseCase,
    private val guessFoodPreparationTimeUseCase: GuessFoodPreparationTimeUseCase,
    private val view: GuessPreparationTimeGameView
) {
    private var meal: Meal? = null
    private var attempts = 0
    private var gameActive = false

    fun startGame() {
        view.displayGameTitle()
        val randomMealResult = randomMealUseCase.invoke()
        randomMealResult.fold(onSuccess = ::onGetRandomMealSuccess, onFailure = ::onGetRandomMealFailure)
    }



    private fun onGetRandomMealSuccess(randomMeal: Meal) {
        meal = randomMeal
        gameActive = true
        attempts = 0
        view.displayMealToGuess(randomMeal.mealName ?: "Unnamed recipe")
        view.promptForGuess(getRemainingAttempts())
    }
    private fun onGetRandomMealFailure(error:Throwable) {
        view.displayError(error.message)
        gameActive = false
    }
    fun isGameActive(): Boolean = gameActive

    private fun getRemainingAttempts() = 3 - attempts

    fun processGuess() {
        val guessedTime = view.readGuessTime()

        if (guessedTime == null) {
            view.displayError("Please enter a valid number.")
            view.promptForGuess(getRemainingAttempts())
            return
        }

        val actualTime = meal?.minutesForPreparation

        if (actualTime == null) {
            view.displayError("An error occurred: Meal preparation time is missing")
            gameActive = false
            return
        }

        val result = guessFoodPreparationTimeUseCase.invoke(guessedTime, actualTime, attempts)
        attempts++

        when (result) {
            GuessPreparationTimeState.CORRECT -> {
                view.displayCorrectGuess(meal?.mealName, actualTime)
                gameActive = false
            }
            GuessPreparationTimeState.TOO_LOW -> {
                if (getRemainingAttempts() > 0) {
                    view.displayTooLowGuess(getRemainingAttempts())
                    view.promptForGuess(getRemainingAttempts())
                } else {
                    view.displayGameOver(actualTime)
                    gameActive = false
                }
            }
            GuessPreparationTimeState.TOO_HIGH -> {
                if (getRemainingAttempts() > 0) {
                    view.displayTooHighGuess(getRemainingAttempts())
                    view.promptForGuess(getRemainingAttempts())
                } else {
                    view.displayGameOver(actualTime)
                    gameActive = false
                }
            }
            GuessPreparationTimeState.FINISHED -> {
                view.displayGameOver(actualTime)
                gameActive = false
            }
        }
    }
}