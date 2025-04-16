package org.example.domain

sealed class FoodException(message: String?) : Exception(message) {
    class NoPotatoMealFound : FoodException("No meals found containing potatoes")
}