package org.example.di

import org.example.presentaion.*
import org.koin.dsl.module

val uiModule = module {
    single { EggFreeSweetsConsoleUi(get()) }
    single { GetEasyFoodSuggestionConsoleUi(get()) }
    single { GetFoodConsoleUi(get()) }
    single {GetIraqiMealsConsoleUi(get())  }
    single { GetMealsByCountryConsoleUi(get()) }
    single { GetSeafoodMealsConsoleUi(get()) }
    single { GuessGameConsoleUi(get()) }
    single { RandomPotatoMealsConsoleUi(get()) }
    single { GymHelperConsoleUi(get()) }
    single { GetFoodConsoleUi(get()) }
    single { GetEasyFoodSuggestionConsoleUi(get()) }
    single { EggFreeSweetsConsoleUi(get()) }
    single {SearchMealsByAddDateConsoleUi(get())}
}
