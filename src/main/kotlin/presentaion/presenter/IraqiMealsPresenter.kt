package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class IraqiMealsPresenter(private val iraqiMeals: GetIraqiMealsUseCase, printer: Printer) : BasePresenter(printer) {

    fun getIraqiMeals() {
        val iraqiMealsResult = iraqiMeals.getIraqiMeals()
        iraqiMealsResult.fold(onSuccess = ::onGetIraqiMealsSuccess, onFailure = ::handleException)
    }

    private fun onGetIraqiMealsSuccess(iraqiMeals: List<Meal>) {
        printer.displayLn("--- IRAQI MEALS ---")
        iraqiMeals.forEachIndexed { index, meal ->
            printer.displayLn(meal.formatDetails())
        }
    }
}
