package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import kotlinx.datetime.LocalDate
import org.example.domain.MealException
import domain.model.Meal


class SearchMealsByAddDateUseCase(private val mealRepository: MealRepository) {

    operator fun invoke(date: LocalDate): Result<List<Meal>> {
        val meals = mealRepository.getMeals().filter { it.submittedDate == date }

        return if (meals.isNotEmpty()) {
            Result.success(meals)
        } else {
            Result.failure(MealException.NoMealsFoundException("No meals found on $date"))
        }
    }

    fun findMealByIdInList(id: String, meals: List<Meal>): Result<Meal> {
        return meals.find { it.mealId == id }
            ?.let { Result.success(it) }
            ?: Result.failure(MealException.NoMealsFoundException("Meal with ID $id not found"))
    }

}