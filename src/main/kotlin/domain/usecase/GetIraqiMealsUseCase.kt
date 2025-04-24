package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import domain.model.Meal

class GetIraqiMealsUseCase(private val mealRepository: MealRepository) {

    fun getIraqiMeals(): Result<List<Meal>> {
        val iraqiMeals = mealRepository.getMeals().filter { meal ->
            isIraqiMeal(meal)
        }

        if (iraqiMeals.isEmpty()) {
            return Result.failure(MealException.NoMealsFoundException("No Iraqi Meals found"))
        }

        return Result.success(iraqiMeals)
    }

    private fun isIraqiMeal(meal: Meal): Boolean {
        return hasIraqiTag(meal) || hasIraqInDescription(meal)
    }

    private fun hasIraqiTag(meal: Meal): Boolean {
        return meal.tags.any { it.equals(IRAQI, ignoreCase = true) }
    }

    private fun hasIraqInDescription(meal: Meal): Boolean {
        return meal.description?.contains(IRAQ, ignoreCase = true) == true
    }

    companion object {
        const val IRAQI = "iraqi"
        const val IRAQ = "Iraq"
    }
}
