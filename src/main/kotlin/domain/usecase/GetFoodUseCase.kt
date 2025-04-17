package org.example.domain.usecase

import org.example.domain.repository.FoodRepository
import org.example.model.Meal

class GetFoodUseCase(private val repository : FoodRepository) {
    fun invoke():List<Meal> = repository.getFood()
}