package presentaion.presenter

import com.sun.org.apache.xpath.internal.compiler.Token.contains
import domain.model.Meal
import domain.model.Nutrition
import domain.usecase.SearchMealsByAddDateUseCaseTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.datetime.LocalDate
import org.example.domain.MealException
import org.example.domain.usecase.SearchMealsByAddDateUseCase
import org.example.presentaion.presenter.SearchMealsByAddDatePresenter
import org.example.presentaion.presenter.io.InputReader
import org.example.presentaion.presenter.io.Printer
import org.example.utils.formatDetails
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import utils.createMeal
import kotlin.test.Test

class SearchMealsByAddDatePresenterTest{


    private lateinit var searchMealsByAddDateUseCase: SearchMealsByAddDateUseCase
    private val printer : Printer = mockk()
    private lateinit var searchMealsByAddDatePresenter: SearchMealsByAddDatePresenter
    private val reader : InputReader = mockk()

    @BeforeEach
    fun setUp(){
        searchMealsByAddDateUseCase = mockk()
        searchMealsByAddDatePresenter = SearchMealsByAddDatePresenter(
            searchMealsByAddDateUseCase,printer,reader
        )
    }

    @Test
    fun `should return a meal when searchMealsByAddDate returns success`(){
        // Given
        val expectedDate = LocalDate(2023, 1, 1)
        val meals =listOf(
            createMeal(
               submittedDate =  LocalDate(
                    2023, 1, 1),
               mealId =  "1",
                mealName = "orange juice"
            ),
            createMeal(
               submittedDate =  LocalDate(
                    2022, 1, 1),
                mealId = "2",
                mealName = "mango juice"
            ),
            createMeal(
               submittedDate =  LocalDate(
                    2021, 1, 1),
                mealId = "3",
                mealName = "apple juice"
            )
        )
        val testMeal = createMeal(
            submittedDate =  LocalDate(
                2023, 1, 1),
            mealId = "1",
            mealName = "orange juice"
        )

        every { printer.displayLn(any()) } returns Unit
        every { searchMealsByAddDateUseCase.invoke(any()) } returns Result.success(meals)
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returnsMany listOf("2023-01-01","1")
        every { searchMealsByAddDateUseCase.findMealByIdInList(any(),any()) } returns Result.success(
            testMeal
        )

        // When
        searchMealsByAddDatePresenter.searchMealsByCreationDate()

        // Then
        verify(exactly = 1){ printer.displayLn("Enter a date (yyyy-MM-dd):") }
        verify(exactly = 1){ searchMealsByAddDateUseCase.invoke(any()) }
        verify(exactly = 1){ searchMealsByAddDateUseCase.findMealByIdInList(any(),any()) }
        verify(exactly = 1){
             printer.displayLn("\n========== MEALS ADDED ON $expectedDate ==========\n")
          }
        meals.forEachIndexed { index, meal ->
            verify (exactly = 1){
                printer.displayLn(
                    "Meal ${index + 1}: ID: ${meal.mealId} | Name: ${meal.mealName ?: "Unnamed Meal"}"
                )
            }
        }
        verify(exactly = 1){printer.displayLn("\nEnter the ID of a meal to view full details:")}
        verify(exactly = 1){searchMealsByAddDateUseCase.findMealByIdInList(any(),any())}
        verify(exactly = 1){printer.displayLn("--------------------------------------------------")}
        verify(exactly = 1){printer.displayLn(testMeal.formatDetails())}
    }

    @Test
    fun `should handle InvalidDateFormatException when parseDate returns failure`(){
        // Given
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returns "2023-1-1"
        every { printer.displayLn(any()) } returns Unit

        // When
        searchMealsByAddDatePresenter.searchMealsByCreationDate()

        // Then
        verify { printer.displayLn("Invalid date format: Use yyyy-MM-dd") }
        verify(exactly = 0){ searchMealsByAddDateUseCase.invoke(any()) }
        verify(exactly = 0){ searchMealsByAddDateUseCase.findMealByIdInList(any(),any()) }
    }

    @Test
    fun `should handle NoMealsFoundException when searchMealByAddedDate function returns failure`(){
        // Given
        val expectedDate = LocalDate(2025, 1, 1)
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returns "2025-01-01"
        every {
            searchMealsByAddDateUseCase.invoke(any())
        } returns Result.failure(MealException.NoMealsFoundException("No meals found on $expectedDate"))
        every { printer.displayLn(any()) } returns Unit

        // When
        searchMealsByAddDatePresenter.searchMealsByCreationDate()

        //Then
        verify(exactly = 1){ printer.displayLn("Enter a date (yyyy-MM-dd):") }
        verify { searchMealsByAddDateUseCase.invoke(any()) }
        verify { printer.displayLn("No meals found on $expectedDate") }
    }

    @Test
    fun `should handle NoMealFoundException when findMealByIdInList returns failure`(){
        val meals =listOf(
            createMeal(
                submittedDate =  LocalDate(
                    2023, 1, 1),
                mealId = "1",
                mealName = "orange juice"
            ),
            createMeal(
                submittedDate =  LocalDate(
                    2024, 1, 1),
                mealId = "2",
               mealName =  "mango juice"
            ),
            createMeal(
                submittedDate =  LocalDate(
                    2022, 1, 1),
                mealId = "3",
               mealName =  "apple juice"
            )
        )
        mockkStatic("kotlin.io.ConsoleKt")
        every { readln() } returnsMany listOf("2025-01-01","4")
        every {
            searchMealsByAddDateUseCase.invoke(any())
        } returns Result.success(meals)
        every {
            searchMealsByAddDateUseCase.findMealByIdInList(any(),any())
        } returns Result.failure(MealException.NoMealsFoundException("Meal with ID 4 not found"))
        every { printer.displayLn(any()) } returns Unit

        // When
        searchMealsByAddDatePresenter.searchMealsByCreationDate()

        // Then
        verify(exactly = 1){ printer.displayLn("Enter a date (yyyy-MM-dd):") }
        verify { searchMealsByAddDateUseCase.invoke(any()) }
        meals.forEachIndexed { index, meal ->
            verify (exactly = 1){
                printer.displayLn(
                    "Meal ${index + 1}: ID: ${meal.mealId} | Name: ${meal.mealName ?: "Unnamed Meal"}"
                )
            }
        }
        verify(exactly = 1){ printer.displayLn("\nEnter the ID of a meal to view full details:") }
        verify(exactly = 1){ searchMealsByAddDateUseCase.findMealByIdInList(any(),any()) }
        verify { printer.displayLn("Meal with ID 4 not found") }
    }





}
