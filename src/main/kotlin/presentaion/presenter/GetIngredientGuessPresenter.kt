package org.example.presentaion.presenter

import org.example.domain.usecase.GetIngredientGuessUseCase

class GetIngredientGuessPresenter(private val useCase: GetIngredientGuessUseCase) {
     fun startIngredientGuess() {
        while (useCase.hasNextRound()) {
            val round = useCase.nextRound() ?: break
            println("Meal: ${round.mealName}")
            println("Choose the correct ingredient:")
            round.options.forEachIndexed { index, opt -> println("${index+1}. $opt") }
            println("Enter the choose:")
            val guess = readLine()?.toIntOrNull()
            val chosen = round.options.getOrNull(guess?.minus(1) ?: -1)

            if (chosen != null && useCase.submitGuess(chosen)) {
                println("Correct! Score: ${useCase.getScore()}")
            } else {
                println("Incorrect! The correct answer was: ${round.correctAnswer}")
                break
            }
        }

        println("Game Over! Final Score: ${useCase.getScore()}")
    }
}