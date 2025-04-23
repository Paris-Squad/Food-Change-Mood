package org.example.presentaion.guessIngredient

import domain.model.Meal
import domain.usecase.GetIngredientGuessUseCase.GetUniqueRandomMealUseCase
import org.example.domain.model.IngredientGameRound
import org.example.domain.usecase.GetIngredientGuessUseCase.GetRandomWrongIngredientsUseCase

class IngredientGuessPresenter(
    private val getUniqueRandomMealUseCase: GetUniqueRandomMealUseCase,
    private val getRandomWrongIngredientsUseCase: GetRandomWrongIngredientsUseCase
    ):IngredientGuessInteractionListener {



   fun getRandomMeal(usedMeals:Set<String>):Result<Meal>{
       val randomMeal=getUniqueRandomMealUseCase.getUniqueRandomMeal(usedMeals)

      return randomMeal.fold(
           onSuccess ={Result.success(it)},
           onFailure ={Result.failure(it)}
       )
   }

    fun nextRound(meal: Meal,number: Int): IngredientGameRound {
        val correctIngredient = meal.ingredients.random()
        val wrongIngredients =getRandomWrongIngredientsUseCase.getRandomWrongIngredients(meal,number)
        val options = (listOf(correctIngredient) + wrongIngredients).shuffled()
        val currentRound = IngredientGameRound(meal.mealName!!, options, correctIngredient)
        return currentRound
    }

    override fun read(): Int {
        val guess = readLine()?.toIntOrNull()
        return guess ?: -1
    }


}