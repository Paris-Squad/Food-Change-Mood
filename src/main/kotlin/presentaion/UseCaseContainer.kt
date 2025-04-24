package org.example.presentaion

import org.example.presentaion.presenter.*

class UseCaseContainer(
    val quickHealthyPicksPresenter: QuickHealthyPicksPresenter,
    val searchByMealNamePresenter: SearchByMealNamePresenter,
    val iraqiMealsPresenter: IraqiMealsPresenter,
    val easyMealSuggestionPresenter: EasyMealSuggestionPresenter,
    val guessGameConsolePresenter: GuessGameConsolePresenter,
    val eggFreeSweetsPresenter: EggFreeSweetsPresenter,
    val ketoDietMealHelperPresenter: KetoDietMealHelperPresenter,
    val searchMealsByAddDatePresenter: SearchMealsByAddDatePresenter,
    val gymHelperConsolePresenter: GymHelperConsolePresenter,
    val mealsByCountryPresenter: MealsByCountryPresenter,
    val ingredientGuessPresenter: IngredientGuessPresenter,
    val randomPotatoMealsPresenter: RandomPotatoMealsPresenter,
    val mealWithHighCaloriesPresenter: MealWithHighCaloriesPresenter,
    val seafoodMealsPresenter: SeafoodMealsPresenter,
    val italianLargeGroupMealsPresenter: ItalianLargeGroupMealsPresenter
)