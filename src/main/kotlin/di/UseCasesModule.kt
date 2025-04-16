package org.example.di

import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.usecase.GetEasyFoodSuggestionUseCase
import org.example.domain.usecase.GetFoodUseCase
import org.example.domain.usecase.GuessGameUseCase
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.domain.usecase.GetSeafoodMealsUseCase
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
val useCasesModule = module {
    single { GetSeafoodMealsUseCase(get()) }
    single { GetFoodUseCase(get()) }
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }

}