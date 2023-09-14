package rs.ac.bg.etf.sm203134m.generation.generators.setup

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.junit.jupiter.api.BeforeAll
import rs.ac.bg.etf.sm203134m.generation.generators.Commons
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun generateBeforeAllMethod(metadata: TestMetadata): String {

    if(!metadata.requiresOkhttp && !metadata.requiresObjectMapper && !metadata.requiresSelenium)
        return ""


    var result = Commons.annotation(BeforeAll::class.simpleName!!) +
            Commons.staticVoidMethodDefinition("setup", emptyMap())
    if (metadata.requiresOkhttp) {
        result += "\t\tclient = new ${OkHttpClient::class.simpleName}();\n"
    }
    if (metadata.requiresObjectMapper) {
        result += "\t\tobjectMapper = new ${ObjectMapper::class.simpleName}();\n"
    }

    if(metadata.requiresSelenium) {
        result += "\t\tseleniumJupiter.getConfig().setBrowserTemplateJsonContent(\"\"\"\n${metadata.formattedBrowserRequirements()}\"\"\");\n"
    }



    result += "\n\t}\n"

    return result

}