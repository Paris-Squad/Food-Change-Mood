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
    single { SearchMealsByAddDateConsoleUi(get())}
    single { GetIngredientGuessUI(get()) }
    single { GetQuickHealthyPicksConsoleUI(get()) }
    single { GetItalianLargeGroupMealsConsoleUi(get()) }
    single { GetMealWithHighCaloriesConsoleUi(get()) }
    single { KetoDietMealHelperConsoleUi(get()) }
    single { SearchByMealNameConsoleUI(get()) }


