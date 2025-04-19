package org.example.presentaion

import org.koin.java.KoinJavaComponent.getKoin
import kotlin.system.exitProcess

class MealConsoleUI {


//    1- Allow the user to get a list of healthy fast food meals that can be prepared in 15 minutes or less, with very low total fat, saturated fat, and carbohydrate values compared to other meals in the dataset.
//    2- Enable meal search by name. Keep in mind that users may not remember the full meal name. Using .contains() may fail if there is a typo in the keyword. Also, due to the large dataset, choose a fast text search algorithm. I recommend the Knuth-Morris-Pratt algorithm for its speed, but feel free to use a better alternative in terms of both performance and accuracy.
//    3- Identify Iraqi meals in the dataset. A meal is considered Iraqi if it is tagged with "iraqi" or if the description contains the word "Iraq".
//    4- Easy Food Suggestion: Like a fun game, this feature suggests 10 random meals that are easy to prepare. A meal is considered easy if it requires 30 minutes or less, has 5 ingredients or fewer, and can be prepared in 6 steps or fewer.
//    5- Guess Game: Show the user a random meal name and ask them to guess its preparation time. The user has 3 attempts. After each attempt, indicate whether the guessed time is correct, too low, or too high. If all attempts are incorrect, show the correct time.
//    6- Sweets with No Eggs: For users allergic to eggs, suggest one sweet (name and description) that contains no eggs. The user can either like it (to view full details) or dislike it (to get another random egg-free sweet). Ensure the same sweet is not suggested more than once.
//    7- Keto Diet Meal Helper: Using the same mechanism as in point 6, suggest one keto-friendly meal at a time (without repetition). Base your logic on nutritional information. Please research keto diet requirements before implementation.
//    8- Search Foods by Add Date: Use Kotlin’s Date class to represent the date in the meal entity. Let the user input a date and return a list of IDs and names of meals added on that date. The user should be able to view details of a specific meal by entering its ID. Handle exceptions for:
//    - Incorrect date format.
//    - No meals were found for the given date. Ensure different exceptions are used for both cases.
//    9- Gym Helper: Allow the user to input a desired amount of calories and protein, and return a list of meals that match or approximate those values.
//    10- Explore Other Countries' Food Culture: Let the user enter a country name, then search any relevant column to return up to 20 randomly ordered meals related to that country.
//    11- Ingredient Game: Display a meal name and three ingredient options (one correct, two incorrect). The user guesses once. A correct guess earns 1000 points; an incorrect guess ends the game. The game also ends after 15 correct answers. Display the final score at the end.
//    12- I Love Potato: Show a random list of 10 meals that include potatoes in their ingredients.
//    13- So Thin Problem: Suggest a meal with more than 700 calories using a logic similar to point 6.
//    14- Show a list of all seafood meals sorted by protein content, from highest to lowest. Each result should display the rank (starting from 1), meal name, and protein amount.
//    15- A large group of friends traveling to Italy want to share a meal suitable for "for-large-groups", and it must be an original Italian dish, write a function to help them by suggesting as many as possible Italian food that are suitable for large groups.
//

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
        val easyMealSuggestionUi = getKoin().get<GetEasyMealSuggestionConsoleUi>()
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
            val mealsWithHighCaloriesConsoleUi = getKoin().get<GetMealWithHighCaloriesConsoleUi>()
            println("Are you like this meal?\npress yes if you liked it and No if you do not liked it")
            val userInput = readlnOrNull()
            userInput?.let { input ->

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