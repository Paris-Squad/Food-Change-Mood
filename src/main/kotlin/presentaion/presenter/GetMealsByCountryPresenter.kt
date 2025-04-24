package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.utils.formatDetails

class GetMealsByCountryPresenter(private val getMealsByCountryUseCase: GetMealsByCountryUseCase):BasePresenter() {

    fun getMealsByCountry() {
        val country = getCountryInput()
        val count = getCountInput()

        getMealsByCountryUseCase(country, count).fold(
            onSuccess = ::onGetMealsByCountrySuccess,
            onFailure = ::handleException
        )
    }

    private fun getCountryInput(): String? {
        print("Enter a country mealName: ")
        return readlnOrNull()?.trim()
    }

    private fun getCountInput(): Int? {
        print("Enter number of meals to display: ")
        return readlnOrNull()?.trim()?.toIntOrNull()
    }

    private fun onGetMealsByCountrySuccess(meals: List<Meal>) {
        meals.forEachIndexed { index, meal ->
            println("${index + 1}.")
            println(meal.formatDetails())
        }
    }
}
