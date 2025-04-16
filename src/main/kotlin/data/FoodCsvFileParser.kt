package org.example.data

import kotlinx.datetime.LocalDate
import org.example.data.utils.ColumnIndex
import org.example.data.utils.NutritionIndex
import org.example.model.Food
import org.example.model.Nutrition
import org.example.utils.lineHasEvenOrZeroQuoteCount
import org.example.utils.lineHasOddQuoteCount

class FoodCsvFileParse {

    fun parseFoods(lines: List<String>): List<Food> {
        val foodLines = mutableListOf<String>()
        val foods = mutableListOf<Food>()
        val lineBuilder = StringBuilder()
        var inMultilineField = false

        lines.forEach { line ->
            inMultilineField = handleLine(line, lineBuilder, inMultilineField)

            if (inMultilineField.not() && lineBuilder.isNotBlank()) {
                val foodLine = lineBuilder.toString().trim()
                foodLines.add(foodLine)
                lineBuilder.clear()
            }
        }

        foodLines.forEach { foodLine ->
            tryParseRecipe(foodLine)?.let { food ->
                foods.add(food)
            }
        }

        return foods
    }

    private fun handleLine(
        line: String,
        lineBuilder: StringBuilder,
        inMultiline: Boolean
    ): Boolean {
        return if (inMultiline) {
            lineBuilder.append("\n$line")
            line.lineHasEvenOrZeroQuoteCount()
        } else {
            lineBuilder.append(line)
            line.lineHasOddQuoteCount()
        }
    }

    private fun tryParseRecipe(csvLine: String): Food? {
        val fields = parseCsvLine(csvLine)
        return if (fields.size >= 12) {
            try {
                createRecipe(fields)
            } catch (e: Exception) {
                null
            }
        } else null
    }

    private fun parseCsvLine(line: String): List<String> {
        val result = mutableListOf<String>()
        val currentField = StringBuilder()
        var inQuotes = false
        var isLastCharQuotes = false

        line.forEach { char ->
            when {
                char == '"' -> {
                    if (isLastCharQuotes.not()) {
                        isLastCharQuotes = true
                    }
                    currentField.append(char)
                    inQuotes = !inQuotes
                }

                char == ',' && !inQuotes -> {
                    isLastCharQuotes = false
                    result.add(currentField.toString())
                    currentField.clear()
                }

                else -> {
                    isLastCharQuotes = false
                    currentField.append(char)
                }
            }
        }

        result.add(currentField.toString())
        return result
    }

    private fun createRecipe(fields: List<String>) = Food(
        name = fields[ColumnIndex.NAMES].trim(),
        id = fields[ColumnIndex.ID].trim(),
        minutes = fields[ColumnIndex.MINUTES].trim().toInt(),
        contributorId = fields[ColumnIndex.CONTRIBUTOR_ID].trim(),
        submitted = LocalDate.parse(fields[ColumnIndex.SUBMITTED_DATE].trim()),
        tags = parseListField(fields[ColumnIndex.TAGS]),
        nutrition = parseNutrition(fields[ColumnIndex.NUTRITION]),
        numberOfSteps = fields[ColumnIndex.NUMBER_OF_STEPS].trim().toInt(),
        steps = parseListField(fields[ColumnIndex.STEPS]),
        description = fields[ColumnIndex.DESCRIPTION].trim(),
        ingredients = parseListField(fields[ColumnIndex.INGREDIENTS]),
        numberOfIngredients = fields[ColumnIndex.NUMBER_OF_INGREDIENTS].trim().toInt()
    )

    private fun parseListField(listField: String): List<String> {
        var content = listField
        val quote = "\""
        while (content.startsWith(quote) && content.endsWith(quote)){
            content = content.removeSurrounding(quote, quote).trim()
        }

        if (content.isBlank() ||
            content.startsWith("[").not() ||
            content.endsWith("]").not()
        ) {
            return emptyList()
        }

        return content.removeSurrounding("[", "]")
            .takeIf { it.isNotBlank() }
            ?.let {
                extractListItems(it)
            } ?: emptyList()
    }

    private fun extractListItems(content: String): List<String> {
        val result = mutableListOf<String>()
        val stringBuilder = StringBuilder()
        var inQuotes = false

        content.forEach { char ->
            when {
                char == '\'' -> {
                    inQuotes = !inQuotes
                    if (!inQuotes && stringBuilder.isNotEmpty()) {
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
        var content = nutritionFiled
        val quote = "\""
        while (content.startsWith(quote) && content.endsWith(quote)){
            content = content.removeSurrounding(quote, quote).trim()
        }
        val values = parseNutritionValues(content)

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
