package org.example.domain

sealed class MealException(message: String?) : Exception(message) {

    class NoEasyMealsFound(message: String) : MealException(message)
    class NoSeaFoodMealsFound(message: String) : MealException(message)
    class NoMoreSweetsAvailable(message: String) : MealException(message)
    class NoIraqiMealsFound : MealException("No Iraqi Meals found")
    class NoMealsFoundForCountry(country: String) : MealException("No meals found related to '$country'")
    class NoMealsFoundException(message: String) : Exception(message)
    class InvalidDateFormatException(message: String) : Exception(message)

    sealed class GuessException(message: String) : MealException(message) {
        class TooLow(val attemptsLeft: Int) : GuessException("Guess too low. $attemptsLeft attempts left")
        class TooHigh(val attemptsLeft: Int) : GuessException("Guess too high. $attemptsLeft attempts left")
        class GameOver(val correctTime: Int) : GuessException("Game over. Correct time was $correctTime minutes")
        class NoMealAvailable(message: String) : GuessException(message)
    }
    class NoPotatoMealFound : MealException("No meals found containing potatoes")
}