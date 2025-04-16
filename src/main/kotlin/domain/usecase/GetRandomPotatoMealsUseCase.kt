package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetRandomPotatoMealsUseCase(private val repository: FoodRepository) {
    companion object {
        private const val POTATO = "potato"
        private const val NUMBER_OF_MEALS = 10
    }

    fun getRandomPotatoMeals(): List<Food> {
        val potatoMeals = getMealsContainingPotato()
        if (potatoMeals.isEmpty()) throw FoodException.NoPotatoMealFound()
        return potatoMeals.shuffled().take(NUMBER_OF_MEALS)
    }

    private fun getMealsContainingPotato(): List<Food> {
        return repository.getFood()
            .filter { food ->
                foodContainsPotato(food)
            }
            .shuffled()
            .take(NUMBER_OF_MEALS)
            .filter (::isMealContainsPotato)
    }

    private fun foodContainsPotato(food: Food): Boolean =
        food.ingredients.any { ingredient -> ingredient.contains(POTATO, ignoreCase = true) }
    private fun isMealContainsPotato(food: Food): Boolean =
        food.ingredients.any { ingredient ->
            ingredient.contains(POTATO, ignoreCase = true)
        }
}



