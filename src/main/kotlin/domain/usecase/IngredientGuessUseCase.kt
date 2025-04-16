package org.example.domain.usecase

import org.example.domain.repository.FoodRepository
import org.example.model.Food
import org.example.model.IngredientGameRound

class IngredientGuessUseCase  (private val repository : FoodRepository) {
    private fun getAllFood(): List<Food> = repository.getFood()

    private var score = 0
    private var correctAnswers = 0
    private val usedMeals = mutableSetOf<String>()
    private lateinit var currentRound: IngredientGameRound

    fun nextRound(): IngredientGameRound?  {
            val meal = getRandomMeal() ?:return null
            usedMeals.add(meal.name!!)
            val correctIngredient = meal.ingredients.random()
            val wrongIngredients = getTwoRandomWrongIngredients(meal) ?: return null
            val options = (listOf(correctIngredient) + wrongIngredients).shuffled()
            currentRound =IngredientGameRound(meal.name, options, correctIngredient)
            return currentRound
    }


    private fun getRandomMeal(): Food? {
        val available = getAllFood().filterNot { it.name==null || usedMeals.contains(it.name) }
        return if (available.isNotEmpty()) available.random() else null
    }

    private fun getTwoRandomWrongIngredients(correctMeal: Food): List<String>? {
        val allIngredients =  getAllFood().flatMap { it.ingredients }.distinct().filterNot { it in correctMeal.ingredients }
        return if(allIngredients.isNotEmpty())  allIngredients.shuffled().take(2) else null


    }
    fun hasNextRound(): Boolean = correctAnswers < 15
    fun submitGuess(guess: String): Boolean {
        return if (guess == currentRound.correctAnswer) {
            score += 1000
            correctAnswers++
            true
        } else false
    }
    fun getScore(): Int = score


}

