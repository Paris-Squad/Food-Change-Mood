package org.example
import org.example.di.appModule
import org.example.di.useCasesModule
import org.example.presentaion.GetIngredientGuessUI
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

fun main() {
    startKoin {
        modules(appModule,useCasesModule)
    }


    val  getIngredientGuessUI= getKoin().get<GetIngredientGuessUI>()

    getIngredientGuessUI.invoke()
}