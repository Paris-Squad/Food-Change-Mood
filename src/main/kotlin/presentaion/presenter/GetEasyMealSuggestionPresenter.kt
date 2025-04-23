package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetEasyMealSuggestionUseCase
import org.example.utils.formatDetails

class GetEasyMealSuggestionPresenter(private val useCase: GetEasyMealSuggestionUseCase): BasePresenter() {
    fun startEasyMeals() {
        useCase.invoke().fold(onSuccess = ::handleSuccess, onFailure = ::handleException)
    }

    private fun handleSuccess(meals: List<Meal>) {
        println("--- EASY MEAL SUGGESTIONS ---")
        meals.forEachIndexed { index, meal ->
            println(meal.formatDetails())
        }
    }

}
