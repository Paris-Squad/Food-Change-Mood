package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetMealsByCountryUseCase

class GetMealsByCountryConsoleUi(private val getMealsByCountryUseCase: GetMealsByCountryUseCase) {

    operator fun invoke() {
        print("Enter a country mealName : ")
        val country = readlnOrNull()?.trim()

        if (country.isNullOrEmpty()) {
            println("Country mealName cannot be empty.")
            return
        }

        print("Enter number of meals to display: ")
        val countInput = readlnOrNull()?.trim()
        val count = countInput?.toIntOrNull()

        if (count == null || count <= 0) {
            println("Invalid number. Please enter a positive integer.")
            return
        }

        try {
            val meals = getMealsByCountryUseCase.getMealsByCountry(country, count).getOrThrow()

            println("\n--- MEALS RELATED TO \"$country\" ---")
            meals.forEachIndexed { index, meal ->
                println("${index + 1}.")
                println(meal.formatDetails())
            }
        } catch (e: MealException.NoMealsFoundException) {
            println(e.message)
        }
    }
}
