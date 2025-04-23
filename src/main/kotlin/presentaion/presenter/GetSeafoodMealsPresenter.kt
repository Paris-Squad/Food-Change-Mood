package org.example.presentaion.presenter

import org.example.domain.usecase.GetSeafoodMealsUseCase

class GetSeafoodMealsPresenter(private val getSeafoodMealsUseCase: GetSeafoodMealsUseCase) : BasePresenter() {
    fun start() {
        val allSeafoodMeals = getSeafoodMealsUseCase()
        allSeafoodMeals.fold(
            onSuccess = ::handleSuccess,
            onFailure = ::handleException
        )
    }

    private fun handleSuccess(allSeafoodMeals: List<Pair<String, Float>>) {
        allSeafoodMeals.forEachIndexed { index, seafoodMeal ->
            println("${index + 1}- Name: ${seafoodMeal.first}, Protein: ${seafoodMeal.second}")
        }
    }
}