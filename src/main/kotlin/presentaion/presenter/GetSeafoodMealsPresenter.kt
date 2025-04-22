package org.example.presentaion.presenter

import org.example.domain.usecase.GetSeafoodMealsUseCase

class GetSeafoodMealsPresenter(
    private val getSeafoodMealsUseCase: GetSeafoodMealsUseCase
) {
    fun start() {
        val allSeafoodMeals = getSeafoodMealsUseCase()
        allSeafoodMeals.fold(
            onSuccess = ::handleSuccess,
            onFailure = { println(it.message) }
        )

    }

    private fun handleSuccess(allSeafoodMeals: List<Pair<String, Float>>) {
        allSeafoodMeals.forEachIndexed { index, seafoodMeal ->
            println("${index + 1}- Name: ${seafoodMeal.first}, Protein: ${seafoodMeal.second}")
        }
    }
}