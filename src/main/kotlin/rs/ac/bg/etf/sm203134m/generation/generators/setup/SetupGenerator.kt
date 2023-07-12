package rs.ac.bg.etf.sm203134m.generation.generators.setup

import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

class SetupGenerator(private val metadata: TestMetadata) {

    fun generate(): String {
        return generateTestFields(metadata) +
                generateBeforeEachMethod(metadata) +
                generateBrowserSetupMethod(metadata) +
                generateAfterEachMethod(metadata)
    }
}