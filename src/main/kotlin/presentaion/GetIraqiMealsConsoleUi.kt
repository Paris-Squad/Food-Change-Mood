package org.example.presentaion

import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.MealException
import org.example.utils.formatDetails

class GetIraqiMealsConsoleUi(private val getIraqiMealsUseCase: GetIraqiMealsUseCase) {

    operator fun invoke() {
        try {
            val iraqiMeals = getIraqiMealsUseCase.getIraqiMeals().getOrThrow()

            println("--- IRAQI MEALS ---")
            iraqiMeals.forEachIndexed { index, meal ->
                println("${index + 1}.")
                println(meal.formatDetails())
            }
        } catch (e: MealException.NoMealsFoundException) {
            println(e.message)
        }
    }
}
