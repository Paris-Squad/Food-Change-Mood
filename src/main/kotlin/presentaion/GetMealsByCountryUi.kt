package org.example.presentaion

import org.example.domain.FoodException
import org.example.domain.usecase.GetMealsByCountryUseCase

class GetMealsByCountryUi(private val getMealsByCountryUseCase: GetMealsByCountryUseCase) {

    operator fun invoke() {
        print("Enter a country name : ")
        val country = readlnOrNull()?.trim()

        if (country.isNullOrEmpty()) {
            println("Country name cannot be empty.")
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
            meals.forEachIndexed { index, food ->
                println("${index + 1}.")
                println("   Name: ${food.name ?: "Unnamed recipe"}")
                println("   ID: ${food.id}")
                println("   Time: ${food.minutes} minutes")
                println("   Contributor ID: ${food.contributorId}")
                println("   Submitted: ${food.submitted}")
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
        } catch (e: FoodException.NoMealsFoundForCountry) {
            println("\nNo meals found related to \"$country\".")
        }
    }
}
