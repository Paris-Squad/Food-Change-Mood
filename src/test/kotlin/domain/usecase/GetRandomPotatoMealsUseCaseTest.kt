package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetRandomPotatoMealsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetRandomPotatoMealsUseCaseTest() {
    lateinit var getRandomPotatoMealsUseCase: GetRandomPotatoMealsUseCase
    val mealRepository = mockk<MealRepository>(relaxed = true)

    @BeforeEach
    fun setup() {
        getRandomPotatoMealsUseCase = GetRandomPotatoMealsUseCase(mealRepository = mealRepository)
    }

    @Test
    fun `should throw exception when the list is empty`() {
        // Given
        every { mealRepository.getMeals() } returns emptyList()
        // When
        val result = getRandomPotatoMealsUseCase()
        // Then
        assertThat(result.isFailure).isTrue()
        val exception = result.exceptionOrNull()
        assertThat(exception).isInstanceOf(MealException.NoMealsFoundException::class.java)
        assertThat(exception?.message).isEqualTo("No meals found containing potatoes")
    }

    @Test
    fun `should throw exception when the list doesn't contain potato`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal("Rice and Chicken", listOf("Rice", "Chicken", "Salt")),
            createMeal("Vegetable Soup", listOf("Carrot", "Onion", "Zucchini")),
            createMeal("Pasta Alfredo", listOf("Pasta", "Cream", "Cheese")),
        )
        // When
        val result = getRandomPotatoMealsUseCase()
        // Then
        assertThat(result.isFailure)
        val exception = result.exceptionOrNull()
        assertThat(exception).isInstanceOf(MealException.NoMealsFoundException::class.java)
        assertThat(exception?.message).isEqualTo("No meals found containing potatoes")
    }

    @Test
    fun `should throw exception when meals are not enough`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal("Potato 1", listOf("Potato")),
            createMeal("Potato 2", listOf("Potato")),
            createMeal("Potato 3", listOf("Potato")),
            createMeal("Potato 4", listOf("Potato")),

            )
        // When
        val result = getRandomPotatoMealsUseCase()
        // Then
        val exception = result.exceptionOrNull()
        assertThat(exception).isInstanceOf(MealException.NoEnoughMealsFound::class.java)
        assertThat(exception?.message).isEqualTo("No Enough Potato Meals")
    }

    @Test
    fun `should return 10 random potato meals when no count is passed to the use case`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal("Potato 1", listOf("Potato")),
            createMeal("Potato 2", listOf("Potato")),
            createMeal("Potato 3", listOf("Potato")),
            createMeal("Potato 4", listOf("Potato")),
            createMeal("Potato 5", listOf("Potato")),
            createMeal("Potato 6", listOf("Potato")),
            createMeal("Potato 7", listOf("Potato")),
            createMeal("Potato 8", listOf("Potato")),
            createMeal("Potato 9", listOf("Potato")),
            createMeal("Potato 10", listOf("Potato")),
        )
        // When
        val result = getRandomPotatoMealsUseCase()
        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).hasSize(10)
    }

    @Test
    fun `should return the given number of random potato meals when count is passed to the use case`() {
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal("Potato 1", listOf("Potato")),
            createMeal("Potato 2", listOf("Potato")),
            createMeal("Potato 3", listOf("Potato")),
            createMeal("Potato 4", listOf("Potato")),
            createMeal("Potato 5", listOf("Potato")),
            createMeal("Potato 6", listOf("Potato")),
            createMeal("Potato 7", listOf("Potato")),
            createMeal("Potato 8", listOf("Potato")),
            createMeal("Potato 9", listOf("Potato")),
            createMeal("Potato 10", listOf("Potato")),
        )
        // When
        val countOfMeals = 4
        val result = getRandomPotatoMealsUseCase(countOfMeals)
        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).hasSize(countOfMeals)
    }


}