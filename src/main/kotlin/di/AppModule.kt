package org.example.di

import org.example.data.FoodRepositoryImpl
import org.example.domain.repository.FoodRepository
import org.example.presentaion.GetFoodUi
import org.koin.dsl.module

val appModule = module {
    single { FoodRepositoryImpl() }
    single<FoodRepository> { FoodRepositoryImpl() }
    
    single { GetFoodUi(get()) }
}