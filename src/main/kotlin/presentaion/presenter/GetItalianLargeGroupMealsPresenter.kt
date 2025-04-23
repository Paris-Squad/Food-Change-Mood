package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetItalianLargeGroupMealsUseCase

class GetItalianLargeGroupMealsPresenter(
    private val useCase: GetItalianLargeGroupMealsUseCase
) : BasePresenter() {
    fun startItalianLargeGroupMeal() {
        useCase.invoke().fold(onSuccess = ::handleSuccess, onFailure = ::handleException)
    }
    private fun handleSuccess(meals: List<Meal>) {
        println("--- ITALIAN MEALS FOR LARGE GROUPS ---")
        meals.forEachIndexed { idx, meal ->
            println("${idx + 1}. ${meal.mealName ?: "Unnamed"} (ID: ${meal.mealId})")
            println("   Time: ${meal.minutesForPreparation} min | Tags: ${meal.tags.joinToString()}")
            println("   Ingredients: ${meal.ingredients.joinToString()}\n")
        }
    }
}