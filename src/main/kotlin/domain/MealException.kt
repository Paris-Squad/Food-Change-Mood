package org.example.domain

abstract class MealException(message: String?) : Exception(message) {

    class NoMealsFoundException(message: String = "No meals found") : Exception(message)
    class IllegalArgumentException(message: String) : Exception(message)
    class InvalidDateFormatException(message: String) : Exception(message)
}