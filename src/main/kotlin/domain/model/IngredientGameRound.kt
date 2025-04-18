package org.example.domain.model

data class IngredientGameRound (
    val mealName: String,
    val options: List<String>,
    val correctAnswer: String
)