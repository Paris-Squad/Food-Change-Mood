package domain.usecase

import domain.model.Meal
import domain.model.Nutrition
import kotlinx.datetime.LocalDate

object MockMeals {
    val easyMeal1 = Meal(
        mealName = "Easy Pasta",
        mealId = "meal-001",
        minutesForPreparation = 15,
        contributorId = "contributor-1",
        submittedDate = LocalDate(2023, 1, 1),
        tags = listOf("pasta", "quick", "vegetarian"),
        nutrition = Nutrition(
            calories = 300f,
            totalFat = 8f,
            sugar = 5f,
            sodium = 400f,
            protein = 12f,
            saturatedFat = 2.5f,
            carbohydrates = 45f
        ),
        numberOfSteps = 4,
        steps = listOf(
            "Boil water in a pot",
            "Add pasta and cook for 8-10 minutes",
            "Drain and return to pot",
            "Add sauce and mix well"
        ),
        description = "A simple and quick pasta dish perfect for busy weeknights",
        ingredients = listOf("pasta", "tomato sauce", "cheese"),
        numberOfIngredients = 3
    )

    val easyMeal2 = Meal(
        mealName = "Scrambled Eggs",
        mealId = "meal-002",
        minutesForPreparation = 10,
        contributorId = "contributor-2",
        submittedDate = LocalDate(2023, 1, 15),
        tags = listOf("breakfast", "quick", "protein"),
        nutrition = Nutrition(
            calories = 220f,
            totalFat = 15f,
            sugar = 1f,
            sodium = 120f,
            protein = 20f,
            saturatedFat = 6f,
            carbohydrates = 2f
        ),
        numberOfSteps = 3,
        steps = listOf(
            "Crack eggs in a bowl and whisk",
            "Pour into hot pan and stir gently",
            "Remove from heat when slightly runny"
        ),
        description = "Fluffy scrambled eggs ready in minutes",
        ingredients = listOf("eggs", "milk", "butter", "salt", "pepper"),
        numberOfIngredients = 5
    )

    val mediumMeal = Meal(
        mealName = "Chicken Stir Fry",
        mealId = "meal-003",
        minutesForPreparation = 25,
        contributorId = "contributor-1",
        submittedDate = LocalDate(2023, 2, 10),
        tags = listOf("chicken", "asian", "dinner"),
        nutrition = Nutrition(
            calories = 380f,
            totalFat = 12f,
            sugar = 6f,
            sodium = 600f,
            protein = 35f,
            saturatedFat = 3f,
            carbohydrates = 30f
        ),
        numberOfSteps = 6,
        steps = listOf(
            "Cut chicken into small pieces",
            "Chop vegetables",
            "Heat oil in wok",
            "Cook chicken until golden",
            "Add vegetables and stir fry for 5 minutes",
            "Add sauce and cook for 2 more minutes"
        ),
        description = "A flavorful stir fry with chicken and vegetables",
        ingredients = listOf("chicken breast", "bell pepper", "broccoli", "carrot", "soy sauce", "ginger"),
        numberOfIngredients = 6
    )

    val hardMeal1 = Meal(
        mealName = "Beef Wellington",
        mealId = "meal-004",
        minutesForPreparation = 120,
        contributorId = "contributor-3",
        submittedDate = LocalDate(2023, 3, 5),
        tags = listOf("beef", "fancy", "dinner", "special occasion"),
        nutrition = Nutrition(
            calories = 750f,
            totalFat = 45f,
            sugar = 3f,
            sodium = 850f,
            protein = 40f,
            saturatedFat = 18f,
            carbohydrates = 40f
        ),
        numberOfSteps = 12,
        steps = listOf(
            "Season beef fillet with salt and pepper",
            "Sear the beef in hot pan on all sides",
            "Let the beef cool completely",
            "Prepare the mushroom duxelles",
            "Roll out the puff pastry",
            "Spread the mushroom mixture on the pastry",
            "Place prosciutto slices on top",
            "Put the beef on top",
            "Wrap the beef with the pastry",
            "Brush with egg wash",
            "Make small cuts for ventilation",
            "Bake until golden"
        ),
        description = "An impressive dish perfect for special occasions",
        ingredients = listOf(
            "beef fillet", "puff pastry", "mushrooms", "shallots", "garlic",
            "thyme", "prosciutto", "egg", "dijon mustard", "olive oil", "salt", "pepper"
        ),
        numberOfIngredients = 12
    )

    val hardMeal2 = Meal(
        mealName = "Homemade Croissants",
        mealId = "meal-005",
        minutesForPreparation = 240,
        contributorId = "contributor-4",
        submittedDate = LocalDate(2023, 2, 20),
        tags = listOf("baking", "breakfast", "french"),
        nutrition = Nutrition(
            calories = 280f,
            totalFat = 18f,
            sugar = 8f,
            sodium = 320f,
            protein = 5f,
            saturatedFat = 11f,
            carbohydrates = 25f
        ),
        numberOfSteps = 15,
        steps = listOf(
            "Mix flour, sugar, salt and yeast",
            "Add milk and water, knead to form dough",
            "Let it rise for 1 hour",
            "Prepare butter layer",
            "Roll out dough",
            "Place butter in center",
            "Fold dough over butter",
            "Roll out and fold (first turn)",
            "Chill for 1 hour",
            "Roll out and fold (second turn)",
            "Chill for 1 hour",
            "Roll out and cut triangles",
            "Roll up triangles to form croissants",
            "Let it proof for 2 hours",
            "Bake until golden"
        ),
        description = "Classic French croissants with a buttery, flaky texture",
        ingredients = listOf(
            "flour", "butter", "sugar", "salt", "yeast", "milk",
            "water", "egg", "vanilla extract"
        ),
        numberOfIngredients = 9
    )

    val allMeals = listOf(easyMeal1, easyMeal2, mediumMeal, hardMeal1, hardMeal2)

    val easyMeals = listOf(easyMeal1, easyMeal2)

    val hardMeals = listOf(hardMeal1, hardMeal2)

    val manyEasyMeals = (1..20).map { index ->
        Meal(
            mealName = "Quick Meal $index",
            mealId = "meal-quick-$index",
            minutesForPreparation = 15,
            contributorId = "contributor-1",
            submittedDate = LocalDate(2023, 1, index),
            tags = listOf("quick", "easy"),
            nutrition = Nutrition(
                calories = 250f,
                totalFat = 8f,
                sugar = 4f,
                sodium = 300f,
                protein = 15f,
                saturatedFat = 2f,
                carbohydrates = 30f
            ),
            numberOfSteps = 3,
            steps = listOf("Step 1", "Step 2", "Step 3"),
            description = "A quick meal for testing",
            ingredients = listOf("ingredient 1", "ingredient 2", "ingredient 3"),
            numberOfIngredients = 3
        )
    }
}