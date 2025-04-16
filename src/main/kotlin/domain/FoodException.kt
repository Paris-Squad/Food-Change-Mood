package org.example.domain

sealed class FoodException(message: String?) : Exception(message) {

    class NoEasyFoodFound(message: String) : FoodException(message)
    class NoSeaFoodMealsFound(message: String) : FoodException(message)
    class NoMoreSweetsAvailable(message: String) : FoodException(message)
    class NoIraqiFoodFound : FoodException("No Iraqi Meals found")

    sealed class GuessException(message: String) : FoodException(message) {
        class TooLow(val attemptsLeft: Int) : GuessException("Guess too low. $attemptsLeft attempts left")
        class TooHigh(val attemptsLeft: Int) : GuessException("Guess too high. $attemptsLeft attempts left")
        class GameOver(val correctTime: Int) : GuessException("Game over. Correct time was $correctTime minutes")
        class NoFoodAvailable(message: String) : GuessException(message)
    }
sealed class FoodException(message: String?) : Exception(message) {
    class NoPotatoMealFound : FoodException("No meals found containing potatoes")
}
    }

