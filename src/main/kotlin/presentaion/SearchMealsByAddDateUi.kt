package org.example.presentaion

import kotlinx.datetime.LocalDate
import org.example.domain.FoodException
import org.example.domain.usecase.SearchMealsByAddDateUseCase

class SearchMealsByAddDateUi(private val useCase: SearchMealsByAddDateUseCase) {

    operator fun invoke() {
        println("Enter a date (yyyy-MM-dd):")
        val input = readln()

        val dateResult = parseDate(input)

        dateResult.onFailure {
            println(" ${it.message}")
            return
        }

        val date = dateResult.getOrThrow()
        val mealsResult = useCase.findMealsByDate(date)

        mealsResult.onFailure {
            println(" ${it.message}")
            return
        }

        val meals = mealsResult.getOrThrow()

        println("\n========== MEALS ADDED ON $date ==========\n")
        meals.forEachIndexed { index, meal ->
            println("Meal ${index + 1}: ID: ${meal.id} | Name: ${meal.name ?: "Unnamed Meal"}")
        }

        println("\nEnter the ID of a meal to view full details:")
        val mealId = readln()

        val detailResult = useCase.findMealByIdInList(mealId)
        detailResult.onSuccess { meal ->
            println("--------------------------------------------------")
            println("Meal ${meal.name ?: "Unnamed Recipe"}")
            println("ID: ${meal.id}")
            println("Preparation Time: ${meal.minutes} minutes")
            println("Contributor ID: ${meal.contributorId}")
            println("Date Submitted: ${meal.submitted}")
            println("Tags: ${meal.tags.joinToString(", ").ifEmpty { "No tags available" }}")
            println("Nutrition Information: ${meal.nutrition}")
            println("Steps (${meal.numberOfSteps}):")
            meal.steps.forEachIndexed { stepIndex, step ->
                println("   ${stepIndex + 1}. $step")
            }
            println("Description: ${meal.description ?: "No description available"}")
            println("Ingredients (${meal.numberOfIngredients}):")
            meal.ingredients.forEach { ingredient ->
                println("   - $ingredient")
            }
            println("--------------------------------------------------\n")
        }
    }
    private fun parseDate(input:String):Result<LocalDate> {
        return try {
            Result.success(LocalDate.parse(input))
        } catch (e: IllegalArgumentException) {
            Result.failure(FoodException.InvalidDateFormatException("Invalid date format: Use yyyy-MM-dd"))
        }
    }
}