package org.example.domain.usecase

import org.example.domain.repository.FoodRepository
import kotlinx.datetime.LocalDate
import org.example.domain.FoodException
import org.example.model.Food


class SearchMealsByAddDateUseCase(private val repository: FoodRepository) {
    private var meals: List<Food> = emptyList()

    fun findMealsByDate(date: LocalDate): Result<List<Food>> {
         meals = repository.getFood().filter { it.submitted == date }

        return if (meals.isNotEmpty()) {
            Result.success(meals)
        } else {
            Result.failure(FoodException.NoMealsFoundException("No meals found on $date"))
        }
    }

    fun findMealByIdInList(id: String): Result<Food> {
        return meals.find { it.id == id }
            ?.let { Result.success(it) }
            ?: Result.failure(FoodException.NoMealsFoundException("Meal with ID $id not found"))
    }

}