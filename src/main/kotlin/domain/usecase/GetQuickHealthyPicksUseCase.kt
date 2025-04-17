package org.example.domain.usecase

import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetQuickHealthyPicksUseCase (private val repository : FoodRepository) {
    private fun getAllFood(): List<Food> = repository.getFood()
    fun quickHealthyPicks():List<Food>{
        return  getAllFood()
            .filter(::isValidQuickMeal)
            .sortedBy{ it.nutrition.totalFat + it.nutrition.saturatedFat + it.nutrition.carbohydrates }
    }
    private fun isValidQuickMeal(food: Food):Boolean{
        return food.minutes<=15 && food.name != null
    }

}