package org.example.di

import org.example.presentaion.MealConsoleUI
import org.example.presentaion.presenter.EggFreeSweetsPresenter
import org.example.presentaion.presenter.EasyMealSuggestionPresenter
import org.example.presentaion.presenter.IngredientGuessPresenter
import org.example.presentaion.presenter.IraqiMealsPresenter
import org.example.presentaion.presenter.ItalianLargeGroupMealsPresenter
import org.example.presentaion.presenter.MealWithHighCaloriesPresenter
import org.example.presentaion.presenter.MealsByCountryPresenter
import org.example.presentaion.presenter.QuickHealthyPicksPresenter
import org.example.presentaion.presenter.SeafoodMealsPresenter
import org.example.presentaion.presenter.guessPreparationTime.GuessPreparationTimeGamePresenter
import org.example.presentaion.presenter.guessPreparationTime.GuessPreparationTimeGameGameUI
import org.example.presentaion.presenter.guessPreparationTime.GuessPreparationTimeGameView
import org.example.presentaion.presenter.GymHelperConsolePresenter
import org.example.presentaion.presenter.KetoDietMealHelperPresenter
import org.example.presentaion.presenter.RandomPotatoMealsPresenter
import org.example.presentaion.presenter.SearchByMealNamePresenter
import org.example.presentaion.presenter.SearchMealsByAddDatePresenter
import org.example.presentaion.presenter.UIContainer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.dsl.bind

val uiModule = module {
    singleOf(::EggFreeSweetsPresenter)
    singleOf(::EasyMealSuggestionPresenter)
    singleOf(::IraqiMealsPresenter)
    singleOf(::MealsByCountryPresenter)
    singleOf(::SeafoodMealsPresenter)
    singleOf(::GuessPreparationTimeGamePresenter)
    singleOf(::RandomPotatoMealsPresenter)
    singleOf(::GymHelperConsolePresenter)
    singleOf(::SearchMealsByAddDatePresenter)
    singleOf(::IngredientGuessPresenter)
    singleOf(::QuickHealthyPicksPresenter)
    singleOf(::ItalianLargeGroupMealsPresenter)
    singleOf(::MealWithHighCaloriesPresenter)
    singleOf(::KetoDietMealHelperPresenter)
    singleOf(::SearchByMealNamePresenter)
    singleOf(::UIContainer)

    singleOf(::MealConsoleUI)
    singleOf(::GuessPreparationTimeGameGameUI) bind GuessPreparationTimeGameView::class
}