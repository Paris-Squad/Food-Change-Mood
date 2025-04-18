package org.example.presentaion

import org.example.domain.usecase.GymHelperUseCase


class GymHelperConsoleUi(
    private val gymHelperUseCase: GymHelperUseCase
) {
    fun start() {

        var calories: Float? = null
        val protein: Float?

        while (true) {
            if (calories == null) {
                print("Enter the calories amount u want:  ")
                calories = readln().toFloatOrNull() ?: continue
            }

            print("Enter the protein amount u want:  ")
            protein = readln().toFloatOrNull() ?: continue

            val gymMeals = gymHelperUseCase.execute(calories = calories, protein = protein)

            if (gymMeals.isEmpty()) {
                println("No meals founded with this values calories:$calories, protein:$protein")
            } else {
                gymMeals.forEach { meal -> println(meal.formatDetails()) }
            }

            return
        }
    }
}