package org.example.di

import org.example.domain.usecase.GetFoodUC
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
val useCasesModule = module {
    single { GetFoodUC(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
}