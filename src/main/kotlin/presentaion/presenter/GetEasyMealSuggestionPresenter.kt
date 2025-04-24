package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetEasyMealSuggestionUseCase
import org.example.utils.formatDetails

class GetEasyMealSuggestionPresenter(private val getEasyMealSuggestion: GetEasyMealSuggestionUseCase): BasePresenter() {
    fun startEasyMeals() {
        getEasyMealSuggestion().fold(onSuccess = ::onGettingEasyMealSuggestionSuccess, onFailure = ::handleException)
    }

    private fun onGettingEasyMealSuggestionSuccess(meals: List<Meal>) {
        println("--- EASY MEAL SUGGESTIONS ---")
        meals.forEachIndexed { index, meal ->
            println(meal.formatDetails())
        }
    }
}
