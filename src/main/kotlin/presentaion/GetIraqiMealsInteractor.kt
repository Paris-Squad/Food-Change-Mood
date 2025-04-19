package org.example.presentaion

import domain.model.Meal
import org.example.domain.usecase.GetIraqiMealsUseCase

class GetIraqiMealsInteractor(private val getIraqiMealsUseCase: GetIraqiMealsUseCase) {

    fun getIraqiMeals(){
        val iraqiMealsResult = getIraqiMealsUseCase.getIraqiMeals()
        iraqiMealsResult.fold(
            onSuccess = :: onGetIraqiMealsSuccess ,
            onFailure = :: onGetIraqiMealsError
        )
    }

    private fun onGetIraqiMealsSuccess(iraqiMeals : List<Meal>){
        println("--- IRAQI MEALS ---")
        iraqiMeals.forEachIndexed { index, meal ->
            println("${index + 1}.")
            println(meal.formatDetails())
        }
    }

    private fun onGetIraqiMealsError(error : Throwable){
        println(error.message)
    }

}
