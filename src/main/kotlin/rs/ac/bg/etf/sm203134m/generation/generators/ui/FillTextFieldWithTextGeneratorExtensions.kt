package rs.ac.bg.etf.sm203134m.generation.generators.ui

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.driverElementWithXPathCallMethod

fun TupParser.FillTextFieldWithValueContext.generateOnEntry(): String {
    val xpathString = Commons.escapeSingleQuoteString(this.SINGLE_QUOTE_STRING().text)
    val valueString = this.STRING().text
    return driverElementWithXPathCallMethod(xpathString, "sendKeys", listOf(valueString))

}