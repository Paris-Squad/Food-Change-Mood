package org.example.domain

sealed class FoodException(message: String?):Exception(message) {

    class NoEasyFoodFound : FoodException("No easy food recipes found matching the criteria")
}

