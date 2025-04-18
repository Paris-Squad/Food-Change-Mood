package org.example.domain.repository

import domain.model.Meal

interface MealRepository {
    fun getMeals(): List<Meal>
}