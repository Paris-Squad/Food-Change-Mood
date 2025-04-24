package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetKetoDietMealUseCase
import org.example.presentaion.presenter.io.Printer

class KetoDietMealHelperPresenter(private val getKetoDietMeal: GetKetoDietMealUseCase, printer: Printer) :
    BasePresenter(printer) {
    private val repeatedMeals = mutableSetOf<Meal>()

    fun startKetoHelper() {
        printer.displayLn("click 1 if you want a suggested keto diet meal.")
        printer.displayLn("click 2 to exit.")
        val pickedChoice = readln()
        when (pickedChoice.trim()) {
            SUGGEST_KETO_MEAL_OPTION -> {
                getKetoDietMeal.getSuggestedKetoMeal(repeatedMeals).fold(
                    onSuccess = ::handleSuccess, onFailure = ::handleException
                )
            }

            EXIT_OPTION -> return
            else -> println("you need to pick a valid choice")
        }
    }

    private fun handleSuccess(meal: Meal) {
        repeatedMeals.add(meal)
        printer.displayLn("\nSuggested Keto Meal:")
        printer.displayLn("Name: ${meal.mealName}")
        printer.displayLn("Description: ${meal.description ?: "No description available"}")
        printer.displayLn("Preparation Time: ${meal.minutesForPreparation} minutes")
        printer.displayLn("\nNutrition Info:")
        printer.displayLn("Carbs: ${meal.nutrition.carbohydrates}g")
        printer.displayLn("Fat: ${meal.nutrition.totalFat}g")
        printer.displayLn("Protein: ${meal.nutrition.protein}g")
    }

    companion object {
        private const val SUGGEST_KETO_MEAL_OPTION = "1"
        private const val EXIT_OPTION = "2"
    }
}