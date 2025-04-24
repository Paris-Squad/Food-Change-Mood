package org.example.presentaion.presenter

import domain.model.Meal
import org.example.domain.usecase.GetMealWithHighCaloriesUseCase
import org.example.presentaion.presenter.io.Printer

class MealWithHighCaloriesPresenter(
    private val getMealsWithHighCalories: GetMealWithHighCaloriesUseCase, printer: Printer
) : BasePresenter(printer) {

    init {
        getMealsWithHighCalories()
    }

    var randomMealWithHighCalories: Meal? = null

    fun getMealsWithHighCalories() {
        val mealsWithHighCaloriesResult = getMealsWithHighCalories(REQUIRED_CALORIES)
        mealsWithHighCaloriesResult.fold(
            onSuccess = ::onGetMealWithHighCaloriesSuccess, onFailure = ::handleException
        )
    }

    companion object {
        const val REQUIRED_CALORIES = 700f
    }

    fun onGetMealWithHighCaloriesSuccess(meal: Meal) {
        randomMealWithHighCalories = meal
        printer.displayLn("Meal name is: ${meal.mealName}")
        printer.displayLn("Meal description is: ${meal.description}")
    }
}



