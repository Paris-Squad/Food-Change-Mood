package org.example.presentaion.presenter.guessPreparationTime

interface GuessPreparationTimeGameView {
    fun displayGameTitle()
    fun displayMealToGuess(mealName: String)
    fun promptForGuess(attemptsLeft: Int)
    fun displayCorrectGuess(mealName: String?, actualTime: Int)
    fun displayTooLowGuess(attemptsLeft: Int)
    fun displayTooHighGuess(attemptsLeft: Int)
    fun displayGameOver(actualTime: Int)
    fun displayError(message: String?)
    fun readGuessTime(): Int?
}