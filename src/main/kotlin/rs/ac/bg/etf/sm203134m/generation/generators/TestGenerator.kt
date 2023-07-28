package rs.ac.bg.etf.sm203134m.generation.generators

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.bonigarcia.wdm.WebDriverManager
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.GeckoDriverInfo
import org.openqa.selenium.safari.SafariDriver
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.importStatement
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata
import java.io.IOException

fun TupParser.TestContext.generateOnEntry(metadata: TestMetadata, symbolTable: SymbolTable): String {
    val imports = generateImportsFromMetadata(metadata)
    val className = this.testName().IDENTIFIER().toString()
    symbolTable.testClassName = className
    return "$imports \n\npublic class $className {\n\n"
}

fun TupParser.TestContext.generateOnExit(): String {
    return "\n}"
}

fun generateImportsFromMetadata(metadata: TestMetadata): String {
    var imports =
        importStatement(Test::class.qualifiedName!!) +
                importStatement(BeforeEach::class.qualifiedName!!) +
                importStatement(Assertions::class.qualifiedName!!)



    if (metadata.requiresSelenium) {

        imports += importStatement(AfterEach::class.qualifiedName!!) +
                importStatement(ParameterizedTest::class.qualifiedName!!) +
                importStatement(ValueSource::class.qualifiedName!!)
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

        imports += importStatement(WebDriverManager::class.qualifiedName!!) +
                importStatement(WebDriver::class.qualifiedName!!) +
                importStatement(EdgeDriver::class.qualifiedName!!) +
                importStatement(ChromeDriver::class.qualifiedName!!) +
                importStatement(GeckoDriverInfo::class.qualifiedName!!) +
                importStatement(SafariDriver::class.qualifiedName!!)
    }

    if (metadata.requiresSeleniumBy) {
        imports += importStatement(By::class.qualifiedName!!)
    }

    return imports
}
