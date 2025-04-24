package org.example.presentaion.presenter

import org.example.domain.MealException
import org.example.presentaion.presenter.io.Printer

abstract class BasePresenter(protected val printer: Printer) {
    protected fun handleException(error: Throwable) {
        when (error) {
            is MealException.NoMealsFoundException -> {
                printer.displayLn(error.message)
            }

            is MealException.IllegalArgumentException -> {
                printer.displayLn(error.message)
            }

            is MealException.InvalidDateFormatException -> {
                printer.displayLn("Invalid date format: ${error.message}")
            }

            is MealException.NoEnoughMealsFound -> {
                printer.displayLn(error.message)
            }

            else -> {
                printer.displayLn("--- UNEXPECTED ERROR ---")
                printer.displayLn("An unexpected error occurred: ${error.message}")
            }
        }
    }
}