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
        every { mealsRepository.getMeals() } returns listOf(
            createMeal(
                tags = listOf("seafood"),
                nutrition = createNutrition(protein = 20f)
            ),
            createMeal(
                tags = listOf("seafood", "cuisine"),
                nutrition = createNutrition(protein = 30f)
            ),
            createMeal(
                tags = listOf("pasta"),
                nutrition = createNutrition(protein = 10f)
            )
        )

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return Failure Result of NoMealsFoundException when all seafoods meals name ar null`() {
        //Given
        every { mealsRepository.getMeals() } returns listOf(
            createMeal(
                tags = listOf("seafood"),
                nutrition = createNutrition(protein = 10f)
            ),
            createMeal(
                tags = listOf("seafood"),
                nutrition = createNutrition(protein = 10f)
            ),
            createMeal(
                tags = listOf("seafood"),
                nutrition = createNutrition(protein = 10f)
            ),
        )

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)

    }

    @Test
    fun `should return Failure Result of NoMealsFoundException when all seafoods meals protein ar null`() {
        //Given
        every { mealsRepository.getMeals() } returns listOf(
            createMeal(
                mealName = "put down your fork tuna and bean salad",
                tags = listOf("seafood")
            ),
            createMeal(
                mealName = "smoked  salmon  cracker spread",
                tags = listOf("seafood")
            ),
            createMeal(
                mealName = "voodoo  calamari ink  pasta",
                tags = listOf("seafood")
            ),
        )

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return Success Result List of Pair MealName & protein when all meals contain seafoods in tags and Name & Protein ar not null`() {
        //Given
        val mealsList = listOf(
            createMeal(
                mealName = "put down your fork tuna and bean salad",
                tags = listOf("seafood"),
                nutrition = createNutrition(protein = 20f)
            ),
            createMeal(
                mealName = "smoked  salmon  cracker spread",
                tags = listOf("seafood", "cuisine"),
                nutrition = createNutrition(protein = 30f)
            ),
            createMeal(
                mealName = "voodoo  calamari ink  pasta",
                tags = listOf("pasta"),
                nutrition = createNutrition(protein = 10f)
            )
        )
        every { mealsRepository.getMeals() } returns mealsList

        //When
        val result = useCase.invoke()

        //Then
        assertThat(result.getOrNull()).containsExactly(
            "smoked  salmon  cracker spread" to 30f,
            "put down your fork tuna and bean salad" to 20f
        ).inOrder()
    }
}