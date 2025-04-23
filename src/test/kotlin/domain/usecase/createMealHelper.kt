package domain.usecase
import domain.model.Meal
import domain.model.Nutrition
import kotlinx.datetime.LocalDate

fun createMeal(
    mealName: String? = null,
    mealId: String = "22",
    minutesForPreparation: String = "22",
    submittedDate: LocalDate = LocalDate.parse("1999-08-07"),
    tags: List<String> = emptyList(),
    nutrition: Nutrition = createNutrition(),
    numberOfSteps: Int = 22,
    steps: List<String> = emptyList(),
    description: String? = null,
    ingredients: List<String> = emptyList(),
    numberOfIngredients: Int = 22,
): Meal = Meal(
    mealName = mealName,
    mealId = mealId,
    minutesForPreparation = 30,
    contributorId = minutesForPreparation,
    submittedDate = submittedDate,
    tags = tags,
    nutrition = nutrition,
    numberOfSteps = numberOfSteps,
    steps = steps,
    description = description,
    ingredients = ingredients,
    numberOfIngredients = numberOfIngredients
)
fun createNutrition(
    calories: Float? = null,
    totalFat: Float? = null,
    sugar: Float? = null,
    sodium: Float? = null,
    protein: Float? = null,
    saturatedFat: Float? = null,
    carbohydrates: Float? = null,
): Nutrition = Nutrition(
    calories = calories,
    totalFat = totalFat,
    sugar = sugar,
    sodium = sodium,
    protein = protein,
    saturatedFat = saturatedFat,
    carbohydrates = carbohydrates
)
