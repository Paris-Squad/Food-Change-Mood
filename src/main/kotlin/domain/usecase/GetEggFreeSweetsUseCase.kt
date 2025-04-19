package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import domain.model.Meal

class GetEggFreeSweetsUseCase(private val mealRepository: MealRepository) {
    fun getRandomEggFreeSweet(): Result<Meal> =
        if (filterEggFreeSweets().isEmpty()) {
            Result.failure(MealException.NoMealsFoundException("No more egg-free sweets available"))
        } else {
            Result.success(filterEggFreeSweets().random())
        }

    private fun filterEggFreeSweets(): List<Meal> = mealRepository.getMeals().filter { meal ->
        meal.tags.any {
            it.contains(DESSERT, ignoreCase = true) || it.contains(SWEET, ignoreCase = true)
        } && !meal.ingredients.any {
            it.contains(EGG, ignoreCase = true)
        }
    }

    companion object {
        private const val DESSERT = "dessert"
        private const val SWEET = "sweet"
        private const val EGG = "egg"
    }
}