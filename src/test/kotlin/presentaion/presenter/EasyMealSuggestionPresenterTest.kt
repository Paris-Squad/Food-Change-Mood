package presentaion.presenter

import com.google.common.truth.Truth.assertThat
import utils.MockMeals
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


class EasyMealSuggestionPresenterTest {
    private lateinit var easyMealSuggestionUseCase: GetEasyMealSuggestionUseCase
    private val printer: Printer = mockk(relaxed = true)
    private lateinit var presenter: EasyMealSuggestionPresenter

    @BeforeEach
    fun setUp() {
        easyMealSuggestionUseCase = mockk()
        presenter = EasyMealSuggestionPresenter(easyMealSuggestionUseCase, printer = printer)
    }

    @Test
    fun `should display easy meals when easyMealSuggestionUseCase returns success`() {
        val easyMeals = MockMeals.easyMeals
        every { easyMealSuggestionUseCase() } returns Result.success(easyMeals)
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        presenter.getEasyMeals()

        verify(exactly = 1) { easyMealSuggestionUseCase() }
        verify(exactly = 1) { printer.displayLn("EASY MEAL SUGGESTIONS") }
        easyMeals.forEach { meal ->
            verify(exactly = 1) { printer.displayLn(meal.formatDetails()) }
        }
        assertThat(capturedStrings).containsExactlyElementsIn(listOf("EASY MEAL SUGGESTIONS") + easyMeals.map { it.formatDetails() })
            .inOrder()
    }

    @Test
    fun `should handle NoMealsFoundException when easyMealSuggestionUseCase returns failure`() {
        val exception = MealException.NoMealsFoundException("No easy food recipes found matching the criteria")
        every { easyMealSuggestionUseCase() } returns Result.failure(exception)
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        presenter.getEasyMeals()

        verify(exactly = 1) { easyMealSuggestionUseCase() }
        verify(exactly = 1) { printer.displayLn(exception.message) }
        verify(exactly = 0) { printer.displayLn("EASY MEAL SUGGESTIONS") }
        assertThat(capturedStrings).containsExactly(exception.message)
    }

    @Test
    fun `should display exactly 10 meals when easyMealSuggestionUseCase returns more than 10 meals`() {
        val meals = MockMeals.manyEasyMeals.take(10)
        every { easyMealSuggestionUseCase() } returns Result.success(meals)
        val capturedStrings = mutableListOf<String>()
        every { printer.displayLn(capture(capturedStrings)) } returns Unit

        presenter.getEasyMeals()

        verify(exactly = 1) { easyMealSuggestionUseCase() }
        verify(exactly = 1) { printer.displayLn("EASY MEAL SUGGESTIONS") }
        meals.forEach { meal ->
            verify(exactly = 1) { printer.displayLn(meal.formatDetails()) }
        }
        verify(exactly = 11) { printer.displayLn(any()) }
        assertThat(capturedStrings).containsExactlyElementsIn(listOf("EASY MEAL SUGGESTIONS") + meals.map { it.formatDetails() })
            .inOrder()
    }
}