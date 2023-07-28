package rs.ac.bg.etf.sm203134m.generation.generators


import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.setup.SetupGenerator
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

// todo refactor me plis
fun TupParser.TestStepsContext.generateOnEntry(metadata: TestMetadata): String {

    val setup = SetupGenerator(metadata).generate()
    val methodAnnotation = if (metadata.requiresSelenium) {
        "\n${Commons.methodAnnotation(ParameterizedTest::class.simpleName!!)} @${ValueSource::class.simpleName}(strings = {${metadata.browserRequirements.filter { it.value }.keys.joinToString { '"' + it + '"'}}})\n"
    } else {
        "\n${Commons.methodAnnotation(Test::class.simpleName!!)}"
    }
    var methodSignature = methodAnnotation + if (metadata.requiresSelenium)
        "\n\tvoid test(String browserName)"
    else
        "\n\tvoid test()"
    if (metadata.requiresOkhttp) {
        methodSignature += " throws IOException "
    }
    methodSignature += "{\n\n"
    return setup + methodSignature + if(metadata.requiresSelenium) "\t\tbrowserSetup(browserName);\n" else "";
}

fun TupParser.TestStepsContext.generateOnExit(symbolTable: SymbolTable): String {
    var closeStatements = "";
    symbolTable.responses.forEach {
        closeStatements += "\t\t$it.close();\n"
    }
    closeStatements += "\t}"
    return closeStatements
}