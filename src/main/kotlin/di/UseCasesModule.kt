package org.example.di

import domain.usecase.GetIngredientGuessUseCase.GetUniqueRandomMealUseCase
import org.example.domain.usecase.*
import org.example.domain.usecase.GetIngredientGuessUseCase.GetRandomWrongIngredientsUseCase
import org.koin.core.module.dsl.singleOf
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
    singleOf(::GetUniqueRandomMealUseCase)
    singleOf(::GetRandomWrongIngredientsUseCase)
    single { GetHealthyMealsUseCase(get()) }
    single { GymHelperUseCase(get()) }
    single {SearchMealsByAddDateUseCase(get())}
    single {GetMealWithHighCaloriesUseCase(get())}
    single { GetKetoDietMealUseCase(get()) }
    single {SearchByMealNameUseCase(get())}
}
