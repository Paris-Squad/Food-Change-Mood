package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
import org.example.utils.formatDetails

class RandomPotatoMealsConsoleUi(private val getRandomPotatoMealsUseCase: GetRandomPotatoMealsUseCase) {

    operator fun invoke() {
        val potatoMeals = getRandomPotatoMealsUseCase.getRandomPotatoMeals()

        println("========== RANDOM POTATO MEALS ==========\n")
        potatoMeals
            .onSuccess { meal ->
                meal.forEachIndexed { index, meal ->
                    println("Meal ${index + 1}: ${meal.mealName ?: "Unnamed Recipe"}")
                    println(meal.formatDetails())
                }


            }
            .onFailure { exception ->
                if (exception is MealException.NoMealsFoundException) println(exception.message)
                else println("${exception.message}")
            }
    }
}
