package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository


class SearchByMealNameUseCase(private val repository: MealRepository) {
    fun invoke(name: String): Result<List<Meal>> {
        val searchWords = name.trim().lowercase().split(" ")

        val result = repository.getMeals().filter { meal ->
            val foodWords = meal.mealName?.lowercase()?.split(" ")

            if (foodWords.isNullOrEmpty()) {
                false
            } else {
                searchWords.all { searchTerm ->
                    foodWords.any { foodWord ->
                        levenshteinDistance(foodWord, searchTerm) <= 2
                    }
                }
            }
        }

        return if (result.isEmpty()) Result.failure(MealException.NoMealsFoundException("no food found matching '$name'"))
        else Result.success(result)
    }

    private fun levenshteinDistance(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

        for (i in 0..s1.length) dp[i][0] = i

        for (j in 0..s2.length) dp[0][j] = j

        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = minOf(dp[i - 1][j], dp[i][j - 1]) + 1
                }

            }
        }

        return dp[s1.length][s2.length]
    }
}