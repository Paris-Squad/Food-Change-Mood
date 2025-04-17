package org.example.data

import org.example.domain.repository.FoodRepository
import org.example.model.Meal

class CsvFoodRepository(
    private val fileReader: CsvFileReader,
    private val foodCsvFileParser: MealCsvFileParser
) : FoodRepository {

    private val meals: List<Meal> by lazy {
        foodCsvFileParser.parseMeals(
            fileReader.readLinesFromFile()
        )
    }

    override fun getFood(): List<Meal> = meals


    companion object {
        const val FILE_NAME = "food.csv"
    }
}