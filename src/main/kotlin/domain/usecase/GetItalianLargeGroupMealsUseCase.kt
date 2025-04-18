package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository

class GetItalianLargeGroupMealsUseCase(
    private val repository: MealRepository
) {
    fun invoke(): Result<List<Meal>> {
        val meals = repository.getMeals().filter { meal ->
            val tags = meal.tags.map { it.lowercase() }
            (ITALIAN in tags || ITALY in tags) && FOR_LARGE_GROUPS in tags
        }
        return if (meals.isNotEmpty()) {
            Result.success(meals.shuffled())
        } else {
            Result.failure(MealException.NoMealsFoundException("No Italian meals suitable for large groups were found."))
        }
    }

    companion object {
        const val ITALIAN = "italian"
        const val ITALY = "italy"
        const val FOR_LARGE_GROUPS = "for-large-groups"
    }
}