package org.example.di

import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.usecase.GetEasyFoodSuggestionUseCase
import org.example.domain.usecase.GetFoodUseCase
import org.example.domain.usecase.GuessGameUseCase
import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.domain.usecase.GetSeafoodMealsUseCase
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.domain.usecase.GymHelperUseCase
import org.example.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {
    single { GetEasyFoodSuggestionUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetFoodUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetItalianLargeGroupMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GymHelperUseCase(get()) }

    single {SearchMealsByAddDateUseCase(get())}
}