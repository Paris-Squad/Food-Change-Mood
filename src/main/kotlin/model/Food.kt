package org.example.model

import kotlinx.datetime.LocalDate

data class Food(
    val name: String?,
    val id: String,
    val minutes: Int,
    val contributorId: String,
    val submitted: LocalDate,
    val tags: List<String>,
    val nutrition: Nutrition,
    val numberOfSteps: Int,
    val steps: List<String>,
    val description: String?,
    val ingredients: List<String>,
    val numberOfIngredients: Int
)