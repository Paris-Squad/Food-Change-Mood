package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Meal

class GetRandomPotatoMealsUseCase(private val repository: FoodRepository) {
    companion object {
        private const val POTATO = "potato"
        private const val NUMBER_OF_MEALS = 10
    }

    fun getRandomPotatoMeals(): List<Meal> {
        val potatoMeals = getMealsContainingPotato()
        if (potatoMeals.isEmpty()) throw FoodException.NoPotatoMealFound()
        return potatoMeals.shuffled().take(NUMBER_OF_MEALS)
    }

    private fun getMealsContainingPotato(): List<Meal> {
        return repository.getFood()
            .filter (::isMealContainsPotato)
    }

    private fun isMealContainsPotato(food: Meal): Boolean =
        food.ingredients.any { ingredient ->
            ingredient.contains(POTATO, ignoreCase = true)
        }
}


