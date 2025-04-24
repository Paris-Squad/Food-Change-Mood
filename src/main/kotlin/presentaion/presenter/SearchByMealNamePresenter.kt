package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.SearchByMealNameUseCase
import kotlin.collections.forEachIndexed
import org.example.utils.formatDetails

class SearchByMealNamePresenter(private val searchByMealName: SearchByMealNameUseCase) : BasePresenter() {
    fun startSearchByName() {
        println("\n=== Search Meals by Name ===")
        println("Enter a meal name to search")

        print("> ")
        val searchTerm = readlnOrNull()?.trim()

        if (!searchTerm.isNullOrEmpty()) {
            val result = searchByMealName.invoke(searchTerm)

            result.fold(onSuccess = { handleSuccess(it, searchTerm) }, onFailure = ::handleException)
        } else println("Please enter a valid search term.")
    }

    private fun handleSuccess(meals: List<Meal>, searchTerm: String) {
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
