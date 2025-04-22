package org.example.domain.usecase

import org.example.domain.model.IngredientGameRound
import org.example.domain.repository.MealRepository
import domain.model.Meal

class GetIngredientGuessUseCase(private val mealRepository: MealRepository) {
    private var score = 0
    private var correctAnswers = 0
    private val usedMeals = mutableSetOf<String>()
    private var currentRound: IngredientGameRound? = null

    fun nextRound(): IngredientGameRound? {
        val meal = getRandomMeal() ?: return null
        usedMeals.add(meal.mealName!!)
        val correctIngredient = meal.ingredients.random()
        val wrongIngredients = getRandomWrongIngredients(meal, 2) ?: return null
        val options = (listOf(correctIngredient) + wrongIngredients).shuffled()
        currentRound = IngredientGameRound(meal.mealName, options, correctIngredient)
        return currentRound
    }

    private fun getRandomMeal(): Meal? {
        val available = mealRepository.getMeals().filterNot { it.mealName == null || usedMeals.contains(it.mealName) }
        return if (available.isNotEmpty()) available.random() else null
    }

    private fun getRandomWrongIngredients(correctMeal: Meal, number: Int): List<String>? {
        val allIngredients =
            mealRepository.getMeals().flatMap { it.ingredients }.distinct().filterNot { it in correctMeal.ingredients }
        return if (allIngredients.isNotEmpty()) allIngredients.shuffled().take(number) else null
    }

    fun hasNextRound(): Boolean = correctAnswers < MAX_ROUNDS
    fun submitGuess(guess: String): Boolean {
        val round = currentRound ?: return false
        return if (guess == round.correctAnswer) {
            score += CORRECT_ANSWER_SCORE
            correctAnswers++
            true
        } else false
    }

    fun getScore(): Int = score

    companion object{
        const val MAX_ROUNDS = 15
        const val CORRECT_ANSWER_SCORE = 1000
    }
}

