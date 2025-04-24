package org.example.presentaion.presenter

import org.example.domain.usecase.GymHelperUseCase
import org.example.utils.formatDetails


class GymHelperConsolePresenter(private val gymHelperUseCase: GymHelperUseCase) : BasePresenter() {
    fun start(calories: Float, protein: Float) {
        gymHelperUseCase(calories = calories, protein = protein).fold(
            onSuccess = { gymMeals ->
                gymMeals.forEach { meal -> println(meal.formatDetails()) }
            },
            onFailure = ::handleException
        )
    }
}