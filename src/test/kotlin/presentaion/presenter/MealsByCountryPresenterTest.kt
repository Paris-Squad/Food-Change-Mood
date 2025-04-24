package presentaion.presenter

import com.google.common.truth.Truth.assertThat
import domain.usecase.createMeal
import io.mockk.mockk
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.example.presentaion.presenter.MealsByCountryPresenter
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.verify
import org.example.domain.MealException

class MealsByCountryPresenterTest {

    private val mealsByCountryUseCase: GetMealsByCountryUseCase = mockk()
    private val reader: InputReader = mockk()
    private val printer: Printer = mockk(relaxed = true)
    private lateinit var presenter: MealsByCountryPresenter

    @BeforeEach
    fun setUp() {
        presenter = MealsByCountryPresenter(mealsByCountryUseCase, reader, printer)
    }

    @Test
    fun `should display meals by country when use case returns success`() {
        // Given
        val meals = listOf(
            createMeal(mealName = "Dolma", description = "Popular in Iraq", tags = listOf("iraqi")) ,
            createMeal(mealName = "Kebab", description = "Iraqi-style grilled meat", tags = listOf("grilled"))
        )
        every { reader.readString() } returns "Iraq"
        every { reader.readInt() } returns 2
        every { mealsByCountryUseCase("Iraq", 2) } returns Result.success(meals)

        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit

        // When
        presenter.getMealsByCountry()

        // Then
        verify(exactly = 1) { mealsByCountryUseCase("Iraq", 2) }
        verify(exactly = meals.size) { printer.displayLn(any()) }

    }

    @Test
    fun `should handle NoMealsFoundException and print message when use case returns failure`() {
        // Given
        every { reader.readString() } returns "Atlantis"
        every { reader.readInt() } returns 5
        every { mealsByCountryUseCase("Atlantis", 5) } returns Result.failure(MealException.NoMealsFoundException("No meals found"))

        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit

        // When
        presenter.getMealsByCountry()

        // Then
        assertThat(output).containsExactly("No meals found")
    }

}