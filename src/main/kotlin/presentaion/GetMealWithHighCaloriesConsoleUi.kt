package org.example.presentaion

import org.example.domain.MealException
import org.example.domain.usecase.GetMealWithHighCaloriesUseCase

class GetMealWithHighCaloriesConsoleUi(private val useCase: GetMealWithHighCaloriesUseCase) {

    operator fun invoke() {
        print("Are you like this meal ? \n if you like it enter 1 or 0 if you disLike \n")
        val userInput: Int? = readlnOrNull()?.trim()?.toIntOrNull()
        userInput?.let { inputValue ->
            when (inputValue) {
                1 -> useCase.invoke(requiredCalories = 700f).getOrNull()?.description
                0 -> useCase.invoke(requiredCalories = 700f)
                else -> validateUserInput(inputValue)
            }
        }
    }

    private fun validateUserInput(isLiked: Int) {
        try {
            println("Invalid number. Please enter 1 or 0")
        } catch (e: Exception) {
            throw MealException.NoMealsFoundException("No Meal Founded")
        }
    }

}