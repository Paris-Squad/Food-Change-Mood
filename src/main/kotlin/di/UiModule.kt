package org.example.di

import org.example.presentaion.*
import org.koin.dsl.module

val uiModule = module {
    single { EggFreeSweetsConsoleUi(get()) }
    single { GetEasyMealSuggestionConsoleUi(get()) }
    single { GetMealConsoleUi(get()) }
    single { GetIraqiMealsConsoleUi(get())  }
    single { GetMealsByCountryConsoleUi(get()) }
    single { GetSeafoodMealsConsoleUi(get()) }
    single { GuessGameConsoleUi(get()) }
    single { RandomPotatoMealsConsoleUi(get()) }
    single { GymHelperConsoleUi(get()) }
    single { GetMealConsoleUi(get()) }
    single { GetEasyMealSuggestionConsoleUi(get()) }
    single { EggFreeSweetsConsoleUi(get()) }
    single {SearchMealsByAddDateConsoleUi(get())}
    single { GetIngredientGuessUI(get()) }
}
