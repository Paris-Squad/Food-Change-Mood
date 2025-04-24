package presentaion.presenter

import com.google.common.truth.Truth.assertThat
import domain.usecase.MockMeals
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.domain.MealException
import org.example.domain.usecase.GetEasyMealSuggestionUseCase
import org.example.presentaion.presenter.EasyMealSuggestionPresenter
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class GetEasyMealSuggestionPresenterTest {
    private lateinit var getEasyMealSuggestionUseCase: GetEasyMealSuggestionUseCase
    private val printer: Printer =mockk(relaxed = true)
    private lateinit var presenter: EasyMealSuggestionPresenter

    @BeforeEach
    fun setUp() {
        getEasyMealSuggestionUseCase = mockk()
        presenter = EasyMealSuggestionPresenter(getEasyMealSuggestionUseCase, printer = printer)
    }

    @Test
    fun `should display meals when use case returns success`() {
        val easyMeals = MockMeals.easyMeals
        every { getEasyMealSuggestionUseCase() } returns Result.success(easyMeals)
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        presenter.startEasyMeals()

        verify(exactly = 1) { getEasyMealSuggestionUseCase() }
        verify(exactly = 1) { printer.displayLn("EASY MEAL SUGGESTIONS") }
        easyMeals.forEach { meal ->
            verify(exactly = 1) { printer.displayLn(meal.formatDetails()) }
        }
        assertThat(capturedStrings).containsExactlyElementsIn(listOf("EASY MEAL SUGGESTIONS") + easyMeals.map { it.formatDetails() })
            .inOrder()
    }

    @Test
    fun `should handle exception when use case returns failure`() {
        val exception = MealException.NoMealsFoundException("No easy food recipes found matching the criteria")
        every { getEasyMealSuggestionUseCase() } returns Result.failure(exception)
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        presenter.startEasyMeals()

        verify(exactly = 1) { getEasyMealSuggestionUseCase() }
        verify(exactly = 1) { printer.displayLn(exception.message) }
        verify(exactly = 0) { printer.displayLn("EASY MEAL SUGGESTIONS") }
        assertThat(capturedStrings).containsExactly(exception.message)
    }

    @Test
    fun `should display exactly 10 meals when use case returns more than 10 meals`() {
        val manyMeals = MockMeals.manyEasyMeals.take(10)
        every { getEasyMealSuggestionUseCase() } returns Result.success(manyMeals)
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        presenter.startEasyMeals()

        verify(exactly = 1) { getEasyMealSuggestionUseCase() }
        verify(exactly = 1) { printer.displayLn("EASY MEAL SUGGESTIONS") }
        manyMeals.forEach { meal ->
            verify(exactly = 1) { printer.displayLn(meal.formatDetails()) }
        }
        verify(exactly = 11) { printer.displayLn(any()) } // Title + 10 meals
        assertThat(capturedStrings).containsExactlyElementsIn(listOf("EASY MEAL SUGGESTIONS") + manyMeals.map { it.formatDetails() })
            .inOrder()
    }
}