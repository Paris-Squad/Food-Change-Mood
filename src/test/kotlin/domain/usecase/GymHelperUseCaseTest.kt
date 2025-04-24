package domain.usecase

import domain.model.Meal
import domain.model.Nutrition
import io.mockk.every
import io.mockk.mockk
import kotlinx.datetime.LocalDate
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GymHelperUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat
import org.example.domain.MealException

class GymHelperUseCaseTest{


    private lateinit var mealRepository: MealRepository
    private lateinit var gymHelperUseCase: GymHelperUseCase

    @BeforeEach
    fun setUp(){
        mealRepository = mockk()
        gymHelperUseCase = GymHelperUseCase(mealRepository)
    }
    @Test
    fun `should return a list of meals that matches or approximate those value`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createTestMealsForGymHelper(400f,20f),
            createTestMealsForGymHelper(399f,19f),
            createTestMealsForGymHelper(401f,21f),
            createTestMealsForGymHelper(500f,15f),
            createTestMealsForGymHelper(300f,10f),
        )
        val calories =  400f
        val protein = 20f

        // When
        val result = gymHelperUseCase.invoke(calories, protein)

        // Then
        assertThat(result.getOrNull()).isEqualTo(
            listOf(
            createTestMealsForGymHelper(400f,20f),
            createTestMealsForGymHelper(399f,19f),
            createTestMealsForGymHelper(401f,21f)
            )
        )
    }

    @Test
    fun `should return a failure if the list of meals doesn't match or approximate the given values`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createTestMealsForGymHelper(500f,15f),
            createTestMealsForGymHelper(300f,10f),
        )
        val calories =  400f
        val protein = 20f

        // When
        val result = gymHelperUseCase.invoke(calories,protein)

        // Then
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `should return a failure if the list of meals is empty`(){
        // Given
        every { mealRepository.getMeals() } returns listOf()
        val calories =  400f
        val protein = 20f

        // When
        val result = gymHelperUseCase.invoke(calories, protein)

        // Then
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `should throw an exception when the filteredMeals list is empty`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createTestMealsForGymHelper(500f,15f),
            createTestMealsForGymHelper(300f,10f),
        )
        val calories =  400f
        val protein = 20f

        // When
        val result = gymHelperUseCase.invoke(calories,protein)

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(
            MealException.NoMealsFoundException::class.java
        )
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