package org.example.data

import org.example.domain.repository.FoodRepository
import org.example.model.Food

class FakeRepositoryImpl : FoodRepository {
    override fun getFood(): List<Food> = FakeFoodList
}