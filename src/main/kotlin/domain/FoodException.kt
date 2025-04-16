package org.example.domain

sealed class FoodException(message: String?):Exception(message) {

    class NoEasyFoodFound : FoodException("No easy food recipes found matching the criteria")
    class NoSeaFoodMealsFound(message: String) : FoodException(message)

    sealed class GuessException(message: String) : FoodException(message) {
        class TooLow(val attemptsLeft: Int) : GuessException("Guess too low. $attemptsLeft attempts left")
        class TooHigh(val attemptsLeft: Int) : GuessException("Guess too high. $attemptsLeft attempts left")
        class GameOver(val correctTime: Int) : GuessException("Game over. Correct time was $correctTime minutes")
        class NoFoodAvailable:GuessException("No food Available.")
    }
}

