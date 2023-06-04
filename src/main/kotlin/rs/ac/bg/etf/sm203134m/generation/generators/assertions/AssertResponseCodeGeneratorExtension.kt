package rs.ac.bg.etf.sm203134m.generation.generators.assertions

import org.junit.jupiter.api.Assertions
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable

fun TupParser.AssertResponseCodeContext.generateOnEntry(symbolTable: SymbolTable): String {
    val responseVariable = symbolTable.responseCodes.last()
    return "\t\t${Assertions::class.simpleName}.assertEquals(${this.INTEGER().toString().toInt()}, $responseVariable);\n"
}
