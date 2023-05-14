package generatior.generators

import antlr4.TupParser
import org.apache.commons.text.StringEscapeUtils
import org.junit.jupiter.api.Assertions

fun TupParser.ResponseBodyIsStepContext.generateOnEntry(): String {
    val body = StringEscapeUtils.escapeJava(STRING().text)
        .replace("'", "\"")
        .replace("\\s", "")
    return "\t\t${Assertions::class.simpleName}.assertEquals($body, response.body().string().replaceAll(\"\\\\s\", \"\"));\n"
}