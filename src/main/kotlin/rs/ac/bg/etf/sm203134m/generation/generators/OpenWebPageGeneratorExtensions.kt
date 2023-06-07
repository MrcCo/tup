package rs.ac.bg.etf.sm203134m.generation.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser

fun TupParser.OpenWebPageContext.generateOnEntry(): String {

    return "\t\tdriver.get(${this.STRING()});\n"
}