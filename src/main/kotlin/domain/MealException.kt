package org.example.domain

abstract class MealException(message: String?) : Exception(message) {

    class NoMealsFoundException(message: String) : Exception(message)
    class InvalidDateFormatException(message: String) : Exception(message)
    class GuessTooLowException(val attemptsLeft: Int) : MealException("Guess too low. $attemptsLeft attempts left")
    class GuessTooHighException(val attemptsLeft: Int) : MealException("Guess too high. $attemptsLeft attempts left")
    class GameOver(val correctTime: Int) : MealException("Game over. Correct time was $correctTime minutes")
    class NoKetoDietMealFound(message: String): MealException(message)
}