package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetMealsByCountryUseCase

class GetMealsByCountryConsoleUi(private val getMealsByCountryUseCase: GetMealsByCountryUseCase) {

    operator fun invoke() {
        print("Enter a country mealName : ")
        val country = readlnOrNull()?.trim()

        if (country.isNullOrEmpty()) {
            println("Country mealName cannot be empty.")
            return
        }

        print("Enter number of meals to display: ")
        val countInput = readlnOrNull()?.trim()
        val count = countInput?.toIntOrNull()

        if (count == null || count <= 0) {
            println("Invalid number. Please enter a positive integer.")
            return
        }

        try {
            val meals = getMealsByCountryUseCase.getMealsByCountry(country,count).getOrThrow()

            println("\n--- MEALS RELATED TO \"$country\" ---")
            meals.forEachIndexed { index, meal ->
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
        } catch (e: MealException.NoMealsFoundForCountry) {
            println("\nNo meals found related to \"$country\".")
        }
    }
}
