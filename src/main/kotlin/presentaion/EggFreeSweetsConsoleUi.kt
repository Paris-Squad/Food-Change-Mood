package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.utils.formatDetails

class EggFreeSweetsConsoleUi(private val useCase: GetEggFreeSweetsUseCase) {
    private enum class UserAction { ACCEPT, REJECT, QUIT, INVALID }

    fun startSuggestions() {
        println("--- EGG-FREE SWEETS SUGGESTER ---")
        println("Finding egg-free sweet options for users with allergies")

        var continueSearching = true
        var showNewSuggestion = true

        while (continueSearching) {
            useCase.getRandomEggFreeSweet().fold(
                onSuccess = { sweet ->
                    if (showNewSuggestion) {
                        println("\n${sweet.mealName ?: "Unnamed Sweet"}")
                        println("Description: ${sweet.description ?: "No description available"}")
                    }
                    when (askUserPreference()) {
                        UserAction.ACCEPT -> {
                            println("\nGreat choice!")
                            println(sweet.formatDetails())
                            println("\nThank you for using the Egg-Free Sweets Suggester!")
                            continueSearching = false
                        }

                        UserAction.REJECT -> {
                            println("Okay, looking for another egg-free sweet...\n")
                            showNewSuggestion = true
                        }

                        UserAction.QUIT -> {
                            println("\nThank you for using the Egg-Free Sweets Suggester!")
                            continueSearching = false
                        }

                        UserAction.INVALID -> {
                            println("Invalid input. Please enter Y (yes), N (no), or Q (quit).")
                            showNewSuggestion = false
                        }
                    }
                },
                onFailure = { error ->
                    when (error) {
                        is MealException.NoMealsFoundException -> {
                            println(error.message)
                            continueSearching = false
                        }

                        else -> {
                            println("An error occurred: ${error.message}")
                            continueSearching = false
                        }
                    }
                }
            )
        }
    }

    private fun askUserPreference(): UserAction {
        while (true) {
            print("\nDo you like this suggestion? (Y/N/Q to quit): ")
            val input = readlnOrNull()?.trim()?.uppercase()
            return when (input) {
                "Y" -> UserAction.ACCEPT
                "N" -> UserAction.REJECT
                "Q" -> UserAction.QUIT
                else -> UserAction.INVALID
            }
        }
    }
}