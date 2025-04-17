package org.example.di

import org.example.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GetFoodUseCase(get()) }
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetItalianLargeGroupMealsUseCase(get()) }


}