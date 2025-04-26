package presentaion.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.domain.usecase.GetKetoDietMealUseCase
import org.example.presentaion.presenter.KetoDietMealHelperPresenter
import org.example.presentaion.presenter.io.Printer
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import com.google.common.truth.Truth.assertThat
import io.mockk.mockkStatic
import org.example.domain.MealException
import utils.createMeal
import utils.createNutrition

class KetoDietMealHelperPresenterTest{

    private lateinit var ketoDietMealUseCase: GetKetoDietMealUseCase
    private val printer : Printer = mockk()
    private lateinit var ketoDietMealHelperPresenter: KetoDietMealHelperPresenter

    @BeforeEach
    fun setUp(){
        ketoDietMealUseCase = mockk()
        ketoDietMealHelperPresenter = KetoDietMealHelperPresenter(ketoDietMealUseCase,printer)
    }

    @Test
    fun `should return a suggested keto meal when GetKetoDietMealUseCase returns success`(){
        // Given
        val suggestedKetoMeal = createMeal(
           nutrition =  createNutrition(
                calories = 500f, protein = 25f,  totalFat = 48.3f,carbohydrates = 40f
            )

        )
        every { ketoDietMealUseCase.getSuggestedKetoMeal(any()) } returns Result.success(suggestedKetoMeal)
        every { printer.displayLn(any()) } returns Unit
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returns "1"

        // When
        ketoDietMealHelperPresenter.startKetoHelper()

        // Then
        verify { printer.displayLn("click 1 if you want a suggested keto diet meal.") }
        verify { printer.displayLn("click 2 to exit.") }
        verify { ketoDietMealUseCase.getSuggestedKetoMeal(any()) }
        assertThat(ketoDietMealHelperPresenter.repeatedMeals.contains(suggestedKetoMeal)).isTrue()
        verify { printer.displayLn("Name: orange juice") }

    }

    @Test
    fun `should return nothing when the user choose option 2 to exit`(){
        // Given
        every { printer.displayLn(any()) } returns Unit
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returns "2"

        // When
        ketoDietMealHelperPresenter.startKetoHelper()

        // Then
        verify { printer.displayLn("click 1 if you want a suggested keto diet meal.") }
        verify { printer.displayLn("click 2 to exit.")}
        verify(exactly = 0) { ketoDietMealUseCase.getSuggestedKetoMeal(any()) }

    }

    @Test
    fun `should return a warning string when the user pick other option than 1 or 2`(){
        // Given
        every { printer.displayLn(any()) } returns Unit
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returns "3"

        // When
        ketoDietMealHelperPresenter.startKetoHelper()

        // Then
        verify { printer.displayLn("click 1 if you want a suggested keto diet meal.") }
        verify { printer.displayLn("click 2 to exit.")}
        verify(exactly = 0) { ketoDietMealUseCase.getSuggestedKetoMeal(any())}
    }

    @Test
    fun `should handle NoMealsFoundException when getSuggestedKetoMeal returns failure`(){
        // Given
        every { printer.displayLn(any()) } returns Unit
        val exception = MealException.NoMealsFoundException("no keto meal found")
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returns "1"
        every { ketoDietMealUseCase.getSuggestedKetoMeal(any()) } returns Result.failure(exception)

        // When
        ketoDietMealHelperPresenter.startKetoHelper()

        // Then
        verify { printer.displayLn("click 1 if you want a suggested keto diet meal.") }
        verify { printer.displayLn("click 2 to exit.")}
        verify { ketoDietMealUseCase.getSuggestedKetoMeal(any()) }
        verify { printer.displayLn(exception.message) }
    }
}