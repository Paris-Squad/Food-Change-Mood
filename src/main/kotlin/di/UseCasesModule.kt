package org.example.di

import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.domain.usecase.GetEasyMealSuggestionUseCase
import org.example.domain.usecase.GetMealUseCase
import org.example.domain.usecase.GuessGameUseCase
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.domain.usecase.GetSeafoodMealsUseCase
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.domain.usecase.GymHelperUseCase
import org.example.domain.usecase.*
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase

val useCasesModule = module {
    single { GetEasyMealSuggestionUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetMealUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GuessGameUseCase(get()) }
    single { GymHelperUseCase(get()) }

    single {SearchMealsByAddDateUseCase(get())}
}