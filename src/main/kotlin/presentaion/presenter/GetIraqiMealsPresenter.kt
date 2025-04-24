package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.utils.formatDetails

class GetIraqiMealsPresenter(private val getIraqiMeals: GetIraqiMealsUseCase):BasePresenter() {

    fun getIraqiMeals() {
        val iraqiMealsResult = getIraqiMeals.getIraqiMeals()
        iraqiMealsResult.fold(onSuccess = ::onGetIraqiMealsSuccess, onFailure = ::handleException)
    }

    private fun onGetIraqiMealsSuccess(iraqiMeals: List<Meal>) {
        println("--- IRAQI MEALS ---")
        iraqiMeals.forEachIndexed { index, meal ->
            println("${index + 1}.")
            println(meal.formatDetails())
        }
    }
}
