package org.example.presentaion.presenter

import org.example.domain.usecase.GetIngredientGuessUseCase

class GetIngredientGuessPresenter(private val getIngredientGuess: GetIngredientGuessUseCase) {
     fun startIngredientGuess() {
        while (getIngredientGuess.hasNextRound()) {
            val round = getIngredientGuess.nextRound() ?: break
            println("Meal: ${round.mealName}")
            println("Choose the correct ingredient:")
            round.options.forEachIndexed { index, opt -> println("${index+1}. $opt") }
            println("Enter the choose:")
            val guess = readLine()?.toIntOrNull()
            val chosen = round.options.getOrNull(guess?.minus(1) ?: -1)

            if (chosen != null && getIngredientGuess.submitGuess(chosen)) {
                println("Correct! Score: ${getIngredientGuess.getScore()}")
            } else {
                println("Incorrect! The correct answer was: ${round.correctAnswer}")
                break
            }
        }

        println("Game Over! Final Score: ${getIngredientGuess.getScore()}")
    }
}