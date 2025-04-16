package org.example.di

import org.example.presentaion.GetEasyFoodSuggestionUi
import org.example.presentaion.GetFoodUi
import org.example.presentaion.GuessGameUi
import org.example.presentaion.GetSeafoodMealsUI
import org.example.presentaion.IraqiMealsUi
import org.example.presentaion.GymHelperUI
import org.koin.dsl.module

val uiModule = module {
    single { GetFoodUi(get()) }
    single { GetSeafoodMealsUI(get()) }
    single { GuessGameUi(get()) }
    single { GetFoodUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single {IraqiMealsUi(get())  }
    single { GymHelperUI(get()) }
}
