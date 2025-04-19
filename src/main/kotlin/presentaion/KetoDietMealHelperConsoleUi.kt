package org.example.presentaion

import domain.model.Meal
import org.example.domain.usecase.GetKetoDietMealUseCase

class KetoDietMealHelperConsoleUi(private val getKetoDietMealUseCase: GetKetoDietMealUseCase) {
    private val repeatedMeals = mutableSetOf<Meal>()
    companion object {
        private const val SUGGEST_KETO_MEAL_OPTION = "1"
        private const val EXIT_OPTION = "2"
    }

    operator fun invoke(){
        println("click 1 if you want a suggested keto diet meal.")
        println("click 2 to exit.")
        val pickedChoice = readln()
        when(pickedChoice.trim()){
            SUGGEST_KETO_MEAL_OPTION -> {
                getKetoDietMealUseCase.getSuggestedKetoMeal(repeatedMeals).fold(
                    onSuccess = {
                        repeatedMeals.add(it)
                        println("\nSuggested Keto Meal:")
                        println("Name: ${it.mealName}")
                        println("Description: ${it.description ?: "No description available"}")
                        println("Preparation Time: ${it.minutesForPreparation} minutes")
                        println("\nNutrition Info:")
                        println("Carbs: ${it.nutrition.carbohydrates}g")
                        println("Fat: ${it.nutrition.totalFat}g")
                        println("Protein: ${it.nutrition.protein}g")

                    },
                    onFailure = {
                        println("Error: ${it.message}")
                    }
                )
            }
            EXIT_OPTION-> return
            else -> println("you need to pick a valid choice")
        }
    }
}