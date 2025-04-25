package data

import com.google.common.truth.Truth.assertThat
import domain.model.Meal
import utils.MockMeals
import io.mockk.every
import io.mockk.mockk
import org.example.data.CsvFileReader
import org.example.data.CsvMealsRepository
import org.example.data.MealCsvFileParser
import org.example.domain.repository.MealRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.FileNotFoundException

class CsvMealsRepositoryTest {

    private val csvFileReader: CsvFileReader = mockk(relaxed = true)
    private val mealCsvFileParser: MealCsvFileParser = mockk(relaxed = true)
    private lateinit var repository: MealRepository

    @BeforeEach
    fun setup() {
        repository = CsvMealsRepository(csvFileReader, mealCsvFileParser)
    }

    @Throws(FileNotFoundException::class)
    @Test
    fun `should throw FileNotFoundException when file does not exist`() {
        // Given
        val fileNotFoundException = FileNotFoundException("File not found.")
        every { csvFileReader.readLinesFromFile() } throws (fileNotFoundException)

        // When / then
        val exception = assertThrows<FileNotFoundException> {
            csvFileReader.readLinesFromFile()
        }

        // Then
        assertThat("File not found.").isEqualTo(exception.message)
    }

    @Throws(FileNotFoundException::class)
    @Test
    fun `should throw FileNotFoundException when path is a directory`() {
        // Given
        val fileNotFoundException = FileNotFoundException("Is a directory.")
        every { csvFileReader.readLinesFromFile() } throws (fileNotFoundException)

        // When / Then
        val exception = assertThrows<FileNotFoundException> {
            csvFileReader.readLinesFromFile()
        }
        assertThat("Is a directory.").isEqualTo(exception.message)
    }

    @Test
    fun `should return empty list of meals when the file is empty`() {
        // Given
        every { mealCsvFileParser.parseMeals(emptyList()) } returns emptyList()

        // When
        val result = repository.getMeals()

        // Then
        assertThat(result).containsExactlyElementsIn(emptyList<Meal>())
    }

    @Test
    fun `should return list of meals when the file has valid content and ignore invalid meals`() {
        // Given
        val csvLines = FILE_CONTENT.split("\n")
        every { csvFileReader.readLinesFromFile() } returns csvLines
        every { mealCsvFileParser.parseMeals(csvLines) } returns MockMeals.realMeals

        // When
        val result = repository.getMeals()

        // Then
        assertThat(result).containsExactlyElementsIn(MockMeals.realMeals)
    }

    companion object {
        const val FILE_CONTENT = """
            name,id,minutes,contributor_id,submitted,tags,nutrition,n_steps,steps,description,ingredients,n_ingredients
arriba   baked winter squash mexican style,137739,55,47892,2005-09-16,"['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']","[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]",11,"['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']","autumn is my favorite time of year to cook! this recipe 
can be prepared either spicy or sweet, your choice!
two of my posted mexican-inspired seasoning mix recipes are offered as suggestions.","['winter squash', 'mexican seasoning', 'mixed spice', 'honey', 'butter', 'olive oil', 'salt']",7
a bit different  breakfast pizza,31490,30,26278,2002-06-17,"['30-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'breakfast', 'main-dish', 'pork', 'american', 'oven', 'easy', 'kid-friendly', 'pizza', 'dietary', 'northeastern-united-states', 'meat', 'equipment']","[173.4, 18.0, 0.0, 17.0, 22.0, 35.0, 1.0]",9,"['preheat oven to 425 degrees f', 'press dough into the bottom and sides of a 12 inch pizza pan', 'bake for 5 minutes until set but not browned', 'cut sausage into small pieces', 'whisk eggs and milk in a bowl until frothy', 'spoon sausage over baked crust and sprinkle with cheese', 'pour egg mixture slowly over sausage and cheese', 's& p to taste', 'bake 15-20 minutes or until eggs are set and crust is brown']",this recipe calls for the crust to be prebaked a bit before adding ingredients. feel free to change sausage to ham or bacon. this warms well in the microwave for those late risers.,"['prepared pizza crust', 'sausage patty', 'eggs', 'milk', 'salt and pepper', 'cheese']",6
        """
    }


}