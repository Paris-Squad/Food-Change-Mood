package org.example.di

import org.example.data.FakeRepositoryImpl
import org.example.domain.repository.FoodRepository
import org.example.presentaion.GetFoodUi
import org.example.presentaion.GetMealsByCountryUi
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
import java.io.File

val appModule = module {
    single { File(CsvFoodRepository.FILE_NAME) }
    single { CsvFileReader(get()) }
    single { FoodCsvFileParse() }
    single<FoodRepository> { CsvFoodRepository(get(), get()) }

    single { GetFoodUi(get()) }
    single { GetMealsByCountryUi(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
}