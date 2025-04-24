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
import kotlin.test.assertEquals

class GetMealsByCountryUseCaseTest {

    val mealRepository = mockk<MealRepository>(relaxed = true)
    private lateinit var getMealsByCountryUseCase: GetMealsByCountryUseCase

    @BeforeEach
    fun setup() {
        getMealsByCountryUseCase = GetMealsByCountryUseCase(mealRepository)
    }

    @Test
    fun `should return meals related to the given country`() {
        // Given
        val meal1 =  createMeal(mealName = "Dolma", description = "Popular in Iraq", tags = listOf("iraqi"))
        val meal2 = createMeal(mealName = "Kebab", description = "Grilled dish", tags = listOf("iraqi", "grilled"))
        val meal3 = createMeal(mealName = "Sushi", description = "From Japan", tags = listOf("japanese"))
        every { mealRepository.getMeals() } returns listOf(meal1,meal2,meal3)

        // when
        val result = getMealsByCountryUseCase("japan", 1)

        // then
        assertThat(result.getOrNull()).containsExactly(meal3)
    }

    @Test
    fun `should return only requested number of meals  `() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Pasta", description = "Traditional meal in Italy", tags = listOf("italian", "traditional")),
            createMeal(mealName = "Pizza Margherita", description = "Classic Neapolitan pizza from Italy", tags = listOf("italian", "pizza")),
            createMeal(mealName = "Risotto", description = "Creamy rice dish popular in northern Italy", tags = listOf("italian", "rice")),
            createMeal(mealName = "Lasagna", description = "Layered pasta dish with rich Italian flavors", tags = listOf("italian", "baked")),
            createMeal(mealName = "Tiramisu", description = "Famous Italian dessert with coffee and mascarpone", tags = listOf("italian", "dessert"))
        )

        // when
        val result = getMealsByCountryUseCase("italy", 3)

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
        val result = getMealsByCountryUseCase("Egypt", 100)

        // then
        assertThat(result.getOrNull()).hasSize(20)
    }

    @Test
    fun `should throw Exception when country name is empty`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Dolma", description = "Popular in Iraq", tags = listOf("iraqi")),
            createMeal(mealName = "Kebab", description = "Grilled dish", tags = listOf("iraqi", "grilled")),
            createMeal(mealName = "Sushi", description = "From Japan", tags = listOf("japanese"))
        )

        // when
        val result = getMealsByCountryUseCase("   ", 5)

        // then
        val exception = assertThrows<MealException.IllegalArgumentException> {
            result.getOrThrow()
        }
        assertEquals("Country name cannot be empty.", exception.message)
    }

    @Test
    fun `should throw Exception when count is less than or equal to 0`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Beef Stroganoff", description = "Classic Russian dish of sautéed beef in a sour cream sauce", tags = listOf("moscow", "russian")),
            createMeal(mealName = "Pelmeni", description = "Russian dumplings filled with minced meat, popular in Moscow", tags = listOf("moscow", "russian", "dumplings")),
            createMeal(mealName = "Olivier Salad", description = "Traditional Russian salad with potatoes, vegetables, and mayonnaise", tags = listOf("moscow", "russian", "salad"))
        )

        // when
        val result = getMealsByCountryUseCase("Moscow", 0)

        // then
        val exception = assertThrows<MealException.IllegalArgumentException> {
            result.getOrThrow()
        }
        Assertions.assertEquals("Invalid Count.", exception.message)
    }

    @Test
    fun `should throw Exception when no meals match the country`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Pizza", description = "Italian", tags = listOf("italian")),
            createMeal(mealName = "Sushi", description = "Japanese", tags = listOf("japanese"))
        )

        // when
        val result = getMealsByCountryUseCase("iraq", 5)

        // then
        val exception = assertThrows<MealException.NoMealsFoundException> {
            result.getOrThrow()
        }
        Assertions.assertEquals("No meals found related to 'iraq'", exception.message)
    }

}