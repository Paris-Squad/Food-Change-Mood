package org.example.di

import org.example.domain.usecase.GetEasyFoodSuggestionUC
import org.example.domain.usecase.GetFoodUC
import org.koin.dsl.module

val useCasesModule = module {
    single { GetFoodUC(get()) }
    single { GetEasyFoodSuggestionUC(get()) }
}