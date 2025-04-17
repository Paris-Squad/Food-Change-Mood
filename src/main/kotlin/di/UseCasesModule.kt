package org.example.di

import org.example.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetFoodUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetQuickHealthyPicksUseCase(get()) }
}