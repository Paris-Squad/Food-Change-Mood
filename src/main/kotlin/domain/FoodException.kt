package org.example.domain


sealed class FoodException(message: String?):Exception(message) {

    class NoEasyFoodFound : FoodException("No easy food recipes found matching the criteria")
    class NoIraqiFoodFound : FoodException("No Iraqi Meals found")
    class NoMealsFoundForCountry(country: String) : FoodException("No meals found related to '$country'")

}