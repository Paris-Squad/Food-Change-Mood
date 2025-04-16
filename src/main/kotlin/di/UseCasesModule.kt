package org.example.di

import org.example.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {
    single { GetSeafoodMealsUseCase(get()) }
    single { GetFoodUseCase(get()) }
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single {SearchMealsByAddDateUseCase(get())}
}