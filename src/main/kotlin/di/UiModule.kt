package org.example.di

import org.example.presentaion.GetFoodUi
import org.example.presentaion.GuessGameUi
import org.koin.dsl.module

val uiModule = module {
    single { GetFoodUi(get()) }
    single { GuessGameUi(get()) }
}
