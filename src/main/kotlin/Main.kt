package org.example

import org.example.di.appModule
import org.example.di.uiModule
import org.example.di.useCasesModule
import org.example.presentaion.MealConsoleUI
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        modules(appModule, useCasesModule, uiModule)
    }

    val mealConsoleUI = getKoin().get<MealConsoleUI>()
    mealConsoleUI.start()
}