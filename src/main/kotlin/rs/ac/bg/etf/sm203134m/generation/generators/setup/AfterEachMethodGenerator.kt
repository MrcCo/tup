package rs.ac.bg.etf.sm203134m.generation.generators.setup

import org.junit.jupiter.api.AfterEach
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata


fun generateAfterEachMethod(metadata: TestMetadata): String {

    if (!metadata.requiresSelenium) {
        return ""
    }

    return """         
${'\t'}@${AfterEach::class.simpleName}
${'\t'}void teardown() {
${'\t'}${'\t'}driver.close();
${'\t'}}
"""


}