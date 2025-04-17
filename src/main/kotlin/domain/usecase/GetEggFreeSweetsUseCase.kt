package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.model.Meal

class GetEggFreeSweetsUseCase(private val repository: MealRepository) {

    private val suggestedSweets = mutableSetOf<String>()

    fun getRandomEggFreeSweet(): Result<Meal> {
        val availableSweets = repository.getMeals()
            .filter { meal ->
                meal.tags.any { it.contains(DESSERT, ignoreCase = true) || it.contains(SWEET, ignoreCase = true) } &&
                !meal.ingredients.any { it.contains(EGG, ignoreCase = true) } &&
                !suggestedSweets.contains(meal.mealId)
            }

        return if (availableSweets.isEmpty()) {
            Result.failure(MealException.NoMoreSweetsAvailable("No more egg-free sweets available"))
        } else {
            val randomSweet = availableSweets.random()
            suggestedSweets.add(randomSweet.mealId)
            Result.success(randomSweet)
        }
    }

    companion object {
        const val DESSERT = "dessert"
        const val EGG = "egg"
        const val SWEET = "sweet"
    }

}