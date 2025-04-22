package org.example.domain.usecase

import org.example.domain.MealException
import org.example.domain.repository.MealRepository
import domain.model.Meal
import kotlin.random.Random

class GetRandomMealUseCase(private val mealRepository: MealRepository) {
    operator fun invoke(): Result<Meal> {
        val meals = mealRepository.getMeals()

        if (meals.isEmpty()) return Result.failure(MealException.NoMealsFoundException("No Meals Available."))

        return Result.success(meals[Random.nextInt(meals.size)])
    }
}