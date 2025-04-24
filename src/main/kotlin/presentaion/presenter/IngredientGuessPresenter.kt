package org.example.presentaion.presenter

import org.example.domain.usecase.GetIngredientGuessUseCase
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer

class IngredientGuessPresenter(
    private val ingredientGuess: GetIngredientGuessUseCase, printer: Printer, private val reader: InputReader
) : BasePresenter(printer) {
    fun startIngredientGuess() {
        while (ingredientGuess.hasNextRound()) {
            val round = ingredientGuess.nextRound() ?: break
            printer.displayLn("Meal: ${round.mealName}")
            printer.displayLn("Choose the correct ingredient:")
            round.options.forEachIndexed { index, opt -> printer.displayLn("${index + 1}. $opt") }
            printer.displayLn("Enter the choose:")
            val guess = reader.readInt()
            val chosen = round.options.getOrNull(guess?.minus(1) ?: -1)

            if (chosen != null && ingredientGuess.submitGuess(chosen)) {
                printer.displayLn("Correct! Score: ${ingredientGuess.getScore()}")
            } else {
                printer.displayLn("Incorrect! The correct answer was: ${round.correctAnswer}")
                break
            }
        }

        printer.displayLn("Game Over! Final Score: ${ingredientGuess.getScore()}")
    }
}