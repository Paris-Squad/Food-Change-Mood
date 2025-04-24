package org.example.presentaion.presenter

import domain.model.Meal
import kotlinx.datetime.LocalDate
import org.example.domain.MealException
import org.example.domain.usecase.SearchMealsByAddDateUseCase
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class SearchMealsByAddDatePresenter(
    private val searchMealsByAddDate: SearchMealsByAddDateUseCase, printer: Printer, private val reader: InputReader
) : BasePresenter(printer) {
    fun searchMealsByCreationDate() {
        var shouldRepeat = true
        var date: LocalDate? = null

        while (shouldRepeat) {
            var meals = emptyList<Meal>()
            if (date == null) {
                printer.displayLn("Enter a date (yyyy-MM-dd):")
                val input = readln()
                val dateResult = parseDate(input)
                if (dateResult.isFailure) {
                    printer.displayLn(" ${dateResult.exceptionOrNull()?.message}")
                    continue
                }

                date = dateResult.getOrThrow()
                val mealsResult = searchMealsByAddDate(date)

                if (mealsResult.isFailure) {
                    printer.displayLn(" ${mealsResult.exceptionOrNull()?.message}")
                    date = null
                    continue
                }

                meals = mealsResult.getOrThrow()

                printer.displayLn("\n========== MEALS ADDED ON $date ==========\n")
                meals.forEachIndexed { index, meal ->
                    printer.displayLn("Meal ${index + 1}: ID: ${meal.mealId} | Name: ${meal.mealName ?: "Unnamed Meal"}")
                }
            }

            printer.displayLn("\nEnter the ID of a meal to view full details:")
            val mealId = readln()
            val detailResult = searchMealsByAddDate.findMealByIdInList(mealId, meals)

            if (detailResult.isFailure) {
                printer.displayLn(" ${detailResult.exceptionOrNull()?.message}")
                continue
            }

            detailResult.onSuccess { meal ->
                printer.displayLn("--------------------------------------------------")
                printer.displayLn(meal.formatDetails())
                printer.displayLn("--------------------------------------------------\n")
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
