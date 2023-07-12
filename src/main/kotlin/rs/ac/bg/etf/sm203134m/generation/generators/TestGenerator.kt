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
    var imports = """
        import ${Test::class.qualifiedName!!};
        import ${BeforeEach::class.qualifiedName!!};
        import ${Assertions::class.qualifiedName!!};
        """.trimIndent()

    if(metadata.requiresSelenium) {
        imports += "\nimport ${AfterEach::class.qualifiedName!!};"
        imports += "\nimport ${ParameterizedTest::class.qualifiedName!!};"
        imports += "\nimport ${ValueSource::class.qualifiedName!!};"
    }

    imports += "\n"

    if(metadata.requiresObjectMapper) {
        imports += "\nimport ${ObjectMapper::class.qualifiedName};\n"
    }

    imports += "\n"

    if(metadata.requiresOkhttp) {
        imports += """
            import ${Headers::class.qualifiedName!!};
            import ${MediaType::class.qualifiedName!!};
            import ${OkHttpClient::class.qualifiedName!!};
            import ${Request::class.qualifiedName!!};
            import ${RequestBody::class.qualifiedName!!};
            
            import ${IOException::class.qualifiedName!!};
        """.trimIndent()
    }

    // todo - for now only support edge
    if(metadata.requiresSelenium) {
        imports += """
            import ${WebDriverManager::class.qualifiedName};
            import ${WebDriver::class.qualifiedName};
            import ${EdgeDriver::class.qualifiedName};
            import ${ChromeDriver::class.qualifiedName};
            import ${GeckoDriverInfo::class.qualifiedName};
            import ${SafariDriver::class.qualifiedName};
        """.trimIndent()
    }

    if(metadata.requiresSeleniumBy) {
        imports += """
            import ${By::class.qualifiedName};
        """.trimIndent()
    }

    return imports
}
