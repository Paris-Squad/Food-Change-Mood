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
            createMeal(mealName = "Rice and Chicken",ingredients = listOf("Rice", "Chicken", "Salt")),
            createMeal(mealName = "Vegetable Soup",  ingredients = listOf("Carrot", "Onion", "Zucchini")),
            createMeal(mealName = "Pasta Alfredo",   ingredients = listOf("Pasta", "Cream", "Cheese")),
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
            createMeal(mealName = "Potato 1", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 2", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 3", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 4", ingredients = listOf("Potato")),

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
            createMeal(mealName = "Potato 1", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 2", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 3", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 4", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 5", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 6", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 7", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 8", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 9", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 10",ingredients = listOf("Potato")),
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
            createMeal(mealName = "Potato 1", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 2", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 3", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 4", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 5", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 6", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 7", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 8", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 9", ingredients = listOf("Potato")),
            createMeal(mealName = "Potato 10",ingredients = listOf("Potato")),
        )
        // When
        val countOfMeals = 4
        val result = getRandomPotatoMealsUseCase(countOfMeals)
        // Then
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).hasSize(countOfMeals)
    }


}