package rs.ac.bg.etf.sm203134m.generation.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser

fun TupParser.TestContext.generateOnEntry(): String {
    val className = this.testName().IDENTIFIER().toString()
    return "public class $className {\n\n"
}

fun TupParser.TestContext.generateOnExit(): String {
    return "\n}"
}
