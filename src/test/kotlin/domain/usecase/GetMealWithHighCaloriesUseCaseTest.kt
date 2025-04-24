package domain.usecase

import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetMealWithHighCaloriesUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GetMealWithHighCaloriesUseCaseTest {
    private val mealRepository = mockk<MealRepository>(relaxed = true)
    private lateinit var getMealWithHighCaloriesUseCase: GetMealWithHighCaloriesUseCase

    @BeforeEach
    fun setup() {
        getMealWithHighCaloriesUseCase = GetMealWithHighCaloriesUseCase(mealRepository)
    }

    @Test
    fun `should return meal with high calories`() {
        //Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(
                mealName = "tunisian penne",
                description = "recipe from ricardo.  posted for zwt - na*me",
                nutrition = createNutrition(calories = 700f)
            ),
            createMeal(
                mealName = "cherry chocolate cobbler",
                description = "a nice recipe to make to show you are a great cook.kids will love it the men too",
                nutrition = createNutrition(calories = 700f)
            )
        )

        //When
        val result = getMealWithHighCaloriesUseCase.invoke(700f)

        //Then
        assertEquals(700f, result.getOrNull()?.nutrition?.calories)
    }


    @Test
    fun `should throw exception when no meal with high calories founded`() {
        every { mealRepository.getMeals() } returns listOf(
            createMeal(
                mealName = "date   nut cake  gilacgi",
                description = "posted for the zaar world tour 2006-iraq",
                nutrition = createNutrition(calories = 654.2f)
            ),
            createMeal(
                mealName = "easy falafel",
                description = "this recipe for falafel and cuts down on preparation time. perfect for those who want an easier version of falafel",
                nutrition = createNutrition(calories = 157.0f)
            )
        )

        //When
        val result = getMealWithHighCaloriesUseCase.invoke(700f)

        //Then
        val exception = assertThrows<MealException.NoMealsFoundException> {
            result.getOrThrow()
        }
        assertEquals("No Meal Founded", exception.message)


    }

}