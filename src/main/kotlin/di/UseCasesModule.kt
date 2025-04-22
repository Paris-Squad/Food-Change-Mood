package org.example.di

import org.example.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {
    single { GetEasyMealSuggestionUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetItalianLargeGroupMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GetRandomMealUseCase(get()) }
    single { GetIngredientGuessUseCase(get()) }
    single { GetQuickHealthyPicksUseCase(get()) }
    single { GymHelperUseCase(get()) }
    single {SearchMealsByAddDateUseCase(get())}
    single {GetMealWithHighCaloriesUseCase(get())}
    single { GetKetoDietMealUseCase(get()) }
    single {SearchByMealNameUseCase(get())}
}
