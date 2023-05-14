package generatior.generators

import antlr4.TupParser
import org.junit.jupiter.api.Test
import semantic.TestMetadata


fun TupParser.TestStepsContext.generateOnEntry(metadata: TestMetadata): String {
    var methodSignature = "\n\t@${Test::class.simpleName} \n\tvoid test()"

    if(metadata.requiresOkhttp) {
        methodSignature +=  " throws IOException "
    }
    methodSignature += "{\n"
    return methodSignature
}

fun TupParser.TestStepsContext.generateOnExit(symbolTable: SymbolTable): String {
    return "\t\tresponse.close()\n\t}\n"
}