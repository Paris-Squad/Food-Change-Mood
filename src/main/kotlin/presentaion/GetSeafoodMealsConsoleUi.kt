package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetSeafoodMealsUseCase

class GetSeafoodMealsConsoleUi(
    private val getSeafoodMealsUseCase: GetSeafoodMealsUseCase
) {
    fun start() {
        try {
            val allSeafoodMeals = getSeafoodMealsUseCase.execute()
            allSeafoodMeals.forEachIndexed { index, seafoodMeal ->
                println("${index + 1}- Name: ${seafoodMeal.first}, Protein: ${seafoodMeal.second}")
            }
        } catch (exception: MealException.NoSeaFoodMealsFound) {
            println(exception.message)
        }

    }
}