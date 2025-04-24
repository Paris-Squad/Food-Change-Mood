
import io.mockk.every
import io.mockk.mockk
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetKetoDietMealUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat
import domain.model.Meal
import domain.model.Nutrition
import kotlinx.datetime.LocalDate
import org.example.domain.MealException


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
            createTestMealsForKetoMealHelper(null,100f,900f,40f),
            createTestMealsForKetoMealHelper(500f,25f,48.3f,40f),
            createTestMealsForKetoMealHelper(500f,11f,400f,60f)
        )

        // When
        val result = getKetoDietMealUseCase.getSuggestedKetoMeal(setOf())

        // Then
      assertThat(result.getOrNull()).isEqualTo(
          createTestMealsForKetoMealHelper(
          500f,25f,48.3f,40f
          )
      )

  }
    @Test
    fun `should return a failure when one of the required nutrition is null`(){
        // Given
        every { mealRepository.getMeals() } returns listOf(
            createTestMealsForKetoMealHelper(500f,25f,null,40f)
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
            createTestMealsForKetoMealHelper(null,100f,900f,40f),
            createTestMealsForKetoMealHelper(500f,25f,48.3f,42f),
            createTestMealsForKetoMealHelper(500f,11f,400f,60f)
        )

        // When
        val result = getKetoDietMealUseCase.getSuggestedKetoMeal(
            setOf(
                createTestMealsForKetoMealHelper(500f,25f,48.3f,42f) //repeated keto meal
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

    @Test
    fun `should return an exception when keto meal list is empty`(){
        // Given
        every{mealRepository.getMeals()} returns listOf()

        // When
        val result  = getKetoDietMealUseCase.getSuggestedKetoMeal(emptySet())

        // Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    companion object{
        fun createTestMealsForKetoMealHelper(
            calories : Float?,
            protein : Float?,
            totalFat : Float?,
            carbohydrates : Float?
        ) = Meal(
            mealName = null,
            mealId = "1",
            minutesForPreparation = 30,
            contributorId = "1",
            nutrition = Nutrition(
                calories,totalFat,null,null,protein,null,carbohydrates
            ),
            description = null,
            numberOfIngredients = 3,
            numberOfSteps = 3,
            steps = listOf("do", "do", "do"),
            ingredients = listOf("do", "do", "do"),
            tags = listOf("do", "do", "do"),
            submittedDate = LocalDate(2000,12,5)

        )
    }

}