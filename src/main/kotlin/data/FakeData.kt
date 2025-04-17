package org.example.data

import kotlinx.datetime.LocalDate
import org.example.model.Meal
import org.example.model.Nutrition

val FakeFoodList = listOf(
    Meal(
        mealName = "arriba baked winter squash mexican style",
        mealId = "137739",
        minutesForPreparation = 55,
        contributorId = "47892",
        submittedDate = LocalDate.parse("2005-09-16"),
        tags = listOf(
            "60-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine",
            "preparation", "occasion", "north-american", "side-dishes", "vegetables",
            "mexican", "easy", "fall", "holiday-event", "vegetarian", "winter",
            "dietary", "christmas", "seasonal", "squash"
        ),
        nutrition = Nutrition(51.5f, 0.0f, 13.0f, 0.0f, 2.0f, 0.0f, 4.0f),
        numberOfSteps = 11,
        steps = listOf(
            "make a choice and proceed with recipe",
            "depending on size of squash, cut into half or fourths",
            "remove seeds",
            "for spicy squash, drizzle olive oil or melted butter over each cut squash piece",
            "season with mexican seasoning mix ii",
            "for sweet squash, drizzle melted honey, butter, grated piloncillo over each cut squash piece",
            "season with sweet mexican spice mix",
            "bake at 350 degrees, again depending on size, for 40 minutes up to an hour, until a fork can easily pierce the skin",
            "be careful not to burn the squash especially if you opt to use sugar or butter",
            "if you feel more comfortable, cover the squash with aluminum foil the first half hour, give or take, of baking",
            "if desired, season with salt"
        ),
        description = "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
        ingredients = listOf("winter squash", "mexican seasoning", "mixed spice", "honey", "butter", "olive oil", "salt"),
        numberOfIngredients = 7
    ),
    Meal(
        mealName = "a bit different breakfast pizza",
        mealId = "31490",
        minutesForPreparation = 30,
        contributorId = "26278",
        submittedDate = LocalDate.parse("2002-06-17"),
        tags = listOf(
            "30-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine",
            "preparation", "occasion", "north-american", "breakfast", "main-dish",
            "pork", "american", "oven", "easy", "kid-friendly", "pizza",
            "dietary", "northeastern-united-states", "meat", "equipment"
        ),
        nutrition = Nutrition(173.4f, 18.0f, 0.0f, 17.0f, 22.0f, 35.0f, 1.0f),
        numberOfSteps = 9,
        steps = listOf(
            "preheat oven to 425 degrees f",
            "press dough into the bottom and sides of a 12 inch pizza pan",
            "bake for 5 minutes until set but not browned",
            "cut sausage into small pieces",
            "whisk eggs and milk in a bowl until frothy",
            "spoon sausage over baked crust and sprinkle with cheese",
            "pour egg mixture slowly over sausage and cheese",
            "s& p to taste",
            "bake 15-20 minutes or until eggs are set and crust is brown"
        ),
        description = "this recipe calls for the crust to be prebaked a bit before adding ingredients. feel free to change sausage to ham or bacon. this warms well in the microwave for those late risers.",
        ingredients = listOf("prepared pizza crust", "sausage patty", "eggs", "milk", "salt and pepper", "cheese"),
        numberOfIngredients = 6
    ),
    Meal(
        mealName = "all in the kitchen chili",
        mealId = "112140",
        minutesForPreparation = 130,
        contributorId = "196586",
        submittedDate = LocalDate.parse("2005-02-25"),
        tags = listOf(
            "time-to-make", "course", "preparation", "main-dish", "chili",
            "crock-pot-slow-cooker", "dietary", "equipment", "4-hours-or-less"
        ),
        nutrition = Nutrition(269.8f, 22.0f, 32.0f, 48.0f, 39.0f, 27.0f, 5.0f),
        numberOfSteps = 6,
        steps = listOf(
            "brown ground beef in large pot",
            "add chopped onions to ground beef when almost brown and sautee until wilted",
            "add all other ingredients",
            "add kidney beans if you like beans in your chili",
            "cook in slow cooker on high for 2-3 hours or 6-8 hours on low",
            "serve with cold clean lettuce and shredded cheese"
        ),
        description = "this modified version of 'mom's' chili was a hit at our 2004 christmas party. we made an extra large pot to have some left to freeze but it never made it to the freezer. it was a favorite by all. perfect for any cold and rainy day. you won't find this one in a cookbook. it is truly an original.",
        ingredients = listOf("ground beef", "yellow onions", "diced tomatoes", "tomato paste", "tomato soup", "rotel tomatoes", "kidney beans", "water", "chili powder", "ground cumin", "salt", "lettuce", "cheddar cheese"),
        numberOfIngredients = 13
    ),
    Meal(
        mealName = "alouette potatoes",
        mealId = "59389",
        minutesForPreparation = 45,
        contributorId = "68585",
        submittedDate = LocalDate.parse("2003-04-14"),
        tags = listOf(
            "60-minutes-or-less", "time-to-make", "course", "main-ingredient", "preparation",
            "occasion", "side-dishes", "eggs-dairy", "potatoes", "vegetables",
            "oven", "easy", "dinner-party", "holiday-event", "easter", "cheese",
            "stove-top", "dietary", "christmas", "new-years", "thanksgiving",
            "independence-day", "st-patricks-day", "valentines-day", "inexpensive",
            "brunch", "superbowl", "equipment", "presentation", "served-hot"
        ),
        nutrition = Nutrition(368.1f, 17.0f, 10.0f, 2.0f, 14.0f, 8.0f, 20.0f),
        numberOfSteps = 11,
        steps = listOf(
            "place potatoes in a large pot of lightly salted water and bring to a gentle boil",
            "cook until potatoes are just tender",
            "drain",
            "place potatoes in a large bowl and add all ingredients except the 'alouette'",
            "mix well and transfer to a buttered 8x8 inch glass baking dish with 2 inch sides",
            "press the potatoes with a spatula to make top as flat as possible",
            "set aside for 2 hours at room temperature",
            "preheat oven to 350^f",
            "spread 'alouette' evenly over potatoes and bake 15 minutes",
            "divide between plates",
            "garnish with finely diced red and yellow bell peppers"
        ),
        description = "this is a super easy, great tasting, make ahead side dish that looks like you spent a lot more time preparing than you actually do. plus, most everything is done in advance. the times do not reflect the standing time of the potatoes.",
        ingredients = listOf("spreadable cheese with garlic and herbs", "new potatoes", "shallots", "parsley", "tarragon", "olive oil", "red wine vinegar", "salt", "pepper", "red bell pepper", "yellow bell pepper"),
        numberOfIngredients = 11
    ),
    Meal(
        mealName = "amish tomato ketchup for canning",
        mealId = "44061",
        minutesForPreparation = 190,
        contributorId = "41706",
        submittedDate = LocalDate.parse("2002-10-25"),
        tags = listOf(
            "weeknight", "time-to-make", "course", "main-ingredient", "cuisine",
            "preparation", "occasion", "north-american", "canning", "condiments-etc",
            "vegetables", "american", "heirloom-historical", "holiday-event", "vegetarian",
            "dietary", "amish-mennonite", "northeastern-united-states", "number-of-servings",
            "technique", "4-hours-or-less"
        ),
        nutrition = Nutrition(352.9f, 1.0f, 337.0f, 23.0f, 3.0f, 0.0f, 28.0f),
        numberOfSteps = 5,
        steps = listOf(
            "mix all ingredients & boil for 2 1/2 hours, or until thick",
            "pour into jars",
            "i use 'old' glass ketchup bottles",
            "it is not necessary for these to 'seal'",
            "'my amish mother-in-law has been making this her entire life, and has never used a 'sealed' jar for this recipe, and it's always been great!'"
        ),
        description = "my dh's amish mother raised him on this recipe. he much prefers it over store-bought ketchup. it was a taste i had to acquire, but now my ds's also prefer this type of ketchup. enjoy!",
        ingredients = listOf("tomato juice", "apple cider vinegar", "sugar", "salt", "pepper", "clove oil", "cinnamon oil", "dry mustard"),
        numberOfIngredients = 8
    ),
    Meal(
        mealName = "apple a day milk shake",
        mealId = "5289",
        minutesForPreparation = 0,
        contributorId = "1533",
        submittedDate = LocalDate.parse("1999-12-06"),
        tags = listOf(
            "15-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine",
            "preparation", "occasion", "north-american", "low-protein", "5-ingredients-or-less",
            "beverages", "fruit", "american", "easy", "kid-friendly", "dietary",
            "low-sodium", "shakes", "low-calorie", "low-in-something", "apples",
            "number-of-servings", "presentation", "served-cold", "3-steps-or-less"
        ),
        nutrition = Nutrition(160.2f, 10.0f, 55.0f, 3.0f, 9.0f, 20.0f, 7.0f),
        numberOfSteps = 4,
        steps = listOf(
            "combine ingredients in blender",
            "cover and blend until smooth",
            "sprinkle with ground cinnamon",
            "makes about 2 cups"
        ),
        description = null,
        ingredients = listOf("milk", "vanilla ice cream", "frozen apple juice concentrate", "apple"),
        numberOfIngredients = 4
    ),
    Meal(
        mealName = "aww marinated olives",
        mealId = "25274",
        minutesForPreparation = 15,
        contributorId = "21730",
        submittedDate = LocalDate.parse("2002-04-14"),
        tags = listOf(
            "15-minutes-or-less", "time-to-make", "course", "main-ingredient", "cuisine",
            "preparation", "occasion", "north-american", "appetizers", "fruit",
            "canadian", "dinner-party", "vegan", "vegetarian", "freezer", "dietary",
            "equipment", "number-of-servings"
        ),
        nutrition = Nutrition(380.7f, 53.0f, 7.0f, 24.0f, 6.0f, 24.0f, 6.0f),
        numberOfSteps = 4,
        steps = listOf(
            "toast the fennel seeds and lightly crush them",
            "place all the ingredients in a bowl, stir well",
            "cover and leave to marinate",
            "keep refrigerated and use within 1 to 2 days"
        ),
        description = "my italian mil was thoroughly impressed by my non-italian treatment of her olives. they are great appetizers and condiments to your fav pasta.(from the vancouver sun) ps. cook time include fridge time",
        ingredients = listOf("fennel seeds", "green olives", "ripe olives", "garlic", "peppercorn", "orange rind", "orange juice", "red chile", "extra virgin olive oil"),
        numberOfIngredients = 9
    ),
    Meal(
        mealName = "backyard style barbecued ribs",
        mealId = "67888",
        minutesForPreparation = 120,
        contributorId = "10404",
        submittedDate = LocalDate.parse("2003-07-30"),
        tags = listOf(
            "weeknight", "time-to-make", "course", "main-ingredient", "cuisine",
            "preparation", "occasion", "north-american", "south-west-pacific", "main-dish",
            "pork", "oven", "holiday-event", "stove-top", "hawaiian", "spicy",
            "copycat", "independence-day", "meat", "pork-ribs", "super-bowl",
            "novelty", "taste-mood", "savory", "sweet", "equipment", "4-hours-or-less"
        ),
        nutrition = Nutrition(1109.5f, 83.0f, 378.0f, 275.0f, 96.0f, 86.0f, 36.0f),
        numberOfSteps = 10,
        steps = listOf(
            "in a medium saucepan combine all the ingredients for sauce#1, bring to a full rolling boil, reduce heat to medium low and simmer for 1 hour, stirring often",
            "rub the ribs with soy sauce, garlic, ginger, chili powder, pepper, salt and chopped cilantro, both sides!",
            "wrap ribs in heavy duty foil",
            "let stand 1 hour",
            "preheat oven to 350 degrees",
            "place ribs in oven for 1 hour, turning once after 30 minutes",
            "3 times during cooking the ribs open foil wrap and drizzle ribs with sauce#1",
            "place all the ingredients for sauce#2 in a glass or plastic bowl, whisk well and set aside",
            "remove ribs from oven and place on serving platter",
            "offer both sauces at table to drizzle over ribs"
        ),
        description = "this recipe is posted by request and was originaly from chef sam choy's cookbook",
        ingredients = listOf("pork spareribs", "soy sauce", "fresh garlic", "fresh ginger", "chili powder", "fresh coarse ground black pepper", "salt", "fresh cilantro leaves", "tomato sauce", "brown sugar", "yellow onion", "white vinegar", "honey", "a.1. original sauce", "liquid smoke"),
        numberOfIngredients = 15
    )
)