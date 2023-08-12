package rs.ac.bg.etf.sm203134m.generation.generators


import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.closeBraces
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.methodAnnotation
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.methodAnnotationWithArguments
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.openBracesInLine
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.voidMethodDefinition
import rs.ac.bg.etf.sm203134m.generation.generators.setup.SetupGenerator
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

// todo refactor me plis
fun TupParser.TestStepsContext.generateOnEntry(metadata: TestMetadata): String {

    return SetupGenerator(metadata).generate() +
            if (metadata.requiresSelenium) {
                methodAnnotation(ParameterizedTest::class.simpleName!!) +
                        methodAnnotationWithArguments(
                            ValueSource::class.simpleName!!,
                            mapOf(Pair("strings", metadata.formattedBrowserRequirements()))
                        )
            } else {
                methodAnnotation(Test::class.simpleName!!)
            } +
            voidMethodDefinition(
                "test",
                if (metadata.requiresSelenium) mapOf(Pair("String", "browserName")) else emptyMap(),
                if (metadata.requiresOkhttp) "IOException" else ""
            ) +
            if (metadata.requiresSelenium) "\t\tbrowserSetup(browserName);\n" else ""

}

fun TupParser.TestStepsContext.generateOnExit(symbolTable: SymbolTable): String {
    var closeStatements = "";
    symbolTable.responses.forEach {
        closeStatements += "\t\t$it.close();\n"
    }
    closeStatements += closeBraces()
    return closeStatements
}