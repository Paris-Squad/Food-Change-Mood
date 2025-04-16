package org.example.di

import org.example.domain.usecase.GetEasyFoodSuggestionUseCase
import org.example.domain.usecase.GetFoodUseCase
import org.example.domain.usecase.GuessGameUseCase
import org.example.domain.usecase.GetEasyFoodSuggestionUC
import org.example.domain.usecase.GetFoodUC
import org.example.domain.usecase.GuessGameUC
import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
val useCasesModule = module {
    single { GetFoodUseCase(get()) }
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetFoodUC(get()) }
    single { GetEasyFoodSuggestionUC(get()) }
    single { GuessGameUC(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetQuickHealthyPicksUseCase(get()) }

}