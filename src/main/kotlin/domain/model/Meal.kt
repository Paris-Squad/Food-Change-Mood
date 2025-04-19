package domain.model

import kotlinx.datetime.LocalDate

data class Meal(
    val mealName: String?,
    val mealId: String,
    val minutesForPreparation: Int,
    val contributorId: String,
    val submittedDate: LocalDate,
    val tags: List<String>,
    val nutrition: Nutrition,
    val numberOfSteps: Int,
    val steps: List<String>,
    val description: String?,
    val ingredients: List<String>,
    val numberOfIngredients: Int
)