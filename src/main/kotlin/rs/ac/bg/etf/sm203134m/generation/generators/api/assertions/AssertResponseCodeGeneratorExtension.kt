package rs.ac.bg.etf.sm203134m.generation.generators.api.assertions

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable

fun TupParser.AssertResponseCodeContext.generateOnEntry(symbolTable: SymbolTable): String {
    val responseVariable = symbolTable.responseCodes.last()
    return Commons.assertEqual(this.INTEGER().toString(), responseVariable)
}
