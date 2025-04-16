package org.example.di

import org.example.presentaion.*
import org.example.presentation.RandomPotatoMealsUi
import org.example.presentaion.GetEasyFoodSuggestionUi
import org.example.presentaion.EggFreeSweetsUi
import org.example.presentaion.GetFoodUi
import org.example.presentaion.GuessGameUi
import org.example.presentaion.GetSeafoodMealsUI
import org.example.presentaion.GetIraqiMealsUi
import org.example.presentaion.*
import org.koin.dsl.module

val uiModule = module {
    single { EggFreeSweetsUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single { GetFoodUi(get()) }
    single {GetIraqiMealsUi(get())  }
    single { GetMealsByCountryUi(get()) }
    single { GetSeafoodMealsUI(get()) }
    single { GuessGameUi(get()) }
    single { RandomPotatoMealsUi(get()) }
    single { GetFoodUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single { EggFreeSweetsUi(get()) }
    single {SearchMealsByAddDateUi(get())}
}
