package presentaion.presenter

import domain.usecase.createMeal
import org.example.domain.usecase.GetIraqiMealsUseCase
import org.example.presentaion.presenter.IraqiMealsPresenter
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.domain.MealException
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class IraqiMealsPresenterTest {
    private lateinit var iraqiMealsUseCase: GetIraqiMealsUseCase
    private val printer: Printer = mockk(relaxed = true)
    private lateinit var presenter: IraqiMealsPresenter

    @BeforeEach
    fun setUp() {
        iraqiMealsUseCase = mockk()
        presenter = IraqiMealsPresenter(iraqiMealsUseCase, printer = printer)
    }

    @Test
    fun `should display iraqi meals when use case returns success`() {
        // Given
        val meals = listOf(
            createMeal(mealName = "Dolma", "Stuffed grape leaves", tags =  listOf("iraqi")),
            createMeal(mealName ="Kebab", "Grilled meat", tags =  listOf("iraqi", "grilled"))
        )
        every { iraqiMealsUseCase.getIraqiMeals() } returns Result.success(meals)

        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit

        // When
        presenter.getIraqiMeals()

        // Then
        verify(exactly = 1) { iraqiMealsUseCase.getIraqiMeals() }
        verify(exactly = meals.size + 1) { printer.displayLn(any()) }

        assertThat(output).containsExactly(
            "--- IRAQI MEALS ---",
            meals[0].formatDetails(),
            meals[1].formatDetails()
        ).inOrder()
    }

    @Test
    fun `should handle NoMealsFoundException and display the message`() {
        // Given
        every { iraqiMealsUseCase.getIraqiMeals() } returns Result.failure(MealException.NoMealsFoundException("No Iraqi meals found"))

        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit

        // When
        presenter.getIraqiMeals()

        // Then
        verify { printer.displayLn("No Iraqi meals found") }
        assertThat(output).containsExactly("No Iraqi meals found")
    }

}