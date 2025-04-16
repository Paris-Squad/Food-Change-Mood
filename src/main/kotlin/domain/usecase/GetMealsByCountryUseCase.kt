package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food


class GetMealsByCountryUseCase(private val repository: FoodRepository) {

    fun getMealsByCountry(country: String, count: Int): Result<List<Food>> {
        val meals = repository.getFood()
            .filter { food -> isRelatedToCountry(food, country) }
            .shuffled()
            .take(minOf(count, 20))
            .sortedBy{it.name}

        if (meals.isEmpty()) {
            throw FoodException.NoMealsFoundForCountry(country)
        }

        return Result.success(meals)
    }

    private fun isRelatedToCountry(food: Food, country: String): Boolean {
        return listOfNotNull(
            food.name,
            food.description,
            *food.tags.toTypedArray(),
            *food.ingredients.toTypedArray(),
            *food.steps.toTypedArray()
        ).any { it.contains(country, ignoreCase = true) }
    }

}
