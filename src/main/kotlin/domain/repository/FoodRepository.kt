package org.example.domain.repository

import org.example.model.Meal

interface FoodRepository {
    fun getFood(): List<Meal>
}