package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
import org.example.utils.formatDetails

class RandomPotatoMealsPresenter(private val getRandomPotatoMealsUseCase: GetRandomPotatoMealsUseCase) {
    fun startRandomPotatoMeals() {
        val potatoMeals = getRandomPotatoMealsUseCase(10)

        println("========== RANDOM POTATO MEALS ==========\n")
        potatoMeals.fold(
            onSuccess = ::handleSuccess,
            onFailure = { println(it.message) }
        )
    }

    private fun handleSuccess(meal: List<Meal>) {
        meal.forEachIndexed { index, meal ->
            println("Meal ${index + 1}: ${meal.mealName ?: "Unnamed Meal"}")
            println(meal.formatDetails())
        }
    }
}
