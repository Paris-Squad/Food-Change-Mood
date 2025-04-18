package org.example.presentaion

import org.example.domain.usecase.KetoDietMealHelperUseCase

class KetoDietMealHelperConsoleUi(private val ketoDietMealHelperUseCase: KetoDietMealHelperUseCase) {

    operator fun invoke(){
        println("click 1 if you want a suggested keto diet meal.")
        println("click 2 to exit.")
        val pickedChoice = readln()
        when(pickedChoice.trim()){
            "1" -> {
                ketoDietMealHelperUseCase.ketoDietMeal().fold(
                    onSuccess = {
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
            "2"-> return
            else -> println("you need to pick a valid choice")
        }
    }
}