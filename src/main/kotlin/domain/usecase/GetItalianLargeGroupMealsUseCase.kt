package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetItalianLargeGroupMealsUseCase (
    private val repository: FoodRepository
) {
    fun invoke(): Result<List<Food>> {
        val meals = repository.getFood().filter { food ->
            val tags = food.tags.map { it.lowercase() }
            ("italian" in tags || "italy" in tags)  && "for-large-groups" in tags
        }
        return if (meals.isNotEmpty()) {
            Result.success(meals.shuffled())
        } else {
            Result.failure(FoodException.NoItalianLargeGroupMealFound())
        }
    }
}