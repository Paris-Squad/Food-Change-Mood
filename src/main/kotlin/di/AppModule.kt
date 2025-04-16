package org.example.di

import org.example.data.FakeRepositoryImpl
import org.example.domain.repository.FoodRepository
import org.koin.dsl.module

val appModule = module {
    single { FakeRepositoryImpl() }
    single<FoodRepository> { FakeRepositoryImpl() }
}