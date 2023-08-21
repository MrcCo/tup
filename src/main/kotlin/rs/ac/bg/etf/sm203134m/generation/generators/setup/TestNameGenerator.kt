package rs.ac.bg.etf.sm203134m.generation.generators.setup

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable

fun TupParser.TestNameContext.generateOnEntry(symbolTable: SymbolTable): String {
    val className = this.IDENTIFIER().toString()
    symbolTable.testClassName = className
    return "\n\npublic class $className {\n\n"
}