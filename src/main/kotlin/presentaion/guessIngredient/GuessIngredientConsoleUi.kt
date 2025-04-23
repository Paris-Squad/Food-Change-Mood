package org.example.presentaion.guessIngredient

import org.example.domain.model.IngredientGameRound

class GuessIngredientConsoleUi(
    private val ingredientGuessPresenter:IngredientGuessPresenter,
    private val listener: IngredientGuessInteractionListener
) {
    private var score = 0
    private var correctAnswers = 0
    private val usedMeals = mutableSetOf<String>()
    private var shouldBreak = false


    fun ingredientGuessConsoleUi() {

        while (correctAnswers < MAX_ROUNDS && !shouldBreak) {
            val resultMeal=ingredientGuessPresenter.getRandomMeal(usedMeals)
            resultMeal.fold(
                onSuccess = {
                    usedMeals.add(it.mealName!!)
                    val round = ingredientGuessPresenter.nextRound(it,NUMBER_OF_RANDOM_WRONG_INGREDIENTS)
                    println("Meal: ${round.mealName}")
                    println("Choose the correct ingredient:")
                    round.options.forEachIndexed { index, opt -> println("${index+1}. $opt") }
                    println("Enter the choose:")
                    val guess=listener.read()
                    val chosen = round.options.getOrNull(guess.minus(1))
                    if (chosen != null && submitGuess(chosen,round)) {
                        println("Correct! Score: $score")
                    } else {
                        println("Incorrect! The correct answer was: ${round.correctAnswer}")
                        shouldBreak = true
                    }
                },
                onFailure = {
                    println("Failed to get a meal: ${it.message}")
                    shouldBreak = true
                }
            )
        }
        println("Game Over! Final Score: $score}")
        resetGame()
    }



    private  fun submitGuess(guess: String,currentRound:IngredientGameRound): Boolean {
        return if (guess == currentRound.correctAnswer) {
            score += CORRECT_ANSWER_SCORE
            correctAnswers++
            true
        } else false
    }

    fun resetGame() {
        score = 0
        correctAnswers = 0
        usedMeals.clear()
        shouldBreak = false
    }
    companion object{
        const val MAX_ROUNDS = 15
        const val CORRECT_ANSWER_SCORE = 1000
        const val NUMBER_OF_RANDOM_WRONG_INGREDIENTS = 2
    }


}