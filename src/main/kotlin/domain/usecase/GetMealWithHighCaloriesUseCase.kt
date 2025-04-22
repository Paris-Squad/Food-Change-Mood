package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository

open class GetMealWithHighCaloriesUseCase(private val mealRepository: MealRepository) {

    operator fun invoke(requiredCalories: Float): Result<Meal> {
        val getFoodWithHighCalories = getMealsWithRequiredCalories(requiredCalories)

        return if (getFoodWithHighCalories.isEmpty()) {
            Result.failure(MealException.NoMealsFoundException("No Meal Founded"))
        } else Result.success(getFoodWithHighCalories.random())
    }

    private fun getMealsWithRequiredCalories(requiredCalories: Float): List<Meal> {
        return mealRepository.getMeals().filter { meal ->
            meal.nutrition.calories == requiredCalories
        }
    }
}




