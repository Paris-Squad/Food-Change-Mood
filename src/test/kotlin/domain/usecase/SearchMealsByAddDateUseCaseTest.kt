package domain.usecase

import domain.model.Meal
import domain.model.Nutrition
import io.mockk.every
import io.mockk.mockk
import kotlinx.datetime.LocalDate
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.SearchMealsByAddDateUseCase
import com.google.common.truth.Truth.assertThat
import org.example.domain.MealException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchMealsByAddDateUseCaseTest{

    private lateinit var mealRepository: MealRepository
    private lateinit var searchMealsByAddDateUseCase: SearchMealsByAddDateUseCase

    @BeforeEach
    fun setUp(){
        mealRepository = mockk()
        searchMealsByAddDateUseCase = SearchMealsByAddDateUseCase(mealRepository)
    }

    @Test
    fun `should return a list of meals when I pass a date to invoke`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createTestMealsForSearchingByAddDate(LocalDate(2023, 1, 1),"1"),
            createTestMealsForSearchingByAddDate(LocalDate(2022, 1, 1),"2"),
            createTestMealsForSearchingByAddDate(LocalDate(2021, 1, 1),"3")
        )

        // When
        val result = searchMealsByAddDateUseCase.invoke(LocalDate(2023, 1, 1))

        // Then
        assertThat(result.getOrNull()).isEqualTo(
            listOf(
                createTestMealsForSearchingByAddDate(LocalDate(2023, 1, 1),"1"
                )
            )
        )
    }

    @Test
    fun `should return a failure when there is no meals with the inserted date`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createTestMealsForSearchingByAddDate(LocalDate(2023, 1, 1),"1"),
            createTestMealsForSearchingByAddDate(LocalDate(2022, 1, 1),"2"),
            createTestMealsForSearchingByAddDate(LocalDate(2021, 1, 1),"3")
        )

        // When
        val result = searchMealsByAddDateUseCase.invoke(LocalDate(2024, 1, 1))

        // Then
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `should return a failure when the list of meals is empty`(){
        // Given
        every { mealRepository.getMeals() } returns listOf()

        // When
        val result = searchMealsByAddDateUseCase.invoke(LocalDate(2024, 1, 1))

        // Then
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `should throw NoMealsFoundException when the list of meals is empty`(){
        // Given
        every { mealRepository.getMeals() } returns listOf()

        // When
        val result = searchMealsByAddDateUseCase.invoke(LocalDate(2024, 1, 1))

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return a meal that matches the provided Id`(){
        // Given
        val meals = listOf(
            createTestMealsForSearchingByAddDate(LocalDate(2023, 1, 1),"1"),
            createTestMealsForSearchingByAddDate(LocalDate(2022, 1, 1),"2"),
            createTestMealsForSearchingByAddDate(LocalDate(2021, 1, 1),"3")
        )

        // When
        val result = searchMealsByAddDateUseCase.findMealByIdInList("1",meals)

        // Then
        assertThat(result.getOrNull()).isEqualTo(
            createTestMealsForSearchingByAddDate(
                LocalDate(2023, 1, 1),
                "1"
            )
        )
    }

    @Test
    fun `should return a failure if the provided Id doesn't match any meal of the listed meals`(){
        // Given
        val meals = listOf(
            createTestMealsForSearchingByAddDate(LocalDate(2023, 1, 1),"1"),
            createTestMealsForSearchingByAddDate(LocalDate(2022, 1, 1),"2"),
            createTestMealsForSearchingByAddDate(LocalDate(2021, 1, 1),"3")
        )

        // When
        val result = searchMealsByAddDateUseCase.findMealByIdInList("4",meals)

        // Then
       assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `should return a NoMealsFoundException if the provided Id doesn't match any meal of the listed meals`(){
        // Given
        val meals = listOf(
            createTestMealsForSearchingByAddDate(LocalDate(2023, 1, 1),"1"),
            createTestMealsForSearchingByAddDate(LocalDate(2022, 1, 1),"2"),
            createTestMealsForSearchingByAddDate(LocalDate(2021, 1, 1),"3")
        )

        // When
        val result = searchMealsByAddDateUseCase.findMealByIdInList("4",meals)

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return a failure if the provided list of meals is empty`(){
        // Given
        val meals = emptyList<Meal>()

        // When
        val result = searchMealsByAddDateUseCase.findMealByIdInList("4",meals)

        // Then
        assertThat(result.isFailure).isTrue()
    }








    companion object{
        fun createTestMealsForSearchingByAddDate(
            date : LocalDate,
            mealId : String
        ) = Meal(
            mealName = null,
            mealId = mealId,
            minutesForPreparation = 30,
            contributorId = "1",
            nutrition = Nutrition(
                null,null,null,null,null,null,null
            ),
            description = null,
            numberOfIngredients = 3,
            numberOfSteps = 3,
            steps = listOf("do", "do", "do"),
            ingredients = listOf("do", "do", "do"),
            tags = listOf("do", "do", "do"),
            submittedDate = date

        )
    }


}