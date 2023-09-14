package rs.ac.bg.etf.sm203134m.generation.generators.api.assertions

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.assertEqual
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.escapeSingleQuoteString
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable

fun TupParser.AssertResponseBodyContext.generateOnEntry(symbolTable: SymbolTable): String {
    val body = escapeSingleQuoteString(SINGLE_QUOTE_STRING().text)
    val responseBody = symbolTable.responseBodies.last()
    return assertEqual(body, "$responseBody.replaceAll(\" \", \"\")")
}