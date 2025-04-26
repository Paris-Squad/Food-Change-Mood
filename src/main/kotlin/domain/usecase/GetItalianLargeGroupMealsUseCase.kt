package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository

class GetItalianLargeGroupMealsUseCase(private val mealRepository: MealRepository) {
    fun invoke(): Result<List<Meal>> {
        val meals = mealRepository.getMeals()
            .filter(::isItalianAndForLargeGroups)
        return if (meals.isNotEmpty()) {
            Result.success(meals.shuffled())
        } else {
            Result.failure(MealException.NoMealsFoundException("No Italian meals suitable for large groups were found."))
        }
    }

    private fun isItalianAndForLargeGroups(meal: Meal): Boolean {
        val lowercaseTags = meal.tags.map { it.lowercase() }
        val isItalian = ITALIAN in lowercaseTags || ITALY in lowercaseTags
        val isForLargeGroups = FOR_LARGE_GROUPS in lowercaseTags
        return isItalian && isForLargeGroups
    }

    companion object {
        const val ITALIAN = "italian"
        const val ITALY = "italy"
        const val FOR_LARGE_GROUPS = "for-large-groups"
    }
}