package org.example.presentaion.presenter.guessPreparationTime

import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.koin.java.KoinJavaComponent.inject

class GuessPreparationTimeGameGameUI(
    private val printer: Printer,
    private val reader: InputReader,
) : GuessPreparationTimeGameView {

    private val presenter: GuessPreparationTimeGamePresenter by inject(GuessPreparationTimeGamePresenter::class.java)

    fun launchGame() {
        presenter.startGame()
        processUserInput()
    }

    private fun processUserInput() {
        while (presenter.isGameActive()) {
            presenter.processGuess()
        }
    }

    override fun displayGameTitle() {
        printer.displayLn("--- MEAL PREPARATION TIME GUESSING GAME ---")
    }

    override fun displayMealToGuess(mealName: String) {
        printer.displayLn("Guess the preparation time (in minutes) for: $mealName")
    }

    override fun promptForGuess(attemptsLeft: Int) {
        printer.displayLn("Your guess (attempts left: $attemptsLeft): ")
    }

    override fun displayCorrectGuess(mealName: String?, actualTime: Int) {
        printer.displayLn("Correct! $mealName takes $actualTime minutes to prepare.")
    }

    override fun displayTooLowGuess(attemptsLeft: Int) {
        printer.displayLn("Too low! Try again. ($attemptsLeft attempts left)")
    }

    override fun displayTooHighGuess(attemptsLeft: Int) {
        printer.displayLn("Too high! Try again. ($attemptsLeft attempts left)")
    }

    override fun displayGameOver(actualTime: Int) {
        printer.displayLn("Game over! The correct answer was $actualTime minutes.")
    }

    override fun displayError(message: String?) {
        printer.displayLn(message ?: "An unknown error occurred")
    }

    override fun readGuessTime(): Int? {
        return reader.readInt()
    }
}