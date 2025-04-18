package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food


open class GetMealWithHighCaloriesUseCase(private val foodRepository: FoodRepository) {

    fun invoke(requiredCalories : Float) : Result<Food> {
      val getFoodWithHighCalories = getMealsWithRequiredCalories(requiredCalories)

        return if (getFoodWithHighCalories.isEmpty()) {
            Result.failure(FoodException.NoMealWith700Calories())
        } else Result.success(getFoodWithHighCalories.random())
    }

    private fun getMealsWithRequiredCalories(requiredCalories: Float) : List<Food> {
        return foodRepository.getFood().filter { meal ->
            meal.nutrition.calories == requiredCalories
            }
    }
}




