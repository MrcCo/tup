package rs.ac.bg.etf.sm203134m.generation.generators


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.closeBraces
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.annotation
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.staticVoidMethodDefinition
import rs.ac.bg.etf.sm203134m.generation.generators.setup.SetupGenerator
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

fun TupParser.TestStepsContext.generateOnEntry(metadata: TestMetadata): String {

    return SetupGenerator(metadata).generate() +
            if (metadata.requiresSelenium) {
                annotation(TestTemplate::class.simpleName!!)
            } else {
                annotation(Test::class.simpleName!!)
            } +
            staticVoidMethodDefinition(
                "test",
                if (metadata.requiresSelenium) mapOf(Pair("WebDriver", "driver")) else emptyMap(),
                if (metadata.requiresOkhttp) "IOException" else null
            )
}

fun TupParser.TestStepsContext.generateOnExit(symbolTable: SymbolTable): String {

    return if(symbolTable.responses.isNotEmpty()) {
        symbolTable.responses.map { "\t\t$it.close();\n" }.reduce { acc, s -> "$acc$s" } + closeBraces()
    } else {
        closeBraces()
    }

}