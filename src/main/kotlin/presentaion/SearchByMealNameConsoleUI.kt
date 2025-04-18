package org.example.presentaion

import domain.model.Meal
import org.example.domain.usecase.SearchByMealNameUseCase
import kotlin.collections.forEachIndexed

class SearchByMealNameConsoleUI(
    private val searchByMealNameUseCase: SearchByMealNameUseCase
) {

    fun invoke() {
        println("\n=== Search Meals by Name ===")
        println("Enter a meal name to search")

        print("> ")
        val searchTerm = readlnOrNull()?.trim()

        if (!searchTerm.isNullOrEmpty()) {
            val result = searchByMealNameUseCase.invoke(searchTerm)

            result.onSuccess { meals ->
                if (meals.isEmpty()) {
                    println("No meals found matching '$searchTerm'.")
                } else {
                    println("\n--- Search Results ---")
                    meals.forEachIndexed { index, meal ->
                        println("${index + 1}. ${meal.mealName ?: "Unnamed Meal"}")
                    }
                    println("--------------------")

                    selectMeal(meals)
                }
            }.onFailure { exception ->
                println("Error: ${exception.message ?: "An error occurred during search."}")
            }
        } else println("Please enter a valid search term.")
    }


    private fun selectMeal(meals: List<Meal>) {
        println("\nEnter the number of the meal to see details:")
        print("> ")

        val selectionIndex = readlnOrNull()?.trim()?.toIntOrNull()
        if (selectionIndex != null && selectionIndex > 0 && selectionIndex <= meals.size) {
            val selectedMeal = meals[selectionIndex - 1]
            println(selectedMeal.formatDetails())
        } else {
            println("Invalid selection.")
        }
    }
}
