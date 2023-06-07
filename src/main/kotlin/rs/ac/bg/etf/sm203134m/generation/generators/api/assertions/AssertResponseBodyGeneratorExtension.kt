package rs.ac.bg.etf.sm203134m.generation.generators.api.assertions

import org.apache.commons.text.StringEscapeUtils
import org.junit.jupiter.api.Assertions
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable

fun TupParser.AssertResponseBodyContext.generateOnEntry(symbolTable: SymbolTable): String {
    val body = StringEscapeUtils.escapeJava(STRING().text)
        .replace("'", "\"")
        .replace("\\s", "")
    val responseBody = symbolTable.responseBodies.last()
    return "\t\t${Assertions::class.simpleName}.assertEquals($body, $responseBody.replaceAll(\"\\\\s\", \"\"));\n"
}