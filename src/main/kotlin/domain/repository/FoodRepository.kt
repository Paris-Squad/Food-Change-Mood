package org.example.domain.repository

import org.example.model.Food

interface FoodRepository {
    fun getFood(): List<Food>
}