package org.example

import org.example.di.appModule
import org.example.di.uiModule
import org.example.di.useCasesModule
import org.example.presentaion.EggFreeSweetsUi
import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.example.presentaion.GetQuickHealthyPicksUI
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        modules(appModule,useCasesModule,uiModule)
    }

    val eggFreeSweetsUi = getKoin().get<EggFreeSweetsUi>()
    val getQuickHealthyPicksUI = getKoin().get<GetQuickHealthyPicksUI>()

    eggFreeSweetsUi.startSuggestions()
    getQuickHealthyPicksUI.invoke()
}