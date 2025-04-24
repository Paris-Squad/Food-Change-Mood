package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository

class GetRandomPotatoMealsUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(numberOFMeals: Int = DEFAULT_COUNT_OF_MEALS): Result<List<Meal>> {
        val potatoMeals = getMealsContainingPotato()
        if (potatoMeals.isEmpty()) return Result.failure(
            MealException.NoMealsFoundException(EXCEPTION_MESSAGE_NO_MEALS_FOUND)
        )
        else if (potatoMeals.size < numberOFMeals)
           return Result.failure(MealException.NoEnoughMealsFound("No Enough Potato Meals"))
        return Result.success(potatoMeals.shuffled().take(numberOFMeals))
    }

    private fun getMealsContainingPotato(): List<Meal> {
        return mealRepository.getMeals()
            .filter(::isMealContainsPotato)
    }

    private fun isMealContainsPotato(meal: Meal): Boolean =
        meal.ingredients.any { ingredient ->
            ingredient.contains(POTATO, ignoreCase = true)
        }

    companion object {
        private const val POTATO = "potato"
        private const val DEFAULT_COUNT_OF_MEALS = 10
        private const val EXCEPTION_MESSAGE_NO_MEALS_FOUND = "No meals found containing potatoes"
    }
}


