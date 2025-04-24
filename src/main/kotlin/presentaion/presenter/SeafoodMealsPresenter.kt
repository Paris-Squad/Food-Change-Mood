package org.example.presentaion.presenter

import org.example.domain.usecase.GetSeafoodMealsUseCase
import org.example.presentaion.presenter.io.Printer

class SeafoodMealsPresenter(
    private val seafoodMeals: GetSeafoodMealsUseCase, printer: Printer
) : BasePresenter(printer) {
    fun presentSeafoodMeals() {
        val allSeafoodMeals = seafoodMeals()
        allSeafoodMeals.fold(
            onSuccess = ::handleSuccess, onFailure = ::handleException
        )
    }

    private fun handleSuccess(allSeafoodMeals: List<Pair<String, Float>>) {
        allSeafoodMeals.forEachIndexed { index, seafoodMeal ->
            printer.displayLn("${index + 1}- Name: ${seafoodMeal.first}, Protein: ${seafoodMeal.second}")
        }
    }
}