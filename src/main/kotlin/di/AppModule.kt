package org.example.di

import org.example.data.CsvFileReader
import org.example.data.CsvFoodRepository
import org.example.data.FakeRepositoryImpl
import org.example.data.FoodCsvFileParse
import org.example.domain.repository.FoodRepository
import org.example.presentaion.GetFoodUi
import org.example.presentaion.GetMealsByCountryUi
import org.koin.dsl.module
import java.io.File

val appModule = module {
    single { FakeRepositoryImpl() }
    single<FoodRepository> { FakeRepositoryImpl() }

    single { File(CsvFoodRepository.FILE_NAME) }
    single { CsvFileReader(get()) }
    single { FoodCsvFileParse() }
    single<FoodRepository> { CsvFoodRepository(get(), get()) }

    single { GetFoodUi(get()) }
    single { GetMealsByCountryUi(get()) }
}