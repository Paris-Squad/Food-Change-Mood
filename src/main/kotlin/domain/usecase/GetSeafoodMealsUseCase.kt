package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository

class GetSeafoodMealsUseCase(
    private val foodRepository: FoodRepository
) {
    fun execute(): List<Pair<String, Float>> {
        val allSeafood = foodRepository.getFood().filter {
            it.tags.contains("seafood") && it.name != null && it.nutrition.protein != null
        }.sortedByDescending {
            it.nutrition.protein!!
        }.map { seafoodMeal ->
            seafoodMeal.name!! to (seafoodMeal.nutrition.protein ?: 0f)
        }

        return allSeafood.takeIf { it.isNotEmpty() } ?: throw FoodException.NoSeaFoodMealsFound("No Seafood meals found")

    }
}