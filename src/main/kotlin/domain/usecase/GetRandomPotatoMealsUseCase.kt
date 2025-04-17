package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import org.example.model.Meal

class GetRandomPotatoMealsUseCase(private val repository: MealRepository) {
    companion object {
        private const val POTATO = "potato"
        private const val NUMBER_OF_MEALS = 10
    }

    fun getRandomPotatoMeals(): List<Meal> {
        val potatoMeals = getMealsContainingPotato()
        if (potatoMeals.isEmpty()) throw MealException.NoPotatoMealFound()
        return potatoMeals.shuffled().take(NUMBER_OF_MEALS)
    }

    private fun getMealsContainingPotato(): List<Meal> {
        return repository.getMeals()
            .filter (::isMealContainsPotato)
    }

    private fun isMealContainsPotato(meal: Meal): Boolean =
        meal.ingredients.any { ingredient ->
            ingredient.contains(POTATO, ignoreCase = true)
        }
}


