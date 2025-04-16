package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food

class GetEggFreeSweetsUseCase(private val repository: FoodRepository) {
    
    private val suggestedSweets = mutableSetOf<String>()
    
    fun getRandomEggFreeSweet(): Result<Food> {
        val availableSweets = repository.getFood()
            .filter { food -> 
                food.tags.any { it.contains("dessert", ignoreCase = true) || it.contains("sweet", ignoreCase = true) } &&
                !food.ingredients.any { it.contains("egg", ignoreCase = true) } &&
                !suggestedSweets.contains(food.id)
            }
        
        return if (availableSweets.isEmpty()) {
            Result.failure(FoodException.NoMoreSweetsAvailable("No more egg-free sweets available"))
        } else {
            val randomSweet = availableSweets.random()
            suggestedSweets.add(randomSweet.id)
            Result.success(randomSweet)
        }
    }

}