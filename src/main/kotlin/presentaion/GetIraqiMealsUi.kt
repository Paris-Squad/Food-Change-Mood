package org.example.presentaion

import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.FoodException

class GetIraqiMealsUi(private val getIraqiMealsUseCase: GetIraqiMealsUseCase) {

    operator fun invoke() {
        try {
            val iraqiMeals = getIraqiMealsUseCase.getIraqiMeals().getOrThrow()

            println("--- IRAQI MEALS ---")
            iraqiMeals.forEachIndexed { index, food ->
                println("${index + 1}.")
                println("   Name: ${food.mealName ?: "Unnamed recipe"}")
                println("   ID: ${food.mealId}")
                println("   Time: ${food.minutesForPreparation} minutes")
                println("   Contributor ID: ${food.contributorId}")
                println("   Submitted: ${food.submittedDate}")
                println("   Tags: ${food.tags.joinToString(", ")}")
                println("   Nutrition: ${food.nutrition}")
                println("   Steps (${food.numberOfSteps}):")
                food.steps.forEachIndexed { i, step -> println("      ${i + 1}. $step") }
                println("   Description: ${food.description ?: "No description"}")
                println("   Ingredients (${food.numberOfIngredients}): ${                     
                    food.ingredients.joinToString(", ")
                }")
                println()
            }
        } catch (e: FoodException.NoIraqiFoodFound) {
            println("No Iraqi Foods found")
        }
    }
}
