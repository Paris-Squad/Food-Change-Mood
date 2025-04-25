package domain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.domain.usecase.GetItalianLargeGroupMealsUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetItalianLargeGroupMealsUseCaseTest {

    private lateinit var mealsRepository: MealRepository
    private lateinit var italianMealsUseCase: GetItalianLargeGroupMealsUseCase

    @BeforeEach
    fun setup() {
        mealsRepository = mockk(relaxed = true)
        italianMealsUseCase = GetItalianLargeGroupMealsUseCase(mealsRepository)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when empty list found`() {
        //Given
        every { mealsRepository.getMeals() } returns emptyList()

        //When
        val result = italianMealsUseCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when can't find any meal tagged with italian or italy`() {
        //Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidItalianMealsByUnTaggedItalianOrItaly

        //When
        val result = italianMealsUseCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)
    }

    @Throws(MealException.NoMealsFoundException::class)
    @Test
    fun `should return Failure Result of NoMealsFoundException when all meals tagged with italian or italy but no tagged with for-large-groups`() {
        //Given
        every { mealsRepository.getMeals() } returns MockMeals.invalidItalianMealsByUnTaggedForLargeGroup

        //When
        val result = italianMealsUseCase.invoke()

        //Then
        assertThat(result.exceptionOrNull()).isInstanceOf(MealException.NoMealsFoundException::class.java)

    }


    @Test
    fun `should return Success with all italian meals that tagged with for-large-groups and (italian or italy)`() {
        //Given
        val validItalianMeals = MockMeals.validItalianMealsTaggedByForLargeGroupAndItalianOrItaly
        every { mealsRepository.getMeals() } returns validItalianMeals

        //When
        val result = italianMealsUseCase.invoke()

        println("result---> $result")
        //Then
        assertThat(result.getOrNull()).containsExactlyElementsIn(validItalianMeals.dropLast(1))
    }
}