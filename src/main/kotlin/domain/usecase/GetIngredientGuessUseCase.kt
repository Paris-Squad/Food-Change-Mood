package org.example.domain.usecase

import org.example.domain.model.IngredientGameRound
import org.example.domain.repository.MealRepository
import domain.model.Meal

class GetIngredientGuessUseCase  (private val repository : MealRepository) {
    private fun getAllFood(): List<Meal> = repository.getMeals()

    private var score = 0
    private var correctAnswers = 0
    private val usedMeals = mutableSetOf<String>()
    private var currentRound: IngredientGameRound? = null

    fun nextRound(): IngredientGameRound?  {
            val meal = getRandomMeal() ?:return null
            usedMeals.add(meal.mealName!!)
            val correctIngredient = meal.ingredients.random()
            val wrongIngredients = getTwoRandomWrongIngredients(meal) ?: return null
            val options = (listOf(correctIngredient) + wrongIngredients).shuffled()
            currentRound =IngredientGameRound(meal.mealName, options, correctIngredient)
            return currentRound
    }


    private fun getRandomMeal(): Meal? {
        val available = getAllFood().filterNot { it.mealName==null || usedMeals.contains(it.mealName) }
        return if (available.isNotEmpty()) available.random() else null
    }

    private fun getTwoRandomWrongIngredients(correctMeal: Meal): List<String>? {
        val allIngredients =  getAllFood().flatMap { it.ingredients }.distinct().filterNot { it in correctMeal.ingredients }
        return if(allIngredients.isNotEmpty())  allIngredients.shuffled().take(2) else null


    }
    fun hasNextRound(): Boolean = correctAnswers < 15
    fun submitGuess(guess: String): Boolean {
        val round = currentRound ?: return false
        return if (guess == round.correctAnswer) {
            score += 1000
            correctAnswers++
            true
        } else false
    }
    fun getScore(): Int = score


}

