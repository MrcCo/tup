package rs.ac.bg.etf.sm203134m.generation.generators.setup

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.edge.EdgeDriver
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun generateBeforeEachMethod(metadata: TestMetadata): String {

    var result = "\t@${BeforeEach::class.simpleName}\n\tvoid setup() {\n"
    if (metadata.requiresOkhttp) {
        result += "\t\tclient = new ${OkHttpClient::class.simpleName}();\n"
    }
    if (metadata.requiresObjectMapper) {
        result += "\t\tobjectMapper = new ${ObjectMapper::class.simpleName}();\n"
    }

    if(metadata.requiresSelenium) {
        result += "\t\tWebDriverManager.edgedriver().setup();\n\t\tdriver = new ${EdgeDriver::class.simpleName}();"
    }
    result += "\ns\t}\n"

    return result

}