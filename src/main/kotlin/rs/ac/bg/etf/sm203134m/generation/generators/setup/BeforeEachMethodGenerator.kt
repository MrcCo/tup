package rs.ac.bg.etf.sm203134m.generation.generators.setup

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.edge.EdgeDriver
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun generateBeforeEachMethod(metadata: TestMetadata): String {

    if(!metadata.requiresOkhttp && !metadata.requiresObjectMapper)
        return ""

    var result = "\t@${BeforeEach::class.simpleName}\n\tvoid setup() {\n"
    if (metadata.requiresOkhttp) {
        result += "\t\tclient = new ${OkHttpClient::class.simpleName}();\n"
    }
    if (metadata.requiresObjectMapper) {
        result += "\t\tobjectMapper = new ${ObjectMapper::class.simpleName}();\n"
    }

    result += "\n\t}\n"

    return result

}