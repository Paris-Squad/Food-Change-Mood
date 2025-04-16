package org.example.domain

sealed class FoodException(message: String?):Exception(message) {

    class NoSeaFoodMealsFound(message: String) : FoodException(message)
}