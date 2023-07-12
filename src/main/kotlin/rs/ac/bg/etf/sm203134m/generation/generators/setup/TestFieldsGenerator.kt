package rs.ac.bg.etf.sm203134m.generation.generators.setup

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.openqa.selenium.WebDriver
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun generateTestFields(metadata: TestMetadata): String {
    var result = "";
    if(metadata.requiresOkhttp) {
        result += "\tprivate ${OkHttpClient::class.simpleName} client; \n"
    }

    if (metadata.requiresObjectMapper) {
        result += "\tprivate ${ObjectMapper::class.simpleName} objectMapper; \n"
    }

    if(metadata.requiresSelenium) {
        result += "\tprivate ${WebDriver::class.simpleName} driver;\n"
    }

    return result

}