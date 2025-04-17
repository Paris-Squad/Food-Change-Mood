package org.example.di

import GetFoodUi
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
    single { GetQuickHealthyPicksUI(get()) }
    single { GymHelperUI(get()) }
    single { GetFoodUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single { EggFreeSweetsUi(get()) }
    single {SearchMealsByAddDateUi(get())}
    single { GetIngredientGuessUI(get()) }
}
