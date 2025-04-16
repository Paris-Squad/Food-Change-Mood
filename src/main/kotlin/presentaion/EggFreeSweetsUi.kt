package org.example.presentaion

import org.example.domain.FoodException
import org.example.domain.usecase.GetEggFreeSweetsUseCase

class EggFreeSweetsUi(private val useCase: GetEggFreeSweetsUseCase) {

    fun startSuggestions() {
        println("--- EGG-FREE SWEETS SUGGESTER ---")
        println("Finding egg-free sweet options for users with allergies")

        var continueSearching = true

        while (continueSearching) {
            useCase.getRandomEggFreeSweet().fold(
                onSuccess = { sweet ->
                    println("\n${sweet.name ?: "Unnamed Sweet"}")
                    println("Description: ${sweet.description ?: "No description available"}")

                    while (true) {
                        print("\nDo you like this suggestion? (Y/N/Q to quit): ")
                        when (readln().trim().uppercase()) {
                            "Y" -> {
                                println(sweet.formatDetails())
                                continueSearching = false
                                break
                            }

                            "N" -> {
                                println("Looking for another egg-free sweet...")
                                break
                            }

                            "Q" -> {
                                println("Thank you for using the Egg-Free Sweets Suggester!")
                                continueSearching = false
                                break
                            }

                            else -> println("Please enter Y (yes), N (no), or Q (quit)")
                        }
                    }
                },
                onFailure = { error ->
                    when (error) {
                        is FoodException.NoMoreSweetsAvailable -> {
                            println("No more egg-free sweets available to suggest.")
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

}