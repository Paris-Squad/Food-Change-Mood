package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetQuickHealthyPicksUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.MockMeals
import kotlin.jvm.Throws


class GetQuickHealthyPicksUseCaseTest {

    private lateinit var mealsRepository: MealRepository
    private lateinit var useCase: GetQuickHealthyPicksUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        useCase = GetQuickHealthyPicksUseCase(mealsRepository)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when empty list found`() {
        //Given
        every { mealsRepository.getMeals() } returns emptyList()

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when meals have preparation time more than 15 minutes`() {
        // Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidQuickMealsWithHighPrepTime

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when meals have null names`() {
        // Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidEasyMealsByNullName

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when meals have null TotalFat`() {
        // Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidEasyMealsByNullTotalFat

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when meals have null SaturatedFat`() {
        // Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidEasyMealsByNullSaturatedFat

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when meals have null Carbohydrates`() {
        // Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidEasyMealsByNullCarbohydrates

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }


    @Test
    fun `should return Success with all the meals and ignore any meal that has Preparation minutes more than 15 minutes`() {
        // Given
        every { mealsRepository.getMeals() } returns MockMeals.validQuickMeals

        // When
        val result = useCase.invoke()

        // Then
        assertThat(result.getOrNull()).containsExactlyElementsIn(MockMeals.validQuickMeals.dropLast(1))
    }
}
