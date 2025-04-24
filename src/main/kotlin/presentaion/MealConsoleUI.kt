package org.example.presentaion

import org.example.utils.formatDetails
import kotlin.system.exitProcess

class MealConsoleUI(private val useCaseContainer: UseCaseContainer) {
    fun start() {
        println("Welcome to the meal app where u can search and find ur meals recipe.")
        while (true) {
            println(
                """
            === Food Recommendation System ===
            Choose an option:
            1. List healthy fast meals
            2. Search meal by name
            3. Identify Iraqi meals
            4. Easy meal suggestions
            5. Guess preparation time game
            6. Sweets with no eggs
            7. Keto diet meal helper
            8. Search foods by add date
            9. Gym helper
            10. Explore meals by country
            11. Ingredient guessing game
            12. I love potato
            13. High calorie meal suggestion
            14. Seafood meals by protein
            15. Italian meals for large groups
            0. Exit
            """.trimIndent()
            )

            print("Enter your choice from the above list: ")
            val input = readlnOrNull()

            when (input) {
                "1" -> listHealthyFastMeal()
                "2" -> searchMealByName()
                "3" -> identifyIraqiMeals()
                "4" -> easyMealSuggestions()
                "5" -> guessGame()
                "6" -> sweetsNoEggs()
                "7" -> ketoDietHelper()
                "8" -> searchByAddDate()
                "9" -> gymHelper()
                "10" -> exploreCountryMeal()
                "11" -> ingredientGame()
                "12" -> potatoLover()
                "13" -> highCalorieMeal()
                "14" -> seafoodByProtein()
                "15" -> italianLargeGroup()
                "0" -> {
                    println("Thanks for using our app!")
                    break
                }

                else -> println("Invalid input. Please enter a number from 0 to 15.")
            }

            println("\n-------------------------------\n")
        }
    }

    private fun listHealthyFastMeal() {
        useCaseContainer.quickHealthyPicksPresenter.presentQuickHealthyMeals()
    }

    private fun searchMealByName() {
        useCaseContainer.searchByMealNamePresenter.startSearchByName()
    }

    private fun identifyIraqiMeals() {
        useCaseContainer.iraqiMealsPresenter.getIraqiMeals()
    }

    private fun easyMealSuggestions() {
        useCaseContainer.easyMealSuggestionPresenter.getEasyMeals()
    }

    private fun guessGame() {
        val guessGameUi = useCaseContainer.guessGameConsolePresenter
        guessGameUi.startGame()

        while (guessGameUi.isGameActive()) {
            val input = readlnOrNull() ?: ""
            guessGameUi.processGuess(input)
        }
    }

    private fun sweetsNoEggs() {
        useCaseContainer.eggFreeSweetsPresenter.startSuggestions()
    }

    private fun ketoDietHelper() {
        useCaseContainer.ketoDietMealHelperPresenter.startKetoHelper()
    }

    private fun searchByAddDate() {
        useCaseContainer.searchMealsByAddDatePresenter.searchMealsByCreationDate()
    }

    private fun gymHelper() {
        print("Enter the calories amount u want:  ")
        val calories = readln().toFloatOrNull() ?: return
        print("Enter the protein amount u want:  ")
        val protein = readln().toFloatOrNull() ?: return
        useCaseContainer.gymHelperConsolePresenter.presentGymMeals(calories, protein)
    }

    private fun exploreCountryMeal() {
        useCaseContainer.mealsByCountryPresenter.getMealsByCountry()
    }

    private fun ingredientGame() {
        useCaseContainer.ingredientGuessPresenter.startIngredientGuess()
    }

    private fun potatoLover() {
        useCaseContainer.randomPotatoMealsPresenter.startRandomPotatoMeals()
    }

    private fun highCalorieMeal() {
        while (true) {
            println("Are you like this meal?\npress yes if you liked it and No if you do not liked it")
            val userInput = readlnOrNull()
            userInput?.let { checkUserInputOnGetMealWithHighCalories(it) }
        }
    }

    private fun checkUserInputOnGetMealWithHighCalories(input: String) {
        val mealsWithHighCaloriesConsoleUi = useCaseContainer.mealWithHighCaloriesPresenter
        if (input.equals("yes", false)) {
            mealsWithHighCaloriesConsoleUi.randomMealWithHighCalories?.let { randomMeal ->
                println(randomMeal.formatDetails())
                exitProcess(0)
            }
        } else if (input.equals("no", false)) {
            mealsWithHighCaloriesConsoleUi.getMealsWithHighCalories()
        } else {
            println("Invalid input format")
        }
    }

    private fun seafoodByProtein() {
        useCaseContainer.seafoodMealsPresenter.presentSeafoodMeals()
    }

    private fun italianLargeGroup() {
        useCaseContainer.italianLargeGroupMealsPresenter.startItalianLargeGroupMeal()
    }
}