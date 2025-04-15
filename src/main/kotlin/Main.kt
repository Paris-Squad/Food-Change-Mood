package org.example

import org.example.di.appModule
import org.example.di.useCasesModule
import org.example.presentaion.GetFoodUi
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        modules(appModule,useCasesModule)
    }

    val getFood = getKoin().get<GetFoodUi>()

    getFood.invoke()
}