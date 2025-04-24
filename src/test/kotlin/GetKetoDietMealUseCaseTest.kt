
import io.mockk.every
import io.mockk.mockk
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetKetoDietMealUseCase
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat


class GetKetoDietMealUseCaseTest{

  private lateinit var mealRepository : MealRepository
  private lateinit var getKetoDietMealUseCase: GetKetoDietMealUseCase

  @BeforeEach
  fun setUp(){
    mealRepository = mockk()
    getKetoDietMealUseCase = GetKetoDietMealUseCase(mealRepository)
  }
  @Test
  fun `should return a random keto meal when the meal contains the required nutrition amount `(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
          createMeal(null,100f,900f,40f),
          createMeal(500f,25f,48.3f,40f),
          createMeal(500f,11f,400f,60f)
        )

        // When
        val result = getKetoDietMealUseCase.getSuggestedKetoMeal(setOf())

        // Then
      assertThat(result.getOrNull()).isEqualTo(
          createMeal(
          500f,25f,48.3f,40f
          )
      )

  }
    @Test
    fun `should return a failure when one of the required nutrition is null`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(500f,25f,null,40f)
        )

        // When
        val result = getKetoDietMealUseCase.getSuggestedKetoMeal(setOf())

        // Then
        assertThat(result.isFailure).isTrue()
    }
    @Test
    fun `should return only a keto meal that is not repeated`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createMeal(null,100f,900f,40f),
            createMeal(500f,25f,48.3f,42f),
            createMeal(500f,11f,400f,60f)
        )

        // When
        val result = getKetoDietMealUseCase.getSuggestedKetoMeal(
            setOf(
                createMeal(500f,25f,48.3f,42f) //repeated keto meal
            )
        )

        // Then
        assertThat(result.isFailure).isTrue()
    }
    @Test
    fun `should return a failure when there is no keto meals returned from getMeals()`(){
        // Given
        every { mealRepository.getMeals() } returns listOf()

        // When
        val result = getKetoDietMealUseCase.getSuggestedKetoMeal(emptySet())

        // Then
        assertThat(result.isFailure).isTrue()
    }

}