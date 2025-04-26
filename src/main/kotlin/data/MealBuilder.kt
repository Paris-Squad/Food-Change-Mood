package org.example.data

import domain.model.Meal
import domain.model.Nutrition
import kotlinx.datetime.LocalDate
import org.example.data.utils.ColumnIndex
import org.example.data.utils.NutritionIndex

class MealBuilder {

    fun buildMeal(fields: List<String>) = Meal(
        mealName = fields[ColumnIndex.NAMES].trim(),
        mealId = fields[ColumnIndex.ID].trim(),
        minutesForPreparation = fields[ColumnIndex.MINUTES].trim().toInt(),
        contributorId = fields[ColumnIndex.CONTRIBUTOR_ID].trim(),
        submittedDate = LocalDate.parse(fields[ColumnIndex.SUBMITTED_DATE].trim()),
        tags = parseListField(fields[ColumnIndex.TAGS]),
        nutrition = parseNutrition(fields[ColumnIndex.NUTRITION]),
        numberOfSteps = fields[ColumnIndex.NUMBER_OF_STEPS].trim().toInt(),
        steps = parseListField(fields[ColumnIndex.STEPS]),
        description = fields[ColumnIndex.DESCRIPTION].trim(),
        ingredients = parseListField(fields[ColumnIndex.INGREDIENTS]),
        numberOfIngredients = fields[ColumnIndex.NUMBER_OF_INGREDIENTS].trim().toInt()
    )

    private fun parseListField(listField: String): List<String> {
        if (listField.isBlank() ||
            listField.startsWith("[").not() ||
            listField.endsWith("]").not()
        ) {
            return emptyList()
        }

      return  extractListItems(listField.removeSurrounding("[", "]"))
    }

    private fun extractListItems(content: String): List<String> {
        val result = mutableListOf<String>()
        val stringBuilder = StringBuilder()
        var inQuotes = false

        content.forEach { char ->
            when {
                char == '\'' -> {
                    inQuotes = !inQuotes
                    if (stringBuilder.isNotEmpty() && inQuotes.not()) {
                        result.add(stringBuilder.toString().trim())
                        stringBuilder.clear()
                    }
                }

                char == ',' && !inQuotes -> {
                    //do nothing
                }

                else -> stringBuilder.append(char)
            }
        }

        return result.map { it.removeSurrounding("'").trim() }
    }

    private fun parseNutrition(nutritionFiled: String): Nutrition {
        val values = parseNutritionValues(nutritionFiled)

        return Nutrition(
            calories = values.getOrNull(NutritionIndex.CALORIES),
            totalFat = values.getOrNull(NutritionIndex.TOTAL_FAT),
            sugar = values.getOrNull(NutritionIndex.SUGAR),
            sodium = values.getOrNull(NutritionIndex.SODIUM),
            protein = values.getOrNull(NutritionIndex.PROTEIN),
            saturatedFat = values.getOrNull(NutritionIndex.SATURATED_FAT),
            carbohydrates = values.getOrNull(NutritionIndex.CARBOHYDRATES)
        )
    }

    private fun parseNutritionValues(field: String): List<Float> {
        return if (field.startsWith("[") && field.endsWith("]")) {
            field.removeSurrounding("[", "]")
                .split(",")
                .map { it.trim().toFloatOrNull() ?: 0.0f }
        } else {
            listOf(field.trim().toFloatOrNull() ?: 0.0f)
        }
    }

}