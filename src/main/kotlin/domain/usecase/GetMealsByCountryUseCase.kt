package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import domain.model.Meal


class GetMealsByCountryUseCase(private val repository: MealRepository) {

    fun getMealsByCountry(country: String?, count: Int?): Result<List<Meal>> {

        if (country.isNullOrBlank()) {
            return Result.failure(IllegalArgumentException("Country name cannot be empty."))
        }

        if (count == null || count <= 0) {
            return Result.failure(IllegalArgumentException("Invalid Count."))
        }

        val meals = repository.getMeals()
            .filter { meal -> isRelatedToCountry(meal, country) }
            .shuffled()
            .take(minOf(count, 20))
            .sortedBy{it.mealName}

        if (meals.isEmpty()) {
            return Result.failure(MealException.NoMealsFoundException("No meals found related to '$country'"))
        }

        return Result.success(meals)
    }

    private fun isRelatedToCountry(meal: Meal, country: String): Boolean {
        return listOfNotNull(
            meal.mealName,
            meal.description,
            *meal.tags.toTypedArray(),
        ).any { it.contains(country, ignoreCase = true) }
    }

}
