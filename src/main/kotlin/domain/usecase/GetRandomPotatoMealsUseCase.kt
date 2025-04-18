package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository

class GetRandomPotatoMealsUseCase(private val repository: MealRepository) {
    companion object {
        private const val POTATO = "potato"
        private const val NUMBER_OF_MEALS = 10
        private const val EXCEPTION_MESSAGE_NO_MEALS_FOUND = "No meals found containing potatoes"
    }

    fun getRandomPotatoMeals(): Result<List<Meal>> {
        val potatoMeals = getMealsContainingPotato()
        if (potatoMeals.isEmpty()) return Result.failure(
            MealException.NoMealsFoundException(EXCEPTION_MESSAGE_NO_MEALS_FOUND)
        )
        return Result.success(potatoMeals.shuffled().take(NUMBER_OF_MEALS))
    }

    private fun getMealsContainingPotato(): List<Meal> {
        return repository.getMeals()
            .filter(::isMealContainsPotato)
    }

    private fun isMealContainsPotato(meal: Meal): Boolean =
        meal.ingredients.any { ingredient ->
            ingredient.contains(POTATO, ignoreCase = true)
        }
}


