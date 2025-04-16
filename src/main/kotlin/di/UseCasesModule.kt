package org.example.di

import org.example.domain.usecase.GetFoodUC
import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.example.domain.usecase.IngredientGuessUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetFoodUC(get()) }
    single { GetQuickHealthyPicksUseCase(get()) }
    single { IngredientGuessUseCase(get()) }

}