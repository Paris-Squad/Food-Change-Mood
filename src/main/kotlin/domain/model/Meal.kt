package org.example.model

import kotlinx.datetime.LocalDate

data class Meal(
    val mealName: String?,
    val mealId: String,
    val minutesForPreparation: Int,
    val contributorId: String,
    val submittedDate: LocalDate,
    val tags: List<String>,
    val nutrition: Nutrition,
    val numberOfSteps: Int,
    val steps: List<String>,
    val description: String?,
    val ingredients: List<String>,
    val numberOfIngredients: Int
) {

    fun formatDetails(): String {
        val details = StringBuilder()
        details.append("\n--- FULL DETAILS ---\n")
        details.append("Name: ${mealName ?: "Unnamed"}\n")
        details.append("Preparation time: $minutesForPreparation minutes\n")
        details.append("Description: ${description ?: "No description available"}\n")
        details.append("Ingredients (${numberOfIngredients}):\n")
        ingredients.forEach { details.append("- $it\n") }
        details.append("\nPreparation steps:\n")
        steps.forEachIndexed { index, step ->
            details.append("${index + 1}. $step\n")
        }
        details.append("\nNutrition information:\n")
        details.append("Calories: ${nutrition.calories}\n")
        details.append("Carbohydrates: ${nutrition.carbohydrates}g\n")
        details.append("Protein: ${nutrition.protein}g\n")
        details.append("Fat: ${nutrition.totalFat}g\n")
        details.append("Sugar: ${nutrition.sugar}g\n")
        return details.toString()
    }
}