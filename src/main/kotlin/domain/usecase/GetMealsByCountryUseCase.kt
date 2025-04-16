package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food


class GetMealsByCountryUseCase(private val repository: FoodRepository) {

    fun getMealsByCountry(country: String): List<Food> {
        val meals = repository.getFood().filter { food ->
            isRelatedToCountry(food, country)
        }.shuffled().take(20)

        if (meals.isEmpty()) {
            throw FoodException.NoMealsFoundForCountry(country)
        }

        return meals
    }


    private fun isRelatedToCountry(food: Food, country: String): Boolean {

        return food.name?.contains(country, ignoreCase = true) == true ||
                food.description?.contains(country, ignoreCase = true) == true ||
                food.tags.any { it.equals(country, ignoreCase = true) } ||
                food.ingredients.any { it.contains(country, ignoreCase = true) } ||
                food.steps.any { it.contains(country, ignoreCase = true) }
    }
}