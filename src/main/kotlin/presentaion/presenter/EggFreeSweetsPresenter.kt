package org.example.presentaion.presenter

import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails

class EggFreeSweetsPresenter(
    private val getEggFreeSweets: GetEggFreeSweetsUseCase, printer: Printer, private val reader: InputReader
) : BasePresenter(printer) {
    private enum class UserAction { ACCEPT, REJECT, QUIT, INVALID }

    fun startSuggestions() {
        printer.displayLn("--- EGG-FREE SWEETS SUGGESTER ---")
        printer.displayLn("Finding egg-free sweet options for users with allergies")

        var continueSearching = true
        var showNewSuggestion = true

        while (continueSearching) {
            getEggFreeSweets.getRandomEggFreeSweet().fold(
                onSuccess = { sweet ->
                    if (showNewSuggestion) {
                        printer.displayLn("\n${sweet.mealName ?: "Unnamed Sweet"}")
                        printer.displayLn("Description: ${sweet.description ?: "No description available"}")
                    }
                    when (askUserPreference()) {
                        UserAction.ACCEPT -> {
                            printer.displayLn("\nGreat choice!")
                            printer.displayLn(sweet.formatDetails())
                            printer.displayLn("\nThank you for using the Egg-Free Sweets Suggester!")
                            continueSearching = false
                        }

                        UserAction.REJECT -> {
                            printer.displayLn("Okay, looking for another egg-free sweet...\n")
                            showNewSuggestion = true
                        }

                        UserAction.QUIT -> {
                            printer.displayLn("\nThank you for using the Egg-Free Sweets Suggester!")
                            continueSearching = false
                        }

                        UserAction.INVALID -> {
                            printer.displayLn("Invalid input. Please enter Y (yes), N (no), or Q (quit).")
                            showNewSuggestion = false
                        }
                    }
                }, onFailure = ::handleException
            )
        }
    }

    private fun askUserPreference(): UserAction {
        while (true) {
            printer.display("\nDo you like this suggestion? (Y/N/Q to quit): ")
            val input = reader.readString()?.trim()?.uppercase()
            return when (input) {
                "Y" -> UserAction.ACCEPT
                "N" -> UserAction.REJECT
                "Q" -> UserAction.QUIT
                else -> UserAction.INVALID
            }
        }
    }
}