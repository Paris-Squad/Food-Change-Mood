package org.example.data

import kotlinx.datetime.LocalDate
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
                println("Failed to parse: ${e.message}")
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
        name = fields[0].trim(),
        id = fields[1].trim(),
        minutes = fields[2].trim().toInt(),
        contributorId = fields[3].trim(),
        submitted = LocalDate.parse(fields[4].trim()),
        tags = parseListField(fields[5]),
        nutrition = parseNutrition(fields[6]),
        numberOfSteps = fields[7].trim().toInt(),
        steps = parseListField(fields[8]),
        description = fields[9].trim(),
        ingredients = parseListField(fields[10]),
        numberOfIngredients = fields[11].trim().toInt()
    )

    private fun parseListField(field: String): List<String> {
        if (field.isBlank() ||
            field.startsWith("[").not() ||
            field.endsWith("]").not()
        ) {
            return emptyList()
        }

        return field.removeSurrounding("[", "]")
            .takeIf { it.isNotBlank() }
            ?.let { content ->
                extractListItems(content)
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

    private fun parseNutrition(field: String): Nutrition {
        val values = parseNutritionValues(field)

        return Nutrition(
            calories = values.getOrNull(0),
            totalFat = values.getOrNull(1),
            sugar = values.getOrNull(2),
            sodium = values.getOrNull(3),
            protein = values.getOrNull(4),
            saturatedFat = values.getOrNull(5),
            carbohydrates = values.getOrNull(6)
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
