package org.example.data

import org.example.domain.repository.MealRepository
import domain.model.Meal

class CsvMealsRepository(
    private val fileReader: CsvFileReader,
    private val mealCsvFileParser: MealCsvFileParser
) : MealRepository {

    private val _meals: List<Meal> by lazy {
        mealCsvFileParser.parseMeals(
            fileReader.readLinesFromFile()
        )
    }

    override fun getMeals(): List<Meal> = _meals


    companion object {
        const val FILE_NAME = "food.csv"
    }
}