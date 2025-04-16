package org.example.di

import org.example.data.FakeRepositoryImpl
import org.example.domain.repository.FoodRepository
import org.example.presentaion.GetFoodUi
import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase

val appModule = module {
    single { FakeRepositoryImpl() }
    single<FoodRepository> { FakeRepositoryImpl() }

    single { GetFoodUi(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
}