package org.example.data

import org.example.domain.repository.FoodRepository
import org.example.model.Meal

class FakeRepositoryImpl : FoodRepository {
    override fun getFood(): List<Meal> = FakeFoodList
}