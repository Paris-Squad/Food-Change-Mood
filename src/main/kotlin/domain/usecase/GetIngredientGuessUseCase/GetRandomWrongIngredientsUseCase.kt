package org.example.domain.usecase.GetIngredientGuessUseCase

import domain.model.Meal
import org.example.domain.repository.MealRepository

class GetRandomWrongIngredientsUseCase(private val mealRepository : MealRepository) {

    fun getRandomWrongIngredients(correctMeal: Meal,number:Int): List<String> {
        val allIngredients =  mealRepository.getMeals()
            .flatMap { it.ingredients }
            .distinct()
            .filterNot { it in correctMeal.ingredients }
        return  allIngredients.shuffled().take(number)
    }
}