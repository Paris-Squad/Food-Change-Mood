package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.model.Meal


class GetMealsByCountryUseCase(private val repository: MealRepository) {

    fun getMealsByCountry(country: String, count: Int): Result<List<Meal>> {
        val meals = repository.getMeals()
            .filter { food -> isRelatedToCountry(food, country) }
            .shuffled()
            .take(minOf(count, 20))
            .sortedBy{it.mealName}

        if (meals.isEmpty()) {
            throw MealException.NoMealsFoundForCountry(country)
        }

        return Result.success(meals)
    }

    private fun isRelatedToCountry(food: Meal, country: String): Boolean {
        return listOfNotNull(
            food.mealName,
            food.description,
            *food.tags.toTypedArray(),
        ).any { it.contains(country, ignoreCase = true) }
    }

}
