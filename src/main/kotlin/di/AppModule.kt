package org.example.di

import org.example.data.CsvFileReader
import org.example.data.CsvFoodRepository
import org.example.data.FoodCsvFileParse
import org.example.domain.repository.FoodRepository
import org.example.presentaion.GetEasyFoodSuggestionUi
import org.example.presentaion.GetFoodUi
import org.koin.dsl.module
import java.io.File

val appModule = module {
    single { File(CsvFoodRepository.FILE_NAME) }
    single { CsvFileReader(get()) }
    single { FoodCsvFileParse() }
    single<FoodRepository> { CsvFoodRepository(get(), get()) }

    single { GetFoodUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
}