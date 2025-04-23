package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetIraqiMealsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class GetIraqiMealsUseCaseTest {

    val mealRepository = mockk<MealRepository>(relaxed = true)
    private lateinit var getIraqiMealsUseCase: GetIraqiMealsUseCase


    @BeforeEach
    fun setup() {
        getIraqiMealsUseCase = GetIraqiMealsUseCase(mealRepository)
    }

    @Test
    fun `should return meals tagged as Iraqi`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Dolma", description = "Tasty Dolma", tags = listOf("iraqi", "traditional")),
            createMeal(mealName = "Burger", description = "Fast food", tags = listOf("american"))
        )

        // When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        // Then
        assertEquals("Dolma", result.getOrNull()?.first()?.mealName)
    }

    @Test
    fun `should return meals when description contains Iraq`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Kebab", description = "Popular in Iraq", tags = listOf("middle-eastern")),
            createMeal(mealName = "Pizza", description = "Italian classic", tags = listOf("italian"))
        )

        // When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        // Then
        assertEquals(1, result.getOrNull()?.size)
    }

    @Test
    fun `should return meals when either tag is Iraqi or description mentions Iraq`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Dolma", description = "Popular in Iraq", tags = listOf("iraqi")),
            createMeal(mealName = "Kebab", description = "Iraqi-style grilled meat", tags = listOf("grilled"))
        )

        // When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        // Then
        assertThat(result.getOrNull()?.map { it.mealName }).containsExactly("Dolma", "Kebab")
    }

    @Test
    fun `should throw exception when no Iraqi meals found`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(mealName = "Pasta", description = "Classic Italian", tags = listOf("italian")),
            createMeal(mealName = "Sushi", description = "Japanese favorite", tags = listOf("japanese"))
        )

        // When
        val result = getIraqiMealsUseCase.getIraqiMeals()

        // Then
        val exception = assertThrows<MealException.NoMealsFoundException> {
            result.getOrThrow()
        }
        assertEquals("No Iraqi Meals found", exception.message)
    }



}
