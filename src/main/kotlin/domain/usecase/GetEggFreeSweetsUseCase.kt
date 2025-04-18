package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import domain.model.Meal

class GetEggFreeSweetsUseCase(private val repository: MealRepository) {


    fun getRandomEggFreeSweet(): Result<Meal> {
        val availableSweets = repository.getMeals().filter { meal ->
            meal.tags.any {
                it.contains(DESSERT, ignoreCase = true) || it.contains(SWEET, ignoreCase = true)
            } && !meal.ingredients.any {
                it.contains(EGG, ignoreCase = true)
            }
        }

        return if (availableSweets.isEmpty()) {
            Result.failure(MealException.NoMealsFoundException("No more egg-free sweets available"))
        } else {
            Result.success(availableSweets.random())
        }
    }

    companion object {
        private const val DESSERT = "dessert"
        private const val SWEET = "sweet"
        private const val EGG = "egg"
    }
}