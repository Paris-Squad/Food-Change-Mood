package data

import com.google.common.truth.Truth.assertThat
import org.example.data.CsvFileReader
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.io.FileNotFoundException

class CsvFileReaderTest {

    @TempDir
    lateinit var tempDir: File

    @Throws(FileNotFoundException::class)
    @Test
    fun `should throw FileNotFoundException when file does not exist`() {
        // Given
        val nonExistentFile = File(tempDir, "notExistFile.csv")
        val csvFileReader = CsvFileReader(nonExistentFile)

        // When
        val exception = assertThrows<FileNotFoundException> {
            csvFileReader.readLinesFromFile()
        }

        // Then
        assertThat("File ${nonExistentFile.path} not found.").isEqualTo(exception.message)
    }

    @Throws(FileNotFoundException::class)
    @Test
    fun `should throw FileNotFoundException when path is a directory`() {
        // Given
        val directory = tempDir.resolve("directory").apply { mkdir() }
        val csvFileReader = CsvFileReader(directory)

        // When / Then
        val exception = assertThrows<FileNotFoundException> {
            csvFileReader.readLinesFromFile()
        }
        assertThat("${directory.path} Is a directory").isEqualTo(exception.message)
    }

    @Test
    fun `should return list of lines that exist in the file when file exists`() {
        // Given
        val testFile = File(tempDir, "multiple_lines.csv").apply {
            writeText(DUMMY_FILE_CONTENT)
        }
        val csvFileReader = CsvFileReader(testFile)

        // When
        val result = csvFileReader.readLinesFromFile()

        // Then
        assertThat(DUMMY_FILE_CONTENT.split("\n")).containsExactlyElementsIn(result)
    }

    @Test
    fun `should return list of only one line that exist in the file when the file exists`() {
        // Given
        val fileContent = "single line"
        val testFile = File(tempDir, "single_line.csv").apply {
            writeText(fileContent)
        }
        val csvFileReader = CsvFileReader(testFile)

        // When
        val result = csvFileReader.readLinesFromFile()

        // Then
        assertThat(listOf(fileContent)).containsExactlyElementsIn(result)
    }

    companion object{
        private const val DUMMY_FILE_CONTENT = "arriba   baked winter squash mexican style,\n" +
                "137739,55,\n"+
                "47892,\n" +
                "2005-09-16,\n" +
                "\"['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']\",\n" +
                "\"[51.5, 0.0, 13.0, 0.0, 2.0, 0.0, 4.0]\",\n" +
                "11,\n" +
                "\"['make a choice and proceed with recipe', 'depending on size of squash , cut into half or fourths', 'remove seeds', 'for spicy squash , drizzle olive oil or melted butter over each cut squash piece', 'season with mexican seasoning mix ii', 'for sweet squash , drizzle melted honey , butter , grated piloncillo over each cut squash piece', 'season with sweet mexican spice mix', 'bake at 350 degrees , again depending on size , for 40 minutes up to an hour , until a fork can easily pierce the skin', 'be careful not to burn the squash especially if you opt to use sugar or butter', 'if you feel more comfortable , cover the squash with aluminum foil the first half hour , give or take , of baking', 'if desired , season with salt']\"\n" +
                ",\"autumn is my favorite time of year to cook! this recipe \n" +
                "can be prepared either spicy or sweet, your choice!"
    }
}