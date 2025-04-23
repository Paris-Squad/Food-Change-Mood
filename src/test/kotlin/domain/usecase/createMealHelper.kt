package domain.usecase

import domain.model.Meal
import domain.model.Nutrition
import kotlinx.datetime.LocalDate
import java.util.*

fun createMeal(
    name: String, ingredients: List<String>
) =
    Meal(
        mealName = name,
        mealId = UUID.randomUUID().toString(),
        minutesForPreparation = 20,
        contributorId = UUID.randomUUID().toString(),
        submittedDate = LocalDate.parse("2010-06-10"),
        tags = listOf("Side Dish", "Vegetarian"),
        nutrition = Nutrition(120f, 5f, 1f, 200f, 2f, 3f, 20f),
        numberOfSteps = 3,
        steps = listOf("Boil potatoes", "Mash them", "Add butter and salt"),
        description = "Simple and delicious mashed potatoes.",
        ingredients = ingredients,
        numberOfIngredients = ingredients.size
    )


