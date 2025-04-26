package org.example.data

import domain.model.Meal
import org.example.utils.lineHasEvenOrZeroQuoteCount
import org.example.utils.lineHasOddQuoteCount

class MealCsvFileParser(private val mealBuilder: MealBuilder) {

    fun parseMeals(lines: List<String>): List<Meal> {
        val mealLines = mutableListOf<String>()
        val meals = mutableListOf<Meal>()
        val lineBuilder = StringBuilder()
        var inMultilineField = false

        lines.forEach { line ->
            inMultilineField = handleLine(line, lineBuilder, inMultilineField)

            if (lineBuilder.isNotBlank() && inMultilineField.not()) {
                val mealLine = lineBuilder.toString().trim()
                mealLines.add(mealLine)
                lineBuilder.clear()
            }
        }

        mealLines.forEach { mealLine ->
            tryParseRecipe(mealLine)?.let { meal ->
                meals.add(meal)
            }
        }

        return meals
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

    private fun tryParseRecipe(csvLine: String): Meal? {
        val fields = parseCsvLine(csvLine)
        return if (fields.size >= 12) {
            try {
                mealBuilder.buildMeal(fields)
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
}
