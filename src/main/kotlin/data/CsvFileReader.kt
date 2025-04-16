package org.example.data

import java.io.File
import java.io.IOException

class CsvFileReader(
    private val file: File
) {
    fun readLinesFromFile(): List<String> {
        if (file.exists()) {
            return file.readLines()
        }
        throw IOException("File Not Found")
    }
}