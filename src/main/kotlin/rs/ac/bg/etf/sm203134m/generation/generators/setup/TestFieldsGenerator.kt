package rs.ac.bg.etf.sm203134m.generation.generators.setup

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.bonigarcia.seljup.SeleniumJupiter
import okhttp3.OkHttpClient
import org.junit.jupiter.api.extension.RegisterExtension
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.annotation
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.staticFieldDefinition
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.staticFieldInitialization
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun generateTestFields(metadata: TestMetadata): String {
    var result = ""

    if(metadata.requiresOkhttp)
        result += staticFieldDefinition(OkHttpClient::class.simpleName!!, "client")

    if (metadata.requiresObjectMapper)
        result += staticFieldDefinition(ObjectMapper::class.simpleName!!, "objectMapper")

    if(metadata.requiresSelenium)
        result += annotation(RegisterExtension::class.simpleName!!) +
                staticFieldInitialization(SeleniumJupiter::class.simpleName!!, "seleniumJupiter", "new ${SeleniumJupiter::class.simpleName!!}()")

    return result

}