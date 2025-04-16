package org.example.di

import org.example.presentaion.*
import org.example.presentation.RandomPotatoMealsUi
import org.koin.dsl.module

val uiModule = module {
    single { GetFoodUi(get()) }
    single { GetSeafoodMealsUI(get()) }
    single { GuessGameUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single {GetIraqiMealsUi(get())  }
    single { EggFreeSweetsUi(get()) }
    single { GetMealsByCountryUi(get()) }
    single { RandomPotatoMealsUi(get()) }
}
