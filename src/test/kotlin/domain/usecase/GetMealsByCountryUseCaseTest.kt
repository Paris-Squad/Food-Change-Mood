package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetMealsByCountryUseCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import utils.createMeal
import kotlin.test.assertEquals

class GetMealsByCountryUseCaseTest {

    val mealRepository = mockk<MealRepository>(relaxed = true)
    private lateinit var mealsByCountryUseCase: GetMealsByCountryUseCase

    @BeforeEach
    fun setup() {
        mealsByCountryUseCase = GetMealsByCountryUseCase(mealRepository)
    }

    @Test
    fun `should return meals related to the given country`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(dolma,kebab,sushi)

        // when
        val result = mealsByCountryUseCase("japan", 1)

        // then
        assertThat(result.getOrNull()).containsExactly(sushi)
    }

    @Test
    fun `should return only requested number of meals`() {
        // Given
        every { mealRepository.getMeals() } returns italianMeals

        // when
        val result = mealsByCountryUseCase("italy", 3)

        // then
        assertThat(result.getOrNull()).hasSize(3)
    }

    @Test
    fun `should not return more than 20 meals when large count requested`() {
        // Given
        every { mealRepository.getMeals() } returns List(50) {
            createMeal(mealName = "Koshari", description = "Traditional Egyptian street food made with rice, lentils, and pasta", tags = listOf("egyptian", "traditional"))
        }

        // when
        val result = mealsByCountryUseCase("Egypt", 100)

        // then
        assertThat(result.getOrNull()).hasSize(20)
    }

    @Test
    fun `should throw IllegalArgumentException Exception when country name is empty`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(dolma,kebab,sushi)

        // when
        val result = mealsByCountryUseCase("   ", 5)

        // then
        val exception = assertThrows<MealException.IllegalArgumentException> {
            result.getOrThrow()
        }
        assertEquals("Country name cannot be empty.", exception.message)
    }

    @Test
    fun `should throw IllegalArgumentException Exception when count is less than or equal to 0`() {
        // Given
        every { mealRepository.getMeals() } returns italianMeals

        // when
        val result = mealsByCountryUseCase("italy", 0)

        // then
        val exception = assertThrows<MealException.IllegalArgumentException> {
            result.getOrThrow()
        }
        Assertions.assertEquals("Invalid Count.", exception.message)
    }

    @Test
    fun `should throw Exception when no meals match the country`() {
        // Given
        every { mealRepository.getMeals() } returns italianMeals

        // when
        val result = mealsByCountryUseCase("iraq", 5)

        // then
        val exception = assertThrows<MealException.NoMealsFoundException> {
            result.getOrThrow()
        }
        Assertions.assertEquals("No meals found related to 'iraq'", exception.message)
    }


    companion object {
        val dolma = createMeal(mealName = "Dolma", description = "Popular in Iraq", tags = listOf("iraqi"))
        val kebab = createMeal(mealName = "Kebab", description = "Grilled dish", tags = listOf("iraqi", "grilled"))
        val sushi = createMeal(mealName = "Sushi", description = "From Japan", tags = listOf("japanese"))

        val italianMeals = listOf(
            createMeal(mealName = "Pasta", description = "Traditional meal in Italy", tags = listOf("italian", "traditional")),
            createMeal(mealName = "Pizza Margherita", description = "Classic Neapolitan pizza from Italy", tags = listOf("italian", "pizza")),
            createMeal(mealName = "Risotto", description = "Creamy rice dish popular in northern Italy", tags = listOf("italian", "rice")),
            createMeal(mealName = "Lasagna", description = "Layered pasta dish with rich Italian flavors", tags = listOf("italian", "baked")),
            createMeal(mealName = "Tiramisu", description = "Famous Italian dessert with coffee and mascarpone", tags = listOf("italian", "dessert"))
        )
    }

}