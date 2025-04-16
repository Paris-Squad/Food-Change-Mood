package org.example

import org.example.di.appModule
import org.example.di.useCasesModule
import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.example.presentaion.GetFoodUi
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        modules(appModule,useCasesModule)
    }

    val getQuickHealthyPicks = getKoin().get<GetQuickHealthyPicksUseCase>()

    val getFoodUi=GetFoodUi(getQuickHealthyPicks)

    getFoodUi.invoke()
}