package org.example.presentaion.presenter

import org.example.domain.usecase.GymHelperUseCase
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails


class GymHelperConsolePresenter(private val gymHelperUseCase: GymHelperUseCase, printer: Printer) :
    BasePresenter(printer) {
    fun presentGymMeals(calories: Float, protein: Float) {
        gymHelperUseCase(calories = calories, protein = protein).fold(
            onSuccess = { gymMeals ->
                gymMeals.forEach { meal -> printer.displayLn(meal.formatDetails()) }
            }, onFailure = ::handleException
        )
    }
}