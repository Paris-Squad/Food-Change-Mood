package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetEggFreeSweetsUseCase
import org.junit.jupiter.api.BeforeEach
import utils.MockMeals
import kotlin.jvm.Throws
import kotlin.test.Test

class GetEggFreeSweetsUseCaseTest {
    private lateinit var mealRepository: MealRepository
    private lateinit var useCase: GetEggFreeSweetsUseCase

    @BeforeEach
    fun setUp() {
        mealRepository = mockk()
        useCase = GetEggFreeSweetsUseCase(mealRepository)
    }

    @Test
    fun `should return a random egg-free sweet meal when getEggFreeSweetsUseCase returns a egg-free sweet meal`() {
        every { mealRepository.getMeals() } returns MockMeals.eggFreeSweetsMixedTestMeals

        val result = useCase.getRandomEggFreeSweet()

        assertThat(result.getOrNull()).isAnyOf(MockMeals.eggFreeSweet1 , MockMeals.eggFreeSweet2)
    }

    @Test
    @Throws(MealException.NoMealsFoundException::class)
    fun `should return NoMealsFoundException when no egg-free sweets are found`() {
        every { mealRepository.getMeals() } returns MockMeals.noEggFreeSweetsMeals

        val result = useCase.getRandomEggFreeSweet()

        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Test
    fun `should return NoMealsFoundException when meal list is empty`() {
        every { mealRepository.getMeals() } returns emptyList()

        val result = useCase.getRandomEggFreeSweet()

        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }
}