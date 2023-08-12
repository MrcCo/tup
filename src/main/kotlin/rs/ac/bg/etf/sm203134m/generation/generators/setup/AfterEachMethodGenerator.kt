package rs.ac.bg.etf.sm203134m.generation.generators.setup

import org.junit.jupiter.api.AfterEach
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.closeBraces
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.methodAnnotation
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.voidMethodDefinition
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata


fun generateAfterEachMethod(metadata: TestMetadata): String {

    if (!metadata.requiresSelenium) {
        return ""
    }

    return methodAnnotation(AfterEach::class.simpleName!!) +
            voidMethodDefinition("teardown", emptyMap()) +
            "\t\t driver.close();\n" +
            closeBraces()

}