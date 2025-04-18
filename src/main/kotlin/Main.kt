package org.example
import org.example.di.appModule
import org.example.di.uiModule
import org.example.di.useCasesModule
import org.example.presentaion.MealConsoleUI
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(appModule,useCasesModule,uiModule)
    }

    val mealConsoleUI = MealConsoleUI()
    mealConsoleUI.start()
}