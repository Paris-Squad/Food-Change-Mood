package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetEasyMealSuggestionUseCase
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class GetEasyMealSuggestionUseCaseTest {

    private lateinit var mealRepository: MealRepository
    private lateinit var getEasyMealSuggestionUseCase: GetEasyMealSuggestionUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk()
        getEasyMealSuggestionUseCase = GetEasyMealSuggestionUseCase(mealRepository)
    }

    @Test
    fun `should return easy meals when meals prep time at most 30min, ingredients at most 5, steps at most 6`() {
        every { mealRepository.getMeals() } returns MockMeals.allMeals

        val result = getEasyMealSuggestionUseCase()

        assertThat(result.getOrNull()?.toSet()).isEqualTo(MockMeals.easyMeals.toSet())
        verify(exactly = 1) { mealRepository.getMeals() }
    }

    @Test
    fun `should return NoMealsFoundException when no easy meals are found`() {
        every { mealRepository.getMeals() } returns MockMeals.hardMeals

        val result = getEasyMealSuggestionUseCase()

        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
        verify(exactly = 1) { mealRepository.getMeals() }
    }


    @Test
    fun `should return failure when meal list is empty`() {
        every { mealRepository.getMeals() } returns emptyList()

        val result = getEasyMealSuggestionUseCase()

        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
        verify(exactly = 1) { mealRepository.getMeals() }
    }
    @Test
    fun `should return at most 10 meals when there are more than 10 easy meals`() {
        every { mealRepository.getMeals() } returns MockMeals.manyEasyMeals


        val result = getEasyMealSuggestionUseCase()

        assertThat(result.getOrNull()?.size).isEqualTo(10)
        verify(exactly = 1) { mealRepository.getMeals() }
    }

    @Test
    fun `should return meals sorted by preparation time`() {
        val unsortedMeals = listOf(MockMeals.easyMeal2, MockMeals.easyMeal1)
        every { mealRepository.getMeals() } returns unsortedMeals

        val result = getEasyMealSuggestionUseCase()

        assertThat(result.getOrNull()?.toSet()).isEqualTo(MockMeals.easyMeals.toSet())
        verify(exactly = 1) { mealRepository.getMeals() }
    }
}