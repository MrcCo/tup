package generatior.generators

import antlr4.TupParser

fun TupParser.TestDescriptionContext.generateOnEntry(): String {
    val description = this.STRING().toString()
    return "\t/* \t $description \t */\n"
}