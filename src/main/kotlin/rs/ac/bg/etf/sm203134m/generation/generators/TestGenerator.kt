package rs.ac.bg.etf.sm203134m.generation.generators

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.bonigarcia.seljup.SeleniumJupiter
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.RegisterExtension
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.importStatement
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata
import java.io.IOException

fun TupParser.TestContext.generateOnEntry(metadata: TestMetadata): String {
    return generateImportsFromMetadata(metadata)
}

fun TupParser.TestContext.generateOnExit(): String {
    return "\n}"
}

fun generateImportsFromMetadata(metadata: TestMetadata): String {
    var imports =
        importStatement(Test::class.qualifiedName!!) +
                importStatement(BeforeAll::class.qualifiedName!!) +
                importStatement(Assertions::class.qualifiedName!!)



    if (metadata.requiresSelenium) {

        imports += importStatement(SeleniumJupiter::class.qualifiedName!!) +
                importStatement(TestTemplate::class.qualifiedName!!) +
                importStatement(RegisterExtension::class.qualifiedName!!)
    }

    if (metadata.requiresObjectMapper) {

        imports += importStatement(ObjectMapper::class.qualifiedName!!)
    }

    if (metadata.requiresOkhttp) {

        imports += importStatement(Headers::class.qualifiedName!!) +
                importStatement(MediaType::class.qualifiedName!!) +
                importStatement(OkHttpClient::class.qualifiedName!!) +
                importStatement(Request::class.qualifiedName!!) +
                importStatement(RequestBody::class.qualifiedName!!) +
                importStatement(IOException::class.qualifiedName!!)
    }

    if (metadata.requiresSelenium) {
         imports += importStatement(WebDriver::class.qualifiedName!!)
    }

    if (metadata.requiresSeleniumBy) {
        imports += importStatement(By::class.qualifiedName!!)
    }

    return imports
}
