package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class RandomPotatoMealsPresenter(
    private val getRandomPotatoMeals: GetRandomPotatoMealsUseCase, printer: Printer
) : BasePresenter(printer) {
    fun startRandomPotatoMeals() {
        val potatoMeals = getRandomPotatoMeals(10)

        printer.displayLn("========== RANDOM POTATO MEALS ==========\n")
        potatoMeals.fold(
            onSuccess = ::handleSuccess, onFailure = ::handleException
        )
    }

    private fun handleSuccess(meal: List<Meal>) {
        meal.forEachIndexed { index, meal ->
            printer.displayLn("Meal ${index + 1}: ${meal.mealName ?: "Unnamed Meal"}")
            printer.displayLn(meal.formatDetails())
        }
    }
}
