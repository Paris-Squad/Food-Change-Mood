package org.example.di

import org.example.domain.usecase.GetFoodUC
import org.example.domain.usecase.GuessGameUC
import org.koin.dsl.module

val useCasesModule = module {
    single { GetFoodUC(get()) }
    single { GuessGameUC(get()) }
}