package org.example.domain.usecase

import org.example.domain.repository.MealRepository
import org.example.model.Meal

class GetQuickHealthyPicksUseCase (private val repository : MealRepository) {
    private fun getAllFood(): List<Meal> = repository.getMeals()
    fun quickHealthyPicks():List<Meal>{
        return  getAllFood()
            .filter(::isValidQuickMeal)
            .sortedBy{ it.nutrition.totalFat!! + it.nutrition.saturatedFat!! + it.nutrition.carbohydrates!! }
    }
    private fun isValidQuickMeal(meal: Meal):Boolean{
        return meal.minutesForPreparation<=15
                && meal.mealName != null
                && meal.nutrition.totalFat !=null
                && meal.nutrition.saturatedFat !=null
                && meal.nutrition.carbohydrates !=null
    }

}