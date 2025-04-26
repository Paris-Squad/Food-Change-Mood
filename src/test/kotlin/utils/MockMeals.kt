package utils

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

    val invalidSeafoodByNotContainsTags = listOf(
        createMeal(
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = 20f)
        ),
        createMeal(
            tags = listOf("seafood", "cuisine"),
            nutrition = createNutrition(protein = 30f)
        ),
        createMeal(
            tags = listOf("pasta"),
            nutrition = createNutrition(protein = 10f)
        )
    )

    val invalidSeafoodByNullName = listOf(
        createMeal(
            mealName = null,
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = 10f)
        ),
        createMeal(
            mealName = null,
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = 10f)
        ),
        createMeal(
            mealName = null,
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = 10f)
        ),
    )

    val invalidSeafoodByNullNutrition = listOf(
        createMeal(
            mealName = "put down your fork tuna and bean salad",
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = null)
        ),
        createMeal(
            mealName = "smoked  salmon  cracker spread",
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = null)
        ),
        createMeal(
            mealName = "voodoo  calamari ink  pasta",
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = null)
        ),
    )

    val validSeaFoodMeals = listOf(
        createMeal(
            mealName = "put down your fork tuna and bean salad",
            tags = listOf("seafood"),
            nutrition = createNutrition(protein = 20f)
        ),
        createMeal(
            mealName = "smoked  salmon  cracker spread",
            tags = listOf("seafood", "cuisine"),
            nutrition = createNutrition(protein = 30f)
        ),
        createMeal(
            mealName = "voodoo  calamari ink  pasta",
            tags = listOf("pasta"),
            nutrition = createNutrition(protein = 10f)
        )
    )

    val validQuickMeals = listOf(
        createMeal(
            mealName = "Healthy Chicken Salad",
            minutesForPreparation = 15,
            nutrition = createNutrition(totalFat = 4f, saturatedFat = 3f, carbohydrates = 10f)
        ),
        createMeal(
            mealName = "Invalid createMeal",
            minutesForPreparation = 12,
            nutrition = createNutrition(totalFat = 4f, saturatedFat = 3f, carbohydrates = 20f)
        ),
        createMeal(
            "Too Long To Prep",
            minutesForPreparation = 30,
            nutrition = createNutrition(3f, 2f, 12f)
        )
    )

    val invalidEasyMealsByNullName = validQuickMeals.map {
        it.copy(mealName = null)
    }

    val invalidEasyMealsByNullTotalFat = validQuickMeals.map {
        it.copy(
            nutrition = it.nutrition.copy(totalFat = null)
        )
    }

    val invalidEasyMealsByNullSaturatedFat = validQuickMeals.map {
        it.copy(
            nutrition = it.nutrition.copy(saturatedFat = null)
        )
    }

    val invalidEasyMealsByNullCarbohydrates = validQuickMeals.map {
        it.copy(
            nutrition = it.nutrition.copy(carbohydrates = null)
        )
    }

    val invalidQuickMealsWithHighPrepTime = validQuickMeals.map {
        it.copy(
            minutesForPreparation = 50
        )
    }

    val eggFreeSweet1 = createMeal(
        mealName = "Vegan Chocolate Cake",
        tags = listOf("sweet", "dessert", "vegan"),
        ingredients = listOf("flour", "sugar", "plant milk", "chocolate")
    )

    val eggFreeSweet2 = createMeal(
        mealName = "Fruit Salad",
        tags = listOf("sweet", "dessert", "healthy"),
        ingredients = listOf("apple", "banana", "orange", "berries")
    )

    val sweetMealWithEggs = createMeal(
        mealName = "Chocolate Cake with Eggs",
        tags = listOf("sweet", "dessert"),
        ingredients = listOf("flour", "sugar", "eggs", "chocolate")
    )

    val nonDessertMeal = createMeal(
        mealName = "Savory Dish",
        tags = listOf("savory", "dinner"),
        ingredients = listOf("potato", "onion", "cheese")
    )

    val eggFreeSweetsMixedTestMeals = listOf(eggFreeSweet1, eggFreeSweet2, sweetMealWithEggs, nonDessertMeal)


    val noEggFreeSweetsMeals = listOf(
        sweetMealWithEggs,
        nonDessertMeal
    )

    val invalidItalianMealsByUnTaggedItalianOrItaly = listOf(
        createMeal(
            mealName = "Healthy Chicken Salad",
            tags = listOf("for-large-groups")
        ),
        createMeal(
            mealName = "Invalid createMeal",
            tags = listOf("for-large-groups")
        ),
        createMeal(
            mealName = "Too Long To Prep",
            tags = listOf("for-large-groups")
        )
    )

    val invalidItalianMealsByUnTaggedForLargeGroup = listOf(
        createMeal(
            mealName = "Healthy Chicken Salad",
            tags = listOf("italian")
        ),
        createMeal(
            mealName = "Invalid createMeal",
            tags = listOf("italy")
        ),
        createMeal(
            mealName = "Too Long To Prep",
            tags = listOf("italy, italian")
        )
    )

    val validItalianMealsTaggedByForLargeGroupAndItalianOrItaly = listOf(
        createMeal(
            mealName = "Healthy Chicken Salad",
            tags = listOf("italian","for-large-groups")
        ),
        createMeal(
            mealName = "Invalid createMeal",
            tags = listOf("italy","for-large-groups")
        ),
        createMeal(
            mealName = "Invalid createMeal",
            tags = listOf("ITALY","for-large-groups")
        ),
        createMeal(
            mealName = "Invalid createMeal",
            tags = listOf("ITALIAN","FOR-LARGE-GROUPS")
        ),
        createMeal(
            mealName = "Too Long To Prep",
            tags = listOf("italy", "italian")
        )
    )


    val validMeal = createMeal(
        mealName = "arriba baked winter squash mexican style",
        mealId = "137739",
        minutesForPreparation = 55,
        contributorId = "47892",
        submittedDate = LocalDate.parse("2005-09-16"),
        tags = listOf(
            "60-minutes-or-less", "time-to-make", "course", "main-ingredient",
            "cuisine", "preparation", "occasion", "north-american", "side-dishes",
            "vegetables", "mexican", "easy", "fall", "holiday-event", "vegetarian",
            "winter", "dietary", "christmas", "seasonal", "squash"
        ),
        nutrition = Nutrition(
            calories = 51.5f,
            totalFat = 0.0f,
            sugar = 13.0f,
            sodium = 0.0f,
            protein = 2.0f,
            saturatedFat = 0.0f,
            carbohydrates = 4.0f
        ),
        numberOfSteps = 11,
        steps = listOf(
            "make a choice and proceed with recipe",
            "depending on size of squash , cut into half or fourths",
            "remove seeds",
            "for spicy squash , drizzle olive oil or melted butter over each cut squash piece",
            "season with mexican seasoning mix ii",
            "for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece",
            "season with sweet mexican spice mix",
            "bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin",
            "be careful not to burn the squash especially if you opt to use sugar or butter",
            "if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking",
            "if desired , season with salt"
        ),
        description = "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
        ingredients = listOf(
            "winter squash",
            "mexican seasoning",
            "mixed spice",
            "honey",
            "butter",
            "olive oil",
            "salt"
        ),
        numberOfIngredients = 7
    )

    val realMeals = listOf(
        validMeal,
        createMeal(
            mealName = "a bit different breakfast pizza",
            mealId = "31490",
            minutesForPreparation = 30,
            contributorId = "26278",
            submittedDate = LocalDate.parse("2002-06-17"),
            tags = listOf(
                "30-minutes-or-less", "time-to-make", "course", "main-ingredient",
                "cuisine", "preparation", "occasion", "north-american", "breakfast",
                "main-dish", "pork", "american", "oven", "easy", "kid-friendly",
                "pizza", "dietary", "northeastern-united-states", "meat", "equipment"
            ),
            nutrition = Nutrition(
                calories = 173.4f,
                totalFat = 18.0f,
                sugar = 0.0f,
                sodium = 17.0f,
                protein = 22.0f,
                saturatedFat = 35.0f,
                carbohydrates = 1.0f
            ),
            numberOfSteps = 9,
            steps = listOf(
                "preheat oven to 425 degrees f",
                "press dough into the bottom and sides of a 12 inch pizza pan",
                "bake for 5 minutes until set but not browned",
                "cut sausage into small pieces",
                "whisk eggs and milk in a bowl until frothy",
                "spoon sausage over baked crust and sprinkle with cheese",
                "pour egg mixture slowly over sausage and cheese",
                "s&p to taste",
                "bake 15-20 minutes or until eggs are set and crust is brown"
            ),
            description = "this recipe calls for the crust to be prebaked a bit before adding ingredients. feel free to change sausage to ham or bacon. this warms well in the microwave for those late risers.",
            ingredients = listOf(
                "prepared pizza crust",
                "sausage patty",
                "eggs",
                "milk",
                "salt and pepper",
                "cheese"
            ),
            numberOfIngredients = 6
        )
    )
}