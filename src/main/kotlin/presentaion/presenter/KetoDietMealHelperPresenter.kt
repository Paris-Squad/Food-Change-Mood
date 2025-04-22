package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetKetoDietMealUseCase

class KetoDietMealHelperPresenter(private val getKetoDietMealUseCase: GetKetoDietMealUseCase) {
    private val repeatedMeals = mutableSetOf<Meal>()

    fun startKetoHelper() {
        println("click 1 if you want a suggested keto diet meal.")
        println("click 2 to exit.")
        val pickedChoice = readln()
        when (pickedChoice.trim()) {
            SUGGEST_KETO_MEAL_OPTION -> {
                getKetoDietMealUseCase.getSuggestedKetoMeal(repeatedMeals).fold(
                    onSuccess = ::handleSuccess, onFailure = { println("Error: ${it.message}") })
            }

            EXIT_OPTION -> return
            else -> println("you need to pick a valid choice")
        }
    }

    private fun handleSuccess(meal: Meal) {
        repeatedMeals.add(meal)
        println("\nSuggested Keto Meal:")
        println("Name: ${meal.mealName}")
        println("Description: ${meal.description ?: "No description available"}")
        println("Preparation Time: ${meal.minutesForPreparation} minutes")
        println("\nNutrition Info:")
        println("Carbs: ${meal.nutrition.carbohydrates}g")
        println("Fat: ${meal.nutrition.totalFat}g")
        println("Protein: ${meal.nutrition.protein}g")
    }

    companion object {
        private const val SUGGEST_KETO_MEAL_OPTION = "1"
        private const val EXIT_OPTION = "2"
    }
}