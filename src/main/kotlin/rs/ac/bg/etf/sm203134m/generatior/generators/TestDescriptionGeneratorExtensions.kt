package rs.ac.bg.etf.sm203134m.generatior.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser

fun TupParser.TestDescriptionContext.generateOnEntry(): String {
    val description = this.STRING().toString()
    return "\t/* \t $description \t */\n"
}