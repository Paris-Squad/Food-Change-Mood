package org.example.presentaion

import org.example.utils.formatDetails
import org.koin.java.KoinJavaComponent.getKoin
import kotlin.system.exitProcess

class MealConsoleUI {
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
        val getQuickHealthyPicksUI = getKoin().get<GetQuickHealthyPicksConsoleUI>()
        getQuickHealthyPicksUI.invoke()
    }

    private fun searchMealByName() {
        val searchMealByNameUi = getKoin().get<SearchByMealNameConsoleUI>()
        searchMealByNameUi.invoke()
    }

    private fun identifyIraqiMeals() {
        val iraqiMealsUi = getKoin().get<GetIraqiMealsConsoleUi>()
        iraqiMealsUi.invoke()
    }

    private fun easyMealSuggestions() {
        val easyMealSuggestionUi = getKoin().get<GetEasyMealSuggestionInteractor>()
        easyMealSuggestionUi.invoke()
    }

    private fun guessGame() {
        val guessGameUi = getKoin().get<GuessGameConsoleUi>()
        guessGameUi.startGame()
    }

    private fun sweetsNoEggs() {
        val eggFreeSweetsUi = getKoin().get<EggFreeSweetsConsoleUi>()
        eggFreeSweetsUi.startSuggestions()
    }


    private fun ketoDietHelper() {
        val ketoDietMealHelper = getKoin().get<KetoDietMealHelperConsoleUi>()
        ketoDietMealHelper()
    }

    private fun searchByAddDate() {
        val searchMealsByAddDateConsoleUi = getKoin().get<SearchMealsByAddDateConsoleUi>()
        searchMealsByAddDateConsoleUi.search()
    }

    private fun gymHelper() {
        val gymHelperUI = getKoin().get<GymHelperConsoleUi>()
        gymHelperUI.start()
    }

    private fun exploreCountryMeal() {
        val getMealsByCountryUi = getKoin().get<GetMealsByCountryConsoleUi>()
        getMealsByCountryUi.invoke()
    }

    private fun ingredientGame() {
        val getIngredientGuessUI = getKoin().get<GetIngredientGuessUI>()
        getIngredientGuessUI.invoke()
    }

    private fun potatoLover() {
        val randomPotatoMealsUi = getKoin().get<RandomPotatoMealsConsoleUi>()
        randomPotatoMealsUi.invoke()
    }

    private fun highCalorieMeal() {

        while (true) {
            println("Are you like this meal?\npress yes if you liked it and No if you do not liked it")
            val userInput = readlnOrNull()
            userInput?.let { checkUserInputOnGetMealWithHighCalories(it) }
        }
    }

    private fun checkUserInputOnGetMealWithHighCalories(input: String) {
        val mealsWithHighCaloriesConsoleUi = getKoin().get<GetMealWithHighCaloriesConsoleUi>()
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
        val seafoodMealsUI = getKoin().get<GetSeafoodMealsConsoleUi>()
        seafoodMealsUI.start()
    }

    private fun italianLargeGroup() {
        val getItalianLargeGroupMealsUi = getKoin().get<GetItalianLargeGroupMealsConsoleUi>()
        getItalianLargeGroupMealsUi.invoke()
    }
}