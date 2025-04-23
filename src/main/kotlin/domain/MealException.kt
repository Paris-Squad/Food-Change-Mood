package org.example.domain

abstract class MealException(message: String?) : Exception(message) {

    class NoMealsFoundException(message: String) : Exception(message)
    class IllegalArgumentException(message: String) : Exception(message)
    class InvalidDateFormatException(message: String) : Exception(message)
    class NoKetoDietMealFound(message: String): MealException(message)
    class NoEnoughMealsFound(message:String): MealException(message)
}