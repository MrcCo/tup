package rs.ac.bg.etf.sm203134m.generation.generators.assertions.selenium

import org.junit.jupiter.api.Assertions
import rs.ac.bg.etf.sm203134m.antlr4.TupParser

fun TupParser.AssertThatCurrentPageIsContext.generateOnEntry(): String {
    return "\t\t${Assertions::class.simpleName}.assertEquals(${STRING()}, driver.getCurrentUrl());\n"
}