package rs.ac.bg.etf.sm203134m.generation.generators

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeEach
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun TupParser.TestTypeContext.generateOnEntry(metadata: TestMetadata): String {

    var fieldDeclarations = ""

    if(metadata.requiresOkhttp) {
        fieldDeclarations += "\tprivate ${OkHttpClient::class.simpleName} client; \n"
    }

    if(metadata.requiresObjectMapper) {
        fieldDeclarations += "\tprivate ${ObjectMapper::class.simpleName} objectMapper; \n"
    }

    return fieldDeclarations
}

fun TupParser.TestTypeContext.generateOnExit(metadata: TestMetadata): String {
    var setupMethod = "\t@${BeforeEach::class.simpleName}\n\tvoid setup() {\n"

    if(metadata.requiresOkhttp) {
        setupMethod += "\t\tclient = new ${OkHttpClient::class.simpleName}();\n"
    }

    if(metadata.requiresObjectMapper) {
        setupMethod += "\t\tobjectMapper = new ${ObjectMapper::class.simpleName}();\n"
    }

    return "$setupMethod\t}\n"

}