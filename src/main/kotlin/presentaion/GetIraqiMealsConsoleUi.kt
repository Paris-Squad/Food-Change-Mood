package org.example.presentaion

import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.MealException

class GetIraqiMealsConsoleUi(private val getIraqiMealsUseCase: GetIraqiMealsUseCase) {

    operator fun invoke() {
        try {
            val iraqiMeals = getIraqiMealsUseCase.getIraqiMeals().getOrThrow()

            println("--- IRAQI MEALS ---")
            iraqiMeals.forEachIndexed { index, meal ->
                println("${index + 1}.")
                println("   Name: ${meal.mealName ?: "Unnamed recipe"}")
                println("   ID: ${meal.mealId}")
                println("   Time: ${meal.minutesForPreparation} minutes")
                println("   Contributor ID: ${meal.contributorId}")
                println("   Submitted: ${meal.submittedDate}")
                println("   Tags: ${meal.tags.joinToString(", ")}")
                println("   Nutrition: ${meal.nutrition}")
                println("   Steps (${meal.numberOfSteps}):")
                meal.steps.forEachIndexed { i, step -> println("      ${i + 1}. $step") }
                println("   Description: ${meal.description ?: "No description"}")
                println("   Ingredients (${meal.numberOfIngredients}): ${
                    meal.ingredients.joinToString(", ")
                }")
                println()
            }
        } catch (exception: MealException.NoIraqiMealsFound) {
            println(exception.message)
        }
    }
}
