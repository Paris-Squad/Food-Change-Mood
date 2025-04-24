package org.example.di

import org.example.data.CsvFileReader
import org.example.data.CsvMealsRepository
import org.example.data.MealCsvFileParser
import org.example.domain.repository.MealRepository
import org.example.presentaion.presenter.io.ConsolePrinter
import org.example.presentaion.presenter.io.ConsoleReader
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.koin.dsl.module
import java.io.File

val appModule = module {

    single { File(CsvMealsRepository.FILE_NAME) }
    single { CsvFileReader(get()) }
    single { MealCsvFileParser() }
    single<MealRepository> { CsvMealsRepository(get(), get()) }
    single<Printer> { ConsolePrinter() }
    single<InputReader> { ConsoleReader() }
}