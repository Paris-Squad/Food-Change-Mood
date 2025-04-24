import domain.model.Meal
import domain.model.Nutrition
import kotlinx.datetime.LocalDate


fun createMeal(
  calories : Float?,
  protein : Float?,
  totalFat : Float?,
  carbohydrates : Float?
) = Meal(
    mealName = null,
    mealId = "1",
    minutesForPreparation = 30,
    contributorId = "1",
    nutrition = Nutrition(
        calories,totalFat,null,null,protein,null,carbohydrates
    ),
    description = null,
    numberOfIngredients = 3,
    numberOfSteps = 3,
    steps = listOf("do", "do", "do"),
    ingredients = listOf("do", "do", "do"),
    tags = listOf("do", "do", "do"),
    submittedDate = LocalDate(2000,12,5)

)