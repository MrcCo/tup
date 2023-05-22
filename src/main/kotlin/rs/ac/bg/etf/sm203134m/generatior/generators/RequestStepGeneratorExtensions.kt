package rs.ac.bg.etf.sm203134m.generatior.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import java.util.Objects

fun TupParser.RequestStepContext.generateOnEntry(symbolTable: SymbolTable): String {

    var code = ""
    // todo custom naming from sym table
    code += "\t\tvar request = new Request.Builder()\n"
    code += "\t\t\t.url(${this.getUrlString()})\n"
    code += if(this.hasRequestBody()) {
        val mediaType = "MediaType.parse(\"application/json\")"
        "\t\t\t.${this.getRequestMethod()}(RequestBody.create(${this.getRequestBodyString()}, ${mediaType}))\n"
    } else {
        "\t\t\t.${this.getRequestMethod()}()\n"
    }
    if(hasHeaders()) {
        code += "\t\t\t.headers(Headers.of(${this.getRequestHeadersString()}))\n"
    }
    code += "\t\t\t.build();\n"


    code += "\t\tvar response = client.newCall(request).execute();\n"
    return code
}

private fun TupParser.RequestStepContext.getRequestMethod(): String {
    return if (Objects.isNull(this.request().httpMethod())) {
        "get"
    } else {
        this.request().httpMethod().IDENTIFIER().toString().lowercase()
    }
}

private fun TupParser.RequestStepContext.getUrlString(): String {
    return this.request().STRING().toString()
}

fun TupParser.RequestStepContext.hasRequestBody(): Boolean {
    return this.requestBody() != null
}

fun TupParser.RequestStepContext.getRequestBodyString(): String {
    return this.requestBody()?.STRING()?.toString() ?: ""
}

fun TupParser.RequestStepContext.hasHeaders(): Boolean {
    return this.requestHeaders() != null
            && this.requestHeaders().headerPair().isNotEmpty()
}

fun TupParser.RequestStepContext.getRequestHeadersString(): String {

    return this.requestHeaders()
        .headerPair()
        .map {
            it.STRING()
                .map { i -> i.toString() }
                .joinToString(",")
        }.joinToString(",")

}