package presentaion.presenter

import com.google.common.truth.Truth.assertThat
import utils.MockMeals
import io.mockk.*
import org.example.domain.MealException
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.example.presentaion.presenter.EggFreeSweetsPresenter
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EggFreeSweetsPresenterTest {
    private val eggFreeSweets: GetEggFreeSweetsUseCase = mockk()
    private val printer: Printer = mockk(relaxed = true)
    private val reader: InputReader = mockk(relaxed = true)
    private lateinit var eggFreeSweetsPresenter: EggFreeSweetsPresenter

    @BeforeEach
    fun setUp() {
        eggFreeSweetsPresenter = EggFreeSweetsPresenter(eggFreeSweets, printer, reader)
    }

    @Test
    fun `should display meal name when use case returns success`() {
        every { eggFreeSweets.getRandomEggFreeSweet() } returns Result.success(MockMeals.eggFreeSweet1)
        every { reader.readString() } returns "Y"
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        eggFreeSweetsPresenter.startSuggestions()

        assertThat(capturedStrings).contains("We suggest: ${MockMeals.eggFreeSweet1.mealName}")
    }

    @Test
    fun `should display meal description when use case returns success`() {
        every { eggFreeSweets.getRandomEggFreeSweet() } returns Result.success(MockMeals.eggFreeSweet1)
        every { reader.readString() } returns "Y"
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        eggFreeSweetsPresenter.startSuggestions()

        assertThat(capturedStrings).contains("Description: ${MockMeals.eggFreeSweet1.description}")
    }

    @Test
    fun `should prompt user for input when meal suggestion is displayed`() {
        every { eggFreeSweets.getRandomEggFreeSweet() } returns Result.success(MockMeals.eggFreeSweet1)
        every { reader.readString() } returns "Y"
        val capturedPrompt = mutableListOf<String>()
        every { printer.display(capture(capturedPrompt)) } returns Unit

        eggFreeSweetsPresenter.startSuggestions()

        assertThat(capturedPrompt).contains("\nDo you like this suggestion? (Y/N): ")
        verify(exactly = 1) { reader.readString() }
    }

    @Test
    fun `should display second meal name when user rejects first suggestion`() {
        val testMeal1 = MockMeals.eggFreeSweet1
        val testMeal2 = MockMeals.eggFreeSweet2
        every { eggFreeSweets.getRandomEggFreeSweet() } returnsMany
                listOf(Result.success(testMeal1), Result.success(testMeal2))
        every { reader.readString() } returnsMany listOf("N", "Y")
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        eggFreeSweetsPresenter.startSuggestions()

        assertThat(capturedStrings).contains("We suggest: ${testMeal2.mealName}")
        verify(exactly = 2) { eggFreeSweets.getRandomEggFreeSweet() }
    }

    @Test
    fun `should accept suggestion when user inputs uppercase Y`() {
        every { eggFreeSweets.getRandomEggFreeSweet() } returns Result.success(MockMeals.eggFreeSweet1)
        every { reader.readString() } returns "Y"

        val result = kotlin.runCatching { eggFreeSweetsPresenter.startSuggestions() }

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `should accept suggestion when user inputs lowercase y`() {
        every { eggFreeSweets.getRandomEggFreeSweet() } returns Result.success(MockMeals.eggFreeSweet1)
        every { reader.readString() } returns "y"

        val result = kotlin.runCatching { eggFreeSweetsPresenter.startSuggestions() }

        assertThat(result.isSuccess).isTrue()
        verify(exactly = 1) { reader.readString() }
    }

    @Test
    fun `should display invalid input message when user provides invalid response`() {
        every { eggFreeSweets.getRandomEggFreeSweet() } returns Result.success(MockMeals.eggFreeSweet1)
        every { reader.readString() } returnsMany listOf("invalid", "Y")
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        eggFreeSweetsPresenter.startSuggestions()

        assertThat(capturedStrings).contains("Invalid input.")
    }

    @Test
    fun `should display error message when use case returns failure`() {
        val exception = MealException.NoMealsFoundException("No egg-free sweets found")
        every { eggFreeSweets.getRandomEggFreeSweet() } returns Result.failure(exception)
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        eggFreeSweetsPresenter.startSuggestions()

        assertThat(capturedStrings).contains(exception.message)
    }
}