package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food

    class GetIraqiMealsUseCase(private val repository: FoodRepository) {

        fun getIraqiMeals(): List<Food> {
            val iraqiMeals = repository.getFood().filter { food ->
                isIraqiMeal(food)
            }

            if (iraqiMeals.isEmpty()) {
                throw FoodException.NoIraqiFoodFound()
            }

            return iraqiMeals
        }

        private fun isIraqiMeal(food: Food): Boolean {
            val taggedWithIraqi = food.tags.any { it.equals("iraqi", ignoreCase = true) }
            val descriptionContainsIraq = food.description?.contains("Iraq", ignoreCase = true) == true
            return taggedWithIraqi || descriptionContainsIraq
        }
    }
