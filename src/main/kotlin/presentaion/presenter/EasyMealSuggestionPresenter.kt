package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetEasyMealSuggestionUseCase
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class EasyMealSuggestionPresenter(private val getEasyMealSuggestion: GetEasyMealSuggestionUseCase, printer: Printer) :
    BasePresenter(printer) {
    fun startEasyMeals() {
        getEasyMealSuggestion().fold(onSuccess = ::onGettingEasyMealSuggestionSuccess, onFailure = ::handleException)
    }

    private fun onGettingEasyMealSuggestionSuccess(meals: List<Meal>) {
        printer.displayLn(TITLE)
        meals.forEachIndexed { index, meal ->
            printer.displayLn(meal.formatDetails())
        }
    }

    companion object {
        const val TITLE = "EASY MEAL SUGGESTIONS"
    }
}
