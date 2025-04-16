package org.example.di

import org.example.domain.usecase.GetEasyFoodSuggestionUseCase
import org.example.domain.usecase.GetFoodUseCase
import org.example.domain.usecase.GuessGameUseCase
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.domain.usecase.GetEasyFoodSuggestionUC
import org.example.domain.usecase.GetFoodUC
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.usecase.GetSeafoodMealsUseCase
import org.example.domain.usecase.GuessGameUC
import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
val useCasesModule = module {
    single { GetSeafoodMealsUseCase(get()) }
    single { GetFoodUseCase(get()) }
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }

    single { GetFoodUC(get()) }
    single{GetMealsByCountryUseCase(get())}
    single { GetIraqiMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GetEasyFoodSuggestionUC(get()) }
    single { GuessGameUC(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetQuickHealthyPicksUseCase(get()) }

}