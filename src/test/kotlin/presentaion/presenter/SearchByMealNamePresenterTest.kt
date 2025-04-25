package presentaion.presenter

import io.mockk.mockk
import org.example.domain.usecase.SearchByMealNameUseCase
import org.example.presentaion.presenter.SearchByMealNamePresenter
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.junit.jupiter.api.BeforeEach
import domain.usecase.createMeal
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat
import org.example.domain.MealException
import io.mockk.mockkStatic

class SearchByMealNamePresenterTest {

    private lateinit var mealsByNameUseCase: SearchByMealNameUseCase
    private val printer: Printer = mockk(relaxed = true)
    private val reader: InputReader = mockk(relaxed = true)
    private lateinit var presenter: SearchByMealNamePresenter

    @BeforeEach
    fun setUp() {
        mealsByNameUseCase = mockk()
        presenter = SearchByMealNamePresenter(searchByMealName = mealsByNameUseCase, printer = printer,reader = reader)
    }

    @Test
    fun `should display meals when meals by name use case returns success`() {
        // Given
        val meals = listOf(
            createMeal(mealName = "Kebab") ,
            createMeal(mealName = "koba")
        )

        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit
        every { printer.display(any()) } returns Unit

        every { reader.readString() } returnsMany listOf("keb", "1")
        every { mealsByNameUseCase.invoke("keb") } returns Result.success(meals)

        // When
        presenter.startSearchByName()

        // Then
        assertThat(output).containsAtLeast("1. Kebab", "2. koba")
        verify(exactly = 1) { mealsByNameUseCase.invoke("keb") }

    }


    @Test
    fun `should display no meals found when meals by name use case throws exception`() {
        // Given
        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit
        every { printer.display(any()) } returns Unit

        every { reader.readString() } returnsMany listOf("pizza", "1")
        every { mealsByNameUseCase.invoke("pizza") } returns Result.failure(MealException.NoMealsFoundException("No meals found matching 'pizza'."))

        // When
        presenter.startSearchByName()

        // Then
        assertThat(output).contains("No meals found matching 'pizza'.")
        verify(exactly = 1) { mealsByNameUseCase.invoke("pizza") }
    }

    @Test
    fun `should handle invalid meal selection`() {
        // Given
        val meals = listOf(
            createMeal(mealName = "Kebab"),
            createMeal(mealName = "koba")
        )

        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit
        every { printer.display(any()) } returns Unit

        every { reader.readString() } returns "keb"
        every { mealsByNameUseCase.invoke("keb") } returns Result.success(meals)

        mockkStatic("kotlin.io.ConsoleKt")
        every { readlnOrNull() } returns "3"

        // When
        presenter.startSearchByName()

        // Then
        assertThat(output).contains("Invalid selection.")
        verify(exactly = 1) { mealsByNameUseCase.invoke("keb") }
        verify(atLeast = 1) { printer.displayLn("Invalid selection.") }
    }

    @Test
    fun `should display message when search term is empty`() {
        // Given
        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit
        every { printer.display(any()) } returns Unit

        every { reader.readString() } returns ""

        // When
        presenter.startSearchByName()

        // Then
        assertThat(output).contains("Please enter a valid search term.")
        verify(exactly = 3) { printer.displayLn(any()) }
    }


}