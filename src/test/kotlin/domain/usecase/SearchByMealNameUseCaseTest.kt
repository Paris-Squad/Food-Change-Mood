package domain.usecase

import io.mockk.mockk
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.SearchByMealNameUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import org.example.domain.MealException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import utils.createMeal
import kotlin.collections.listOf
import kotlin.test.assertEquals

class SearchByMealNameUseCaseTest {

    private lateinit var mealRepository: MealRepository
    private lateinit var mealByNameUseCase: SearchByMealNameUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk()
        mealByNameUseCase = SearchByMealNameUseCase(mealRepository)
    }

    @Test
    fun `should return meals that exactly match search term`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(validMeal)

        // When
        val result = mealByNameUseCase.invoke(validMealName)

        // Then
        assertThat(result.getOrNull()).containsExactly(validMeal)
    }

    @Test
    fun `should return meals with similar names using Levenshtein`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(validMeal)

        // When
        val result = mealByNameUseCase.invoke("Kebob")

        // Then
        assertThat(result.getOrNull()).containsExactly(validMeal)
    }

    @Test
    fun `should throw exception when no meals match`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(validMeal)

        // When
        val result = mealByNameUseCase.invoke("Pizza")

        // Then
        val exception = assertThrows<MealException.NoMealsFoundException> {
            result.getOrThrow()
        }
        assertEquals("no food found matching 'Pizza'", exception.message)
    }

    @Test
    fun `should return meals with same name and ignore meals with null or empty names`() {
        // Given
        val meals = listOf(
            createMeal(mealName = null),
            createMeal(mealName = validMealName),
            createMeal(mealName = ""),
            createMeal(mealName = " "),
            createMeal(mealName = "Keba")
        )
        every { mealRepository.getMeals() } returns meals

        // When
        val result = mealByNameUseCase.invoke(validMealName)

        // Then
        assertThat(result.getOrNull()?.map { it.mealName }).containsExactlyElementsIn(listOf("Kebab", "Keba"))
    }


    companion object{
        private const val validMealName = "Kebab"
        val validMeal = createMeal(mealName = validMealName)
    }

}
