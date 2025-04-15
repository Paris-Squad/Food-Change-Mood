package org.example.domain.usecase

import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetFoodUC(private val repository : FoodRepository) {
    operator fun invoke():List<Food> = repository.getFood()
}