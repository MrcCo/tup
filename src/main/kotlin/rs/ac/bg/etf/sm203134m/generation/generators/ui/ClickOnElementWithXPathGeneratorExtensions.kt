package rs.ac.bg.etf.sm203134m.generation.generators.ui

import rs.ac.bg.etf.sm203134m.antlr4.TupParser

fun TupParser.ClickElementWithXPathContext.generateOnEntry(): String {

    return "\t\tdriver.findElement(By.xpath(${this.STRING()})).click();\n"

}