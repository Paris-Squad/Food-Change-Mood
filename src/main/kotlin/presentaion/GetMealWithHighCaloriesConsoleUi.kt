package org.example.presentaion

import domain.model.Meal
import org.example.domain.usecase.GetMealWithHighCaloriesUseCase

class GetMealWithHighCaloriesConsoleUi(private val mealsWithHighCaloriesUseCase: GetMealWithHighCaloriesUseCase) {

    init {
        getMealsWithHighCalories()
    }

    var randomMealWithHighCalories: Meal? = null

     fun getMealsWithHighCalories() {
        val mealsWithHighCaloriesResult = mealsWithHighCaloriesUseCase.invoke(REQUIRED_CALORIES)
        mealsWithHighCaloriesResult.fold(
            onSuccess = { meal ->
                randomMealWithHighCalories = meal
                println("Meal name is: ${meal.mealName}")
                println("Meal description is: ${meal.description}")
            },
            onFailure = { exception ->
                println(exception.message.toString())
            }
        )

    }

    companion object {
        const val REQUIRED_CALORIES = 700f
    }
}
