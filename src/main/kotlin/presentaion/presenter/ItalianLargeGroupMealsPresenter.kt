package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetItalianLargeGroupMealsUseCase
import org.example.presentaion.presenter.io.Printer

class ItalianLargeGroupMealsPresenter(
    private val italianMealForLargeGroup: GetItalianLargeGroupMealsUseCase,
    printer: Printer
) : BasePresenter(printer) {
    fun startItalianLargeGroupMeal() {
        italianMealForLargeGroup.invoke()
            .fold(onSuccess = ::onGetItalianLargeGroupSuccess, onFailure = ::handleException)
    }

    private fun onGetItalianLargeGroupSuccess(meals: List<Meal>) {
        printer.displayLn("--- ITALIAN MEALS FOR LARGE GROUPS ---")
        meals.forEachIndexed { idx, meal ->
            printer.displayLn("${idx + 1}. ${meal.mealName ?: "Unnamed"} (ID: ${meal.mealId})")
            printer.displayLn("   Time: ${meal.minutesForPreparation} min | Tags: ${meal.tags.joinToString()}")
            printer.displayLn("   Ingredients: ${meal.ingredients.joinToString()}\n")
        }
    }
}