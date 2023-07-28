package rs.ac.bg.etf.sm203134m.generation.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser

fun TupParser.TestDescriptionContext.generateOnEntry(): String {
    val description = this.STRING().toString()
    return Commons.multilineComment(description.substring(1 until description.length - 1))
}