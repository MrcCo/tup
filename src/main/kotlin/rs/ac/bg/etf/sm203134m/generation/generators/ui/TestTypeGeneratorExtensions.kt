package rs.ac.bg.etf.sm203134m.generation.generators.ui

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.edge.EdgeDriver
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun TupParser.TestTypeContext.generateOnEntry(metadata: TestMetadata): String {

    return if(this.API() != null) {
        var fieldDeclarations = "\tprivate ${OkHttpClient::class.simpleName} client; \n"


        if (metadata.requiresObjectMapper) {
            fieldDeclarations += "\tprivate ${ObjectMapper::class.simpleName} objectMapper; \n"
        }

        fieldDeclarations
    } else {

        "\tprivate ${WebDriver::class.simpleName} driver;\n"

    }
}

fun TupParser.TestTypeContext.generateOnExit(metadata: TestMetadata): String {

    var lifecycleMethods = "\t@${BeforeEach::class.simpleName}\n\tvoid setup() {\n"
    return if (this.API() != null) {

        lifecycleMethods += "\t\tclient = new ${OkHttpClient::class.simpleName}();\n"

        if (metadata.requiresObjectMapper) {
            lifecycleMethods += "\t\tobjectMapper = new ${ObjectMapper::class.simpleName}();\n"
        }
        "$lifecycleMethods\t}\n"
    } else {

        """
${'\t'}@${BeforeEach::class.simpleName}
${'\t'}void setup() {
${'\t'}${'\t'}WebDriverManager.edgedriver().setup();
${'\t'}${'\t'}driver = new ${EdgeDriver::class.simpleName}();
${'\t'}}
            
${'\t'}@${AfterEach::class.simpleName}
${'\t'}void teardown() {
${'\t'}${'\t'}driver.close();
${'\t'}}
        """

    }


}