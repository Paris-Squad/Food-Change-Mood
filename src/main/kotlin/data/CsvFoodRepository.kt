package org.example.data

import org.example.domain.repository.FoodRepository
import org.example.model.Food

class CsvFoodRepository(
    private val fileReader: CsvFileReader,
    private val foodCsvFileParser: FoodCsvFileParse
) : FoodRepository {

    private val foods: List<Food> by lazy {
        foodCsvFileParser.parseFoods(
            fileReader.readLinesFromFile()
        )
    }

    override fun getFood(): List<Food> = foods


    companion object {
        const val FILE_NAME = "food.csv"
    }
}