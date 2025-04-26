package presentaion.presenter

import com.google.common.truth.Truth.assertThat
import domain.usecase.createMeal
import domain.usecase.createNutrition
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.domain.MealException
import org.example.domain.usecase.GetMealWithHighCaloriesUseCase
import org.example.presentaion.presenter.MealWithHighCaloriesPresenter
import org.example.presentaion.presenter.io.Printer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MealWithHighCaloriesPresenterTest {

    private lateinit var mealWithHighCalories: GetMealWithHighCaloriesUseCase
    private lateinit var printer: Printer
    private lateinit var presenter: MealWithHighCaloriesPresenter

    @BeforeEach
    fun setup() {
        mealWithHighCalories = mockk()
        printer = mockk(relaxed = true)
        presenter = MealWithHighCaloriesPresenter(mealWithHighCalories, printer, access = false)
    }

    @Test
    fun `should display rondom meal With High Calories when use case return success`() {
        //Given
        val meal = createMeal(
            mealName = "tunisian penne",
            description = "recipe from ricardo.  posted for zwt - na*me",
            nutrition = createNutrition(calories = 700f)
        )

        every { mealWithHighCalories.invoke(700f) } returns Result.success(meal)

        //when
        presenter.getMealsWithHighCalories()

        //Then
        assertEquals(meal, presenter.randomMealWithHighCalories)

    }


    @Test
    fun `should throw exception when meal with high calories use case return failure`() {
        // Given
        every { mealWithHighCalories.invoke(700f) } returns Result.failure(MealException.NoMealsFoundException("No meals found"))
        val output = mutableListOf<String>()
        every { printer.displayLn(capture(output)) } returns Unit

        // When
        presenter.getMealsWithHighCalories()

        // Then
        verify { printer.displayLn("No meals found") }
        assertThat(output).containsExactly("No meals found")
    }

}

