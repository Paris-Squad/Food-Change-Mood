package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository

class GetSeafoodMealsUseCase(
    private val foodRepository: MealRepository
) {
    fun execute(): List<Pair<String, Float>> {
        val allSeafood = foodRepository.getMeals().filter {
            it.tags.contains(SEAFOOD) && it.mealName != null && it.nutrition.protein != null
        }.sortedByDescending {
            it.nutrition.protein!!
        }.map { seafoodMeal ->
            seafoodMeal.mealName!! to (seafoodMeal.nutrition.protein ?: 0f)
        }

        return allSeafood.takeIf { it.isNotEmpty() } ?: throw MealException.NoMealsFoundException("No Seafood meals found")

    }

    companion object{
        const val SEAFOOD = "seafood"
    }
}