package presentaion.presenter

import com.google.common.truth.Truth
import domain.usecase.MockMeals
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.domain.usecase.GetRandomMealUseCase
import org.example.domain.usecase.GuessFoodPreparationTimeUseCase
import org.example.presentaion.model.GuessPreparationTimeState
import org.example.presentaion.presenter.guessPreparationTime.GuessPreparationTimeGamePresenter
import org.example.presentaion.presenter.guessPreparationTime.GuessPreparationTimeGameView
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GuessPreparationTimeGamePresenterTest {
    private lateinit var presenter: GuessPreparationTimeGamePresenter
    private val randomMealUseCase: GetRandomMealUseCase = mockk()
    private val guessFoodPreparationTimeUseCase: GuessFoodPreparationTimeUseCase = mockk()
    private val view: GuessPreparationTimeGameView = mockk(relaxed = true)
    private val testMeal = MockMeals.easyMeal1

    @BeforeEach
    fun setUp() {
        presenter = GuessPreparationTimeGamePresenter(randomMealUseCase, guessFoodPreparationTimeUseCase, view)
    }

    @Test
    fun `startGame should handle error when fetching random meal fails`() {
        val errorMessage = "Failed to fetch random meal"
        every { randomMealUseCase.invoke() } returns Result.failure(RuntimeException(errorMessage))

        presenter.startGame()

        verify(exactly = 1) { view.displayGameTitle() }
        verify(exactly = 1) { view.displayError(errorMessage) }
        verify(exactly = 0) { view.displayMealToGuess(any()) }
        Truth.assertThat(presenter.isGameActive()).isFalse()
    }

    @Test
    fun `processGuess should display error message when guess is null`() {
        every { randomMealUseCase.invoke() } returns Result.success(testMeal)
        presenter.startGame()
        every { view.readGuessTime() } returns null

        presenter.processGuess()

        verify(exactly = 1) { view.displayError("Please enter a valid number.") }
        Truth.assertThat(presenter.isGameActive()).isTrue()
    }

    @Test
    fun `tartGame should display displayCorrectGuess and fetch random meal when fetching random meal succeeds`() {
        every { randomMealUseCase.invoke() } returns Result.success(testMeal)
        presenter.startGame()
        val guessTime = 15
        every { view.readGuessTime() } returns guessTime
        every {
            guessFoodPreparationTimeUseCase.invoke(
                guessTime,
                testMeal.minutesForPreparation,
                0
            )
        } returns GuessPreparationTimeState.CORRECT

        presenter.processGuess()

        verify(exactly = 1) { view.displayCorrectGuess(testMeal.mealName, testMeal.minutesForPreparation) }
        verify(exactly = 0) { view.displayTooLowGuess(any()) }
        verify(exactly = 0) { view.displayTooHighGuess(any()) }
        verify(exactly = 0) { view.displayGameOver(any()) }
        Truth.assertThat(presenter.isGameActive()).isFalse()
    }

    @Test
    fun `processGuess should display too low guess with remaining attempts when guess is too low`() {
        every { randomMealUseCase.invoke() } returns Result.success(testMeal)
        presenter.startGame()
        val guessTime = 10
        every { view.readGuessTime() } returns guessTime
        every {
            guessFoodPreparationTimeUseCase.invoke(guessTime, testMeal.minutesForPreparation, 0)
        } returns GuessPreparationTimeState.TOO_LOW

        presenter.processGuess()

        verify(exactly = 1) { view.displayTooLowGuess(2) }
        verify(exactly = 1) { view.promptForGuess(2) }
        Truth.assertThat(presenter.isGameActive()).isTrue()
    }

    @Test
    fun `processGuess should display too high guess with remaining attempts when guess is too high`() {
        every { randomMealUseCase.invoke() } returns Result.success(testMeal)
        presenter.startGame()
        val guessTime = 40
        every { view.readGuessTime() } returns guessTime
        every {
            guessFoodPreparationTimeUseCase.invoke(guessTime, testMeal.minutesForPreparation, 0)
        } returns GuessPreparationTimeState.TOO_HIGH

        presenter.processGuess()

        verify { view.displayTooHighGuess(2) }
        verify { view.promptForGuess(2) }
        Truth.assertThat(presenter.isGameActive()).isTrue()
    }
}