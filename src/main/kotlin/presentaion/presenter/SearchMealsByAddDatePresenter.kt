package org.example.presentaion.presenter

import domain.model.Meal
import kotlinx.datetime.LocalDate
import org.example.domain.MealException
import org.example.domain.usecase.SearchMealsByAddDateUseCase
import org.example.utils.formatDetails

class SearchMealsByAddDatePresenter(private val useCase: SearchMealsByAddDateUseCase) {
    fun search() {
        var shouldRepeat = true
        var date: LocalDate? = null

        while (shouldRepeat) {
            var meals = emptyList<Meal>()
            if (date == null) {
                println("Enter a date (yyyy-MM-dd):")
                val input = readln()
                val dateResult = parseDate(input)
                if (dateResult.isFailure) {
                    println(" ${dateResult.exceptionOrNull()?.message}")
                    continue
                }

                date = dateResult.getOrThrow()
                val mealsResult = useCase(date)

                if (mealsResult.isFailure) {
                    println(" ${mealsResult.exceptionOrNull()?.message}")
                    date = null
                    continue
                }

                meals = mealsResult.getOrThrow()

                println("\n========== MEALS ADDED ON $date ==========\n")
                meals.forEachIndexed { index, meal ->
                    println("Meal ${index + 1}: ID: ${meal.mealId} | Name: ${meal.mealName ?: "Unnamed Meal"}")
                }
            }

            println("\nEnter the ID of a meal to view full details:")
            val mealId = readln()
            val detailResult = useCase.findMealByIdInList(mealId, meals)

            if (detailResult.isFailure) {
                println(" ${detailResult.exceptionOrNull()?.message}")
                continue
            }

            detailResult.onSuccess { meal ->
                println("--------------------------------------------------")
                println(meal.formatDetails())
                println("--------------------------------------------------\n")
            }
            shouldRepeat = false
        }
    }

    private fun parseDate(input: String): Result<LocalDate> {
        return try {
            Result.success(LocalDate.parse(input))
        } catch (e: IllegalArgumentException) {
            Result.failure(MealException.InvalidDateFormatException("Invalid date format: Use yyyy-MM-dd"))
        }
    }
}
