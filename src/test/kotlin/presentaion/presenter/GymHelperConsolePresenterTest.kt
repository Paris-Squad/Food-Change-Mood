package presentaion.presenter

import org.junit.jupiter.api.Assertions.*
import com.google.common.truth.Truth.assertThat
import domain.model.Meal
import domain.model.Nutrition
import domain.usecase.GymHelperUseCaseTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.datetime.LocalDate
import org.example.domain.MealException
import org.example.domain.usecase.GymHelperUseCase
import org.example.presentaion.presenter.GymHelperConsolePresenter
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GymHelperConsolePresenterTest{

    private lateinit var gymHelperUseCase: GymHelperUseCase
    private lateinit var gymHelperConsolePresenter: GymHelperConsolePresenter
    private val printer : Printer = mockk()

    @BeforeEach
    fun setUp(){
        gymHelperUseCase = mockk()
        gymHelperConsolePresenter = GymHelperConsolePresenter(gymHelperUseCase,printer)
    }

    @Test
    fun `should return a list of meals when gymHelperUseCase returns success`(){
        // Given
        val gymMeals = listOf(
            GymHelperUseCaseTest.createTestMealsForGymHelper(400f, 20f),
            GymHelperUseCaseTest.createTestMealsForGymHelper(399f, 19f),
            GymHelperUseCaseTest.createTestMealsForGymHelper(401f, 21f)
        )
        every { gymHelperUseCase.invoke(any(),any()) } returns Result.success(gymMeals)
        every { printer.displayLn(any()) } returns Unit
        val calories =  400f
        val protein = 20f

        // When
        gymHelperConsolePresenter.presentGymMeals(calories, protein)

        // Then
        verify(exactly = 1){ gymHelperUseCase.invoke(any(),any()) }
        gymMeals.forEach{meal->
            verify (exactly = 1){ printer.displayLn(meal.formatDetails()) }
        }
    }

    @Test
    fun `should handle NoMealsFoundException when gymHelperUseCase returns failure`(){
        // Given
        val exception = MealException.NoMealsFoundException("No meals found")
        every { printer.displayLn(any()) } returns Unit
        every { gymHelperUseCase.invoke(any(),any()) } returns Result.failure(exception)
        val calories =  400f
        val protein = 20f

        // When
        gymHelperConsolePresenter.presentGymMeals(calories,protein)

        // Then
        verify(exactly = 1){gymHelperUseCase.invoke(any(),any())}
        verify(exactly = 1){ printer.displayLn(exception.message) }

    }


    companion object{
        fun createTestMealsForGymHelper(
            calories : Float?,
            protein : Float?
        ) = Meal(
            mealName = null,
            mealId = "1",
            minutesForPreparation = 30,
            contributorId = "1",
            nutrition = Nutrition(
                calories,null,null,null,protein,null,null
            ),
            description = null,
            numberOfIngredients = 3,
            numberOfSteps = 3,
            steps = listOf("do", "do", "do"),
            ingredients = listOf("do", "do", "do"),
            tags = listOf("do", "do", "do"),
            submittedDate = LocalDate(2000,12,5)

        )
    }


}