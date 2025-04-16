package org.example.data

import java.io.File
import java.io.FileNotFoundException

class CsvFileReader(
    private val file: File
) {
    fun readLinesFromFile(): List<String> {
        when{
            file.exists().not() ->{
                throw FileNotFoundException("File ${file.path} not found.")
            }

            file.isFile.not() -> {
                throw FileNotFoundException("${file.path} Is a directory")
            }

            else -> {
                return file.readLines()
            }
        }
    }
}