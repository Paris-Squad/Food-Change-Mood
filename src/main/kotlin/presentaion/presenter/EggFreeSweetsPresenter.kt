package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class EggFreeSweetsPresenter(
    private val eggFreeSweets: GetEggFreeSweetsUseCase, printer: Printer, private val reader: InputReader
) : BasePresenter(printer) {

    fun startSuggestions() {
        var userAccepted = false

        while (!userAccepted) {
            val mealResult = eggFreeSweets.getRandomEggFreeSweet()
            mealResult.fold(
                onSuccess = { meal -> userAccepted = handleEggFreeSweetsSuccess(meal) },
                onFailure = {
                    handleException(it)
                    userAccepted = true
                }
            )
        }

    }

    private fun handleEggFreeSweetsSuccess(meal: Meal): Boolean {
        printer.displayLn("We suggest: ${meal.mealName}")
        printer.displayLn("Description: ${meal.description}")
        when (askUserPreference()) {
            true -> {
                print(meal.formatDetails())
                return true
            }

            false -> return false
        }
    }

    private fun askUserPreference(): Boolean {
        while (true) {
            printer.display("\nDo you like this suggestion? (Y/N): ")
            val input = reader.readString()?.trim()
            input?.let {
                return when {
                    it.equals("Y", true) -> true
                    it.equals("N", true) -> false
                    else -> {
                        printer.displayLn("Invalid input.")
                        false
                    }
                }
            }
        }
    }
}