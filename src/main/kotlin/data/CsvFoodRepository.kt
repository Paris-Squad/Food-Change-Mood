package org.example.data

import org.example.domain.repository.FoodRepository
import org.example.model.Food

class CsvFoodRepository(
    private val fileReader: CsvFileReader,
    private val foodCsvFileParser: FoodCsvFileParse
) : FoodRepository {

    private var foods = listOf<Food>()
    override fun getFood(): List<Food> {
        if (foods.isEmpty()) {
            foods = foodCsvFileParser.parseFoods(
                fileReader.readLinesFromFile()
            )
        }
        return foods
    }

    companion object {
        const val FILE_NAME = "food.csv"
    }
}