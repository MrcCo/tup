package rs.ac.bg.etf.sm203134m.generation.generators.api.assertions

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.assertEqual
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.variableAssignment
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable

fun TupParser.AssertResponseBodyContainsFieldContext.generateOnEntry(symbolTable: SymbolTable): String {


    val responseFieldName = symbolTable.createNextResponseField()
    val lastResponse = symbolTable.responseBodies.last()
    val field = STRING(0)
    val value = STRING(1)

    return variableAssignment(responseFieldName, "objectMapper.readTree($lastResponse).get($field).asText()") +
            assertEqual(value.text, responseFieldName)



}