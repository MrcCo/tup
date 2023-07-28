package rs.ac.bg.etf.sm203134m.generation.generators.setup

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.openqa.selenium.WebDriver
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.fieldDefinition
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun generateTestFields(metadata: TestMetadata): String {
    var result = "";

    if(metadata.requiresOkhttp)
        result += fieldDefinition(OkHttpClient::class.simpleName!!, "client")

    if (metadata.requiresObjectMapper)
        result += fieldDefinition(ObjectMapper::class.simpleName!!, "objectMapper")

    if(metadata.requiresSelenium)
        result += fieldDefinition(WebDriver::class.simpleName!!, "driver")

    return result

}