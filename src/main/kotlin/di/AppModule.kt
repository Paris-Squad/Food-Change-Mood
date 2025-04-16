package org.example.di


import org.example.data.CsvFileReader
import org.example.data.CsvFoodRepository
import org.example.data.FoodCsvFileParse
import org.example.domain.repository.FoodRepository
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
import java.io.File

val appModule = module {
    single { File(CsvFoodRepository.FILE_NAME) }
    single { CsvFileReader(get()) }
    single { FoodCsvFileParse() }
    single<FoodRepository> { CsvFoodRepository(get(), get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
}