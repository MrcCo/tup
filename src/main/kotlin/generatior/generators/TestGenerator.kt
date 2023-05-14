package generatior.generators

import antlr4.TupParser

fun TupParser.TestContext.generateOnEntry(): String {
    val className = this.testMetaData().testName().IDENTIFIER().toString()
    return "public class $className {\n\n"
}

fun TupParser.TestContext.generateOnExit(): String {
    return "\n}"
}
