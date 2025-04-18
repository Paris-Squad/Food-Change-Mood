package org.example.di


import org.example.domain.usecase.*

import org.koin.dsl.module
import org.example.domain.usecase.GetRandomPotatoMealsUseCase

val useCasesModule = module {

    single { GetEasyMealSuggestionUseCase(get()) }
    single { GetEggFreeSweetsUseCase(get()) }
    single { GetMealUseCase(get()) }
    single { GetIraqiMealsUseCase(get()) }
    single { GetMealsByCountryUseCase(get()) }
    single { GetRandomPotatoMealsUseCase(get()) }
    single { GetItalianLargeGroupMealsUseCase(get()) }
    single { GetSeafoodMealsUseCase(get()) }
    single { GuessPreparationTimeGameUseCase(get()) }
    single { GetIngredientGuessUseCase(get()) }
    single { GetQuickHealthyPicksUseCase(get()) }
    single { GymHelperUseCase(get()) }
    single {SearchMealsByAddDateUseCase(get())}
    single { KetoDietMealHelperUseCase(get()) }

}