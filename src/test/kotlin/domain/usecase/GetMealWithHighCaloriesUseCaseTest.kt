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



}