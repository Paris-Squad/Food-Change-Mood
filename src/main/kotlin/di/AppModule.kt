package org.example.di

import org.example.data.CsvFileReader

import org.example.data.CsvMealsRepository
import org.example.data.MealCsvFileParser
import org.example.domain.repository.MealRepository
import org.koin.dsl.module
import java.io.File

val appModule = module {

    single { File(CsvMealsRepository.FILE_NAME) }
    single { CsvFileReader(get()) }
    single { MealCsvFileParser() }
    single<MealRepository> { CsvMealsRepository(get(), get()) }

}