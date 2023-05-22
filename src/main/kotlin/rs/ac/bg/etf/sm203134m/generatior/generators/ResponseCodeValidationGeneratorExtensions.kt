package rs.ac.bg.etf.sm203134m.generatior.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import org.junit.jupiter.api.Assertions

fun TupParser.ResponseCodeValidationStepContext.generateOnEntry(symbolTable: SymbolTable): String {
    return "\t\t${Assertions::class.simpleName}.assertEquals(${this.statusCode().INTEGER().toString().toInt()}, response.code());\n"
}
