package org.example.domain.usecase

import org.example.presentaion.model.GuessPreparationTimeState


class GuessFoodPreparationTimeUseCase() {
    operator fun invoke(userGuess: Int, preparationTime: Int, attempts: Int): GuessPreparationTimeState {
        return when {
            userGuess == preparationTime -> GuessPreparationTimeState.CORRECT
            attempts >= MAX_ATTEMPT -> GuessPreparationTimeState.FINISHED
            userGuess < preparationTime -> GuessPreparationTimeState.TOO_LOW
            else -> GuessPreparationTimeState.TOO_HIGH
        }
    }

    companion object {
        private const val MAX_ATTEMPT = 3
    }
}