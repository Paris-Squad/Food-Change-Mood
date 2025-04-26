package data


import com.google.common.truth.Truth.assertThat
import org.example.data.MealBuilder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import utils.MockMeals
import utils.createNutrition

class MealBuilderTest {

    private lateinit var mealBuilder: MealBuilder

    @BeforeEach
    fun setUp() {
        mealBuilder = MealBuilder()
    }

    @Test
    fun `should correctly parse all fields and return valid meal`() {
        // Given
        val fields = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(fields)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal)
    }

    @Test
    fun `should correctly parse all fields and return valid meal but with empty tags as tags sent empty`() {
        val dataWithEmptyTags = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(dataWithEmptyTags)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(tags = emptyList()))
    }

    @Test
    fun `should correctly parse all fields and return valid meal but with empty steps as invalid steps sent`() {
        val dataWithEmptyTags = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",
            "11",
            "()",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(dataWithEmptyTags)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(steps = emptyList()))
    }

    @Test
    fun `should correctly parse all fields and return valid meal but with empty ingredients as invalid ingredients sent`() {
        val dataWithEmptyTags = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt'",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(dataWithEmptyTags)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(ingredients = emptyList()))
    }

    @Test
    fun `should correctly parse all fields and return valid meal but with empty ingredients as empty ingredients sent`() {
        val dataWithEmptyTags = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "[]",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(dataWithEmptyTags)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(ingredients = emptyList()))
    }

    @Test
    fun `should correctly parse all fields and return valid meal but with zeros values in Nutrition except calories`() {
        val nutrition = createNutrition(calories = 20.5f)

        val fields = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "20.5",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(fields)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(nutrition = nutrition))
    }

    @Test
    fun `should correctly parse all fields and return valid meal but with zeros values in Nutrition`() {
        val nutrition = createNutrition(
            calories = 0f,
            totalFat = 0f,
            sugar = 0f,
            sodium = 0f,
            protein = 0f,
            saturatedFat = 0f,
            carbohydrates = 0f
        )
        val fields = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[ww, w, s, sa, d, a, fff]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(fields)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(nutrition = nutrition))
    }

    @Test
    fun `should correctly parse all fields and return valid meal but with zero value in carbohydrates at Nutrition`() {
        val nutrition = createNutrition(
            calories = 51.5f,
            totalFat = 0f,
            sugar = 13f,
            sodium = 0f,
            protein = 2f,
            saturatedFat = 0f,
            carbohydrates = 0f
        )
        val fields = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, fff]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(fields)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(nutrition = nutrition))
    }

    @Test
    fun `should correctly parse all fields but with zero calories and other value nullable in Nutrition because of closing bracket not found`() {
        // Given
        val nutrition = createNutrition(
            calories = 0f,
        )
        val fields = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[4.0",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(fields)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(nutrition = nutrition))
    }

    @Test
    fun `should correctly parse all fields but with zero calories and other value nullable in Nutrition because of opening bracket not found`() {
        // Given
        val nutrition = createNutrition(
            calories = 0f,
        )
        val fields = listOf(
            "arriba baked winter squash mexican style",
            "137739",
            "55",
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "4.0]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When
        val meal = mealBuilder.buildMeal(fields)

        // Then
        assertThat(meal).isEqualTo(MockMeals.validMeal.copy(nutrition = nutrition))
    }

    @Throws(IndexOutOfBoundsException::class)
    @Test
    fun `should throw IndexOutOfBoundsException when given empty list`() {
        // Given
        val emptyList = emptyList<String>()

        // When / Then
        assertThrows<IndexOutOfBoundsException> {
            mealBuilder.buildMeal(emptyList)
        }
    }

    @Throws(IndexOutOfBoundsException::class)
    @Test
    fun `should throw IndexOutOfBoundsException when given empty list that it's size less than 12`() {
        // Given
        val incompleteData = listOf("Test Meal")

        // When/Then
        assertThrows<IndexOutOfBoundsException> {
            mealBuilder.buildMeal(incompleteData)
        }
    }

    @Throws(NumberFormatException::class)
    @Test
    fun `should throw NumberFormatException when minutes for preparation is not valid Number`() {
        // Given
        val dataWithInvalidMinutesForPreparation = listOf(
            "arriba baked winter squash mexican style",
            "jee",
            "ewe", //minutes for preparation
            "47892",
            "2005-09-16",
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )

        // When/Then
        assertThrows<NumberFormatException> {
            mealBuilder.buildMeal(dataWithInvalidMinutesForPreparation)
        }
    }

    @Throws(IllegalArgumentException::class)
    @Test
    fun `should throw IllegalArgumentException when given invalid date`() {

        val dataWithEmptyNumbers = listOf(
            "arriba baked winter squash mexican style",
            "jee",
            "33",
            "47892",
            "2005-9-16", //Invalid date
            "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']",
            "[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",
            "11",
            "['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']",
            "autumn is my favorite time of year to cook! this recipe can be prepared either spicy or sweet, your choice! two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.",
            "['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",
            "7"
        )
        assertThrows<IllegalArgumentException> {
            mealBuilder.buildMeal(dataWithEmptyNumbers)
        }

    }
}
