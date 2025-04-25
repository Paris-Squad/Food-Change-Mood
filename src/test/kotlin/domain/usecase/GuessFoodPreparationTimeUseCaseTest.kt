package domain.usecase

import com.google.common.truth.Truth.assertThat
import org.example.domain.usecase.GuessFoodPreparationTimeUseCase
import org.example.presentaion.model.GuessPreparationTimeState
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GuessFoodPreparationTimeUseCaseTest {

    private lateinit var guessFoodPreparationTimeUseCase: GuessFoodPreparationTimeUseCase

    @BeforeEach
    fun setUp() {
        guessFoodPreparationTimeUseCase = GuessFoodPreparationTimeUseCase()
    }

    @ParameterizedTest
    @CsvSource("1", "2", "3")
    fun `should return CORRECT when user guess equals preparation time`(attempts: Int) {
        val result = guessFoodPreparationTimeUseCase(30, 30, attempts)

        assertThat(result).isEqualTo(GuessPreparationTimeState.CORRECT)
    }

    @Test
    fun `should return TOO_LOW when user guess is less than preparation time`() {
        val result = guessFoodPreparationTimeUseCase(20, 30, 1)

        assertThat(result).isEqualTo(GuessPreparationTimeState.TOO_LOW)
    }

    @Test
    fun `should return TOO_HIGH when user guess is greater than preparation time`() {
        val result = guessFoodPreparationTimeUseCase(40, 30, 1)

        assertThat(result).isEqualTo(GuessPreparationTimeState.TOO_HIGH)
    }

    @Test
    fun `should return FINISHED when attempts reached maximum`() {
        val result = guessFoodPreparationTimeUseCase(20, 30, 3)

        assertThat(result).isEqualTo(GuessPreparationTimeState.FINISHED)
    }

    @Test
    fun `should return FINISHED when attempts exceed maximum`() {
        val result = guessFoodPreparationTimeUseCase(20, 30, 4)

        assertThat(result).isEqualTo(GuessPreparationTimeState.FINISHED)
    }
}