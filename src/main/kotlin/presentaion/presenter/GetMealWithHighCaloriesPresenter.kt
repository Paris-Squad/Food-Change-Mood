package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetMealWithHighCaloriesUseCase

class GetMealWithHighCaloriesPresenter(private val mealsWithHighCaloriesUseCase: GetMealWithHighCaloriesUseCase) {

    init {
        getMealsWithHighCalories()
    }

    var randomMealWithHighCalories: Meal? = null

    fun getMealsWithHighCalories() {
        val mealsWithHighCaloriesResult = mealsWithHighCaloriesUseCase(REQUIRED_CALORIES)
        mealsWithHighCaloriesResult.fold(
            onSuccess = ::onGetMealWithHighCaloriesSuccess,
            onFailure = ::onGetMealWithHighCaloriesFailure
        )
    }

    companion object {
        const val REQUIRED_CALORIES = 700f
    }

    fun onGetMealWithHighCaloriesSuccess(meal: Meal) {
        randomMealWithHighCalories = meal
        println("Meal name is: ${meal.mealName}")
        println("Meal description is: ${meal.description}")
    }

    fun onGetMealWithHighCaloriesFailure(exception: Throwable) {
        println(exception.message.toString())
    }
}



