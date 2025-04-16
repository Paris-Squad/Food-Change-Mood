package org.example.di

import org.example.presentaion.GetFoodUi
import org.example.presentaion.GetSeafoodMealsUI
import org.koin.dsl.module

val uiModule = module {
    single { GetFoodUi(get()) }
    single { GetSeafoodMealsUI(get()) }
}
