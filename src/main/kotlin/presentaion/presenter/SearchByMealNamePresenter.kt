package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.SearchByMealNameUseCase
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class SearchByMealNamePresenter(
    private val searchByMealName: SearchByMealNameUseCase, printer: Printer, private val reader: InputReader
) : BasePresenter(printer) {
    fun startSearchByName() {
        printer.displayLn("\n=== Search Meals by Name ===")
        printer.displayLn("Enter a meal name to search")

        printer.display("> ")
        val searchTerm = reader.readString()

        if (!searchTerm.isNullOrEmpty()) {
            val result = searchByMealName.invoke(searchTerm)

            result.fold(onSuccess = { handleSuccess(it, searchTerm) }, onFailure = ::handleException)
        } else printer.displayLn("Please enter a valid search term.")
    }

    private fun handleSuccess(meals: List<Meal>, searchTerm: String) {
        if (meals.isEmpty()) {
            printer.displayLn("No meals found matching '$searchTerm'.")
        } else {
            printer.displayLn("\n--- Search Results ---")
            meals.forEachIndexed { index, meal ->
                printer.displayLn("${index + 1}. ${meal.mealName ?: "Unnamed Meal"}")
            }
            printer.displayLn("--------------------")

            selectMeal(meals)
        }
    }

    private fun selectMeal(meals: List<Meal>) {
        printer.displayLn("\nEnter the number of the meal to see details:")
        printer.display("> ")

        val selectionIndex = readlnOrNull()?.trim()?.toIntOrNull()
        if (selectionIndex != null && selectionIndex > 0 && selectionIndex <= meals.size) {
            val selectedMeal = meals[selectionIndex - 1]
            printer.displayLn(selectedMeal.formatDetails())
        } else {
            printer.displayLn("Invalid selection.")
        }
    }
}
