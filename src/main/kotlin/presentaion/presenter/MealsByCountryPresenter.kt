package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class MealsByCountryPresenter(
    private val mealsByCountryUseCase: GetMealsByCountryUseCase, private val reader: InputReader, printer: Printer
) : BasePresenter(printer) {

    fun getMealsByCountry() {
        val country = getCountryInput()
        val count = getCountInput()

        mealsByCountryUseCase(country, count).fold(
            onSuccess = ::onGetMealsByCountrySuccess, onFailure = ::handleException
        )
    }

    private fun getCountryInput(): String? {
        printer.display("Enter a country mealName: ")
        return reader.readString()
    }

    private fun getCountInput(): Int? {
        printer.display("Enter number of meals to display: ")
        return reader.readInt()
    }

    private fun onGetMealsByCountrySuccess(meals: List<Meal>) {
        meals.forEachIndexed { index, meal ->
            printer.displayLn(meal.formatDetails())
        }
    }
}
