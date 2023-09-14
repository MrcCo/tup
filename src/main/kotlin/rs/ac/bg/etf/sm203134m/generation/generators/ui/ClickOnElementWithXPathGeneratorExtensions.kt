package rs.ac.bg.etf.sm203134m.generation.generators.ui

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons

fun TupParser.ClickOnElementWithXPathContext.generateOnEntry(): String {
    val xPathString = Commons.escapeSingleQuoteString(this.SINGLE_QUOTE_STRING().text)
    return Commons.driverElementWithXPathCallMethod(xPathString, "click", emptyList())
}