package org.example.domain.usecase

import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetFoodUseCase(private val repository : FoodRepository) {
    fun invoke():List<Food> = repository.getFood()
}