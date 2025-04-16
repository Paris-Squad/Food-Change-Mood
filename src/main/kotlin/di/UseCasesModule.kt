package org.example.di

import org.example.domain.usecase.GetFoodUC
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetFoodUC(get()) }
    single{GetMealsByCountryUseCase(get())}
}