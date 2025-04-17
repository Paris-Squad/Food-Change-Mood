package org.example.di

import org.example.presentaion.*
import org.koin.dsl.module

val uiModule = module {
    single { GetFoodUi(get()) }
    single { GetSeafoodMealsUI(get()) }
    single { GuessGameUi(get()) }
    single { GetFoodUi(get()) }
    single { GetEasyFoodSuggestionUi(get()) }
    single {IraqiMealsUi(get())  }
    single { EggFreeSweetsUi(get()) }
    single { GetItalianLargeGroupMealsUi(get()) }

}
