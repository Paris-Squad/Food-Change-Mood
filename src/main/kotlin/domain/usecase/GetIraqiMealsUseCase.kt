package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.model.Meal

class GetIraqiMealsUseCase(private val repository: MealRepository) {

    fun getIraqiMeals(): Result<List<Meal>> {
        val iraqiMeals = repository.getMeals().filter { meal ->
            isIraqiMeal(meal)
        }

        if (iraqiMeals.isEmpty()) {
            throw MealException.NoIraqiMealsFound()
        }

        return Result.success(iraqiMeals)
    }

    private fun isIraqiMeal(meal: Meal): Boolean {
        val taggedWithIraqi = meal.tags.any { it.equals(IRAQI, ignoreCase = true) }
        val descriptionContainsIraq = meal.description?.contains(IRAQ, ignoreCase = true) == true
        return taggedWithIraqi || descriptionContainsIraq
    }

    companion object {
        const val IRAQI = "iraqi"
        const val IRAQ = "Iraq"
    }
}
