package org.example.presentaion

import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.utils.formatDetails

class GetMealsByCountryInteractor(private val getMealsByCountryUseCase: GetMealsByCountryUseCase) {

    fun getMealsByCountry() {
        val country = getCountryInput()
        val count = getCountInput()

        val result = getMealsByCountryUseCase.getMealsByCountry(country, count)

        result.fold(
            onSuccess = ::onGetMealsByCountrySuccess,
            onFailure = ::onGetMealsByCountryError
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

    private fun onGetMealsByCountrySuccess(meals: List<domain.model.Meal>) {
        meals.forEachIndexed { index, meal ->
            println("${index + 1}.")
            println(meal.formatDetails())
        }
    }

    private fun onGetMealsByCountryError(error: Throwable) {
        println(error.message)
    }
}
