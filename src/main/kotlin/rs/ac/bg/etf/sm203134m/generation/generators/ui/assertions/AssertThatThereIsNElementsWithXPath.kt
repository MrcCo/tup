package rs.ac.bg.etf.sm203134m.generation.generators.ui.assertions

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.assertEqual
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.incompleteDriverFindElementsByXPathCallMethod

fun TupParser.AssertThatThereIsNElementsWithXPathContext.generateOnEntry(): String {
    val xpathString = Commons.escapeSingleQuoteString(this.SINGLE_QUOTE_STRING().text)
    val n = this.INTEGER().text

    return assertEqual(incompleteDriverFindElementsByXPathCallMethod(xpathString, "size", emptyList()), n)

}