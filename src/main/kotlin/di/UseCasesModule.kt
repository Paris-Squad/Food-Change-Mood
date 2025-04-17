package org.example.di

import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.usecase.GetEasyFoodSuggestionUseCase
import org.example.domain.usecase.GetFoodUseCase
import org.example.domain.usecase.GuessGameUseCase
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.domain.usecase.GetSeafoodMealsUseCase
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.domain.usecase.GetFoodUC
import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.example.domain.usecase.IngredientGuessUseCase
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase

val useCasesModule = module {
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetFoodUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetFoodUC(get()) }
    single { GetQuickHealthyPicksUseCase(get()) }
    single { IngredientGuessUseCase(get()) }

}