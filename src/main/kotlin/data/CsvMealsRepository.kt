package org.example.data

import org.example.domain.repository.MealRepository
import org.example.model.Meal

class CsvMealsRepository(
    private val fileReader: CsvFileReader,
    private val foodCsvFileParser: MealCsvFileParser
) : MealRepository {

    private val _meals: List<Meal> by lazy {
        foodCsvFileParser.parseMeals(
            fileReader.readLinesFromFile()
        )
    }

    override fun getMeals(): List<Meal> = _meals


    companion object {
        const val FILE_NAME = "food.csv"
    }
}