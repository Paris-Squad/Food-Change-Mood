package org.example.di

import org.example.presentaion.presenter.EggFreeSweetsPresenter
import org.example.presentaion.presenter.GetEasyMealSuggestionPresenter
import org.example.presentaion.presenter.GetIngredientGuessPresenter
import org.example.presentaion.presenter.GetIraqiMealsPresenter
import org.example.presentaion.presenter.GetItalianLargeGroupMealsPresenter
import org.example.presentaion.presenter.GetMealWithHighCaloriesPresenter
import org.example.presentaion.presenter.GetMealsByCountryPresenter
import org.example.presentaion.presenter.GetQuickHealthyPicksPresenter
import org.example.presentaion.presenter.GetSeafoodMealsPresenter
import org.example.presentaion.presenter.GuessGameConsolePresenter
import org.example.presentaion.presenter.GymHelperConsolePresenter
import org.example.presentaion.presenter.KetoDietMealHelperPresenter
import org.example.presentaion.presenter.RandomPotatoMealsPresenter
import org.example.presentaion.presenter.SearchByMealNamePresenter
import org.example.presentaion.presenter.SearchMealsByAddDatePresenter
import org.koin.dsl.module

val uiModule = module {
    single { EggFreeSweetsPresenter(get()) }
    single { GetEasyMealSuggestionPresenter(get()) }
    single { GetIraqiMealsPresenter(get()) }
    single { GetMealsByCountryPresenter(get()) }
    single { GetSeafoodMealsPresenter(get()) }
    single { GuessGameConsolePresenter(get()) }
    single { RandomPotatoMealsPresenter(get()) }
    single { GymHelperConsolePresenter(get()) }
    single { SearchMealsByAddDatePresenter(get()) }
    single { GetIngredientGuessPresenter(get()) }
    single { GetQuickHealthyPicksPresenter(get()) }
    single { GetItalianLargeGroupMealsPresenter(get()) }
    single { GetMealWithHighCaloriesPresenter(get()) }
    single { KetoDietMealHelperPresenter(get()) }
    single { SearchByMealNamePresenter(get()) }
}

