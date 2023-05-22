package rs.ac.bg.etf.sm203134m.writer

import java.io.File

class CodeWriter(private val directoryPath: String) {

    fun write(fileName: String, code: String) {

        val directory = File(directoryPath)
        if(!directory.exists()) {
            directory.mkdir()
        }
        File("$directory/$fileName").writeText(code);
    }

}