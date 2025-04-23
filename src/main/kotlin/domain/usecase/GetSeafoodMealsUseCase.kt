package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository

class GetSeafoodMealsUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(): Result<List<Pair<String, Float>>> {
        val allSeafood = mealRepository.getMeals().filter {
            it.tags.contains(SEAFOOD) && it.mealName != null && it.nutrition.protein != null
        }.sortedByDescending {
            it.nutrition.protein!!
        }.map { seafoodMeal ->
            seafoodMeal.mealName!! to seafoodMeal.nutrition.protein!!
        }

        if (allSeafood.isEmpty()) return Result.failure(MealException.NoMealsFoundException("No Seafood meals found"))

        return Result.success(allSeafood)
    }

    companion object{
        const val SEAFOOD = "seafood"
    }
}