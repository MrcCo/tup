package rs.ac.bg.etf.sm203134m.generation.generators


import org.junit.jupiter.api.Test
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.setup.SetupGenerator
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata


fun TupParser.TestStepsContext.generateOnEntry(metadata: TestMetadata): String {

    val setup = SetupGenerator(metadata).generate()
    var methodSignature = "\n\t@${Test::class.simpleName} \n\tvoid test()"

    if(metadata.requiresOkhttp) {
        methodSignature +=  " throws IOException "
    }
    methodSignature += "{\n\n"
    return setup + methodSignature
}

fun TupParser.TestStepsContext.generateOnExit(symbolTable: SymbolTable): String {
    var closeStatements = "";
    symbolTable.responses.forEach {
        closeStatements += "\t\t$it.close();\n"
    }
    closeStatements += "\t}"
    return closeStatements
}