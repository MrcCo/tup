package rs.ac.bg.etf.sm203134m.generation.generators.ui.assertions

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons

fun TupParser.AssertThatCurrentPageIsContext.generateOnEntry(): String {
    return Commons.assertEqual(STRING().text, "driver.getCurrentUrl()")
}