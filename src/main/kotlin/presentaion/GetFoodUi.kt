package org.example.presentaion

import org.example.domain.usecase.GetFoodUC
import org.example.domain.usecase.IngredientGuessUseCase

class GetFoodUi(private val useCase: IngredientGuessUseCase) {
    operator fun invoke() {
        while (useCase.hasNextRound()) {
            val round = useCase.nextRound() ?: break
            println("Meal: ${round.mealName}")
            println("Choose the correct ingredient:")
            round.options.forEachIndexed { i, opt -> println("${i+1}. $opt") }
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