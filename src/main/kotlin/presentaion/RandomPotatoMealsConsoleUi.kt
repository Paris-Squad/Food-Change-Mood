package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetRandomPotatoMealsUseCase

class RandomPotatoMealsConsoleUi(private val getRandomPotatoMealsUseCase: GetRandomPotatoMealsUseCase) {

    operator fun invoke() {
        try {
            val potatoMeals = getRandomPotatoMealsUseCase.getRandomPotatoMeals()

            println("========== RANDOM POTATO MEALS ==========\n")

            potatoMeals.forEachIndexed { index, meal ->
                println("Meal ${index + 1}: ${meal.mealName ?: "Unnamed Recipe"}")
                println(meal.formatDetails())
            }

        } catch (e: MealException.NoMealsFoundException) {
            println(e.message)
        }
    }
}
