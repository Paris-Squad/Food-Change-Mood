package org.example.di

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
    single { GetFoodUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single {IraqiMealsUi(get())  }
    single { EggFreeSweetsUi(get()) }
    single { GetItalianLargeGroupMealsUi(get()) }

    single { RandomPotatoMealsUi(get()) }
    single { GymHelperUI(get()) }
    single { GetFoodUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single { EggFreeSweetsUi(get()) }
    single {SearchMealsByAddDateUi(get())}
}
