package org.example.domain.usecase

import org.example.domain.repository.FoodRepository
import org.example.model.Food
import org.example.utils.inRange

class GymHelperUseCase(
    private val foodRepository: FoodRepository
) {
    fun execute(calories: Float, protein: Float): List<Food> {
        val caloriesRange = (calories - 3).coerceAtLeast(0f)..(calories + 3)
        val proteinRange = (protein - 3).coerceAtLeast(0f)..(protein + 3)

        return foodRepository.getFood().filter { food ->
            food.nutrition.calories.inRange(caloriesRange) &&
                    food.nutrition.protein.inRange(proteinRange)
        }
    }
}