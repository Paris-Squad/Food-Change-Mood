package org.example.domain.repository

import org.example.model.Meal

interface MealRepository {
    fun getMeals(): List<Meal>
}