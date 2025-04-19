package org.example.utils

import domain.model.Meal

fun String.lineHasOddQuoteCount(): Boolean {
    return this.count { it == '"' } % 2 != 0
}

fun String.lineHasEvenOrZeroQuoteCount(): Boolean {
    return this.count { it == '"' } % 2 == 0
}

fun Float?.inRange(range: ClosedFloatingPointRange<Float>) : Boolean{
  return  this?.let {
        this in range
    } ?: false
}

fun Meal.formatDetails(): String {
    val details = StringBuilder()
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
