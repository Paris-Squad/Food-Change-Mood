package org.example.di

import org.example.domain.usecase.GetEasyFoodSuggestionUseCase
import org.example.domain.usecase.GetFoodUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetFoodUseCase(get()) }
    single { GetEasyFoodSuggestionUseCase(get()) }
}