package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetSeafoodMealsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetSeafoodMealsUseCaseTest {

    private lateinit var mealsRepository: MealRepository
    private lateinit var useCase: GetSeafoodMealsUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        useCase = GetSeafoodMealsUseCase(mealsRepository)
    }

    @Test
    fun `should return Failure Result of NoMealsFoundException when empty list found`() {
        //Given
        every { mealsRepository.getMeals() } returns emptyList()

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return Failure Result of NoMealsFoundException when can't find any meal that contain seafood in tags`() {
        //Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidSeafoodByNotContainsTags

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return Failure Result of NoMealsFoundException when all seafoods meals name ar null`() {
        //Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidSeafoodByNullName

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)

    }

    @Test
    fun `should return Failure Result of NoMealsFoundException when all seafoods meals protein ar null`() {
        //Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidSeafoodByNullNutrition

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return Success with MealName-Protein pairs when all meals have seafood tags and valid values`() {
        //Given
        every { mealsRepository.getMeals() } returns MockMeals.validSeaFoodMeals

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.getOrNull()).containsExactly(
            "smoked  salmon  cracker spread" to 30f,
            "put down your fork tuna and bean salad" to 20f
        ).inOrder()
    }
}