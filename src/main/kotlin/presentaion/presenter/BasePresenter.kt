package org.example.presentaion.presenter

import org.example.domain.MealException

abstract class BasePresenter {

    protected fun handleException(error: Throwable) {
        when (error) {
            is MealException.NoMealsFoundException -> {
                println(error.message)
            }

            is MealException.IllegalArgumentException -> {
                println(error.message)
            }

            is MealException.InvalidDateFormatException -> {
                println("Invalid date format: ${error.message}")
            }

            else -> {
                println("--- UNEXPECTED ERROR ---")
                println("An unexpected error occurred: ${error.message}")
            }
        }
    }
}