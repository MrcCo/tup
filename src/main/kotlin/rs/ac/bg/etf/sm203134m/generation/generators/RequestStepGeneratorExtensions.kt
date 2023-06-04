package rs.ac.bg.etf.sm203134m.generation.generators

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import java.util.Objects

fun TupParser.RequestStepContext.generateOnEntry(symbolTable: SymbolTable): String {

    var code = ""
    // todo custom naming from sym table
    val request = symbolTable.createNextRequest()
    code += "\t\tvar $request = new Request.Builder()\n"
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

    val response = symbolTable.createNextResponse()
    code += "\t\tvar $response = client.newCall($request).execute();\n"

    val responseCode = symbolTable.createNextResponseCode()
    code += "\t\tvar $responseCode = $response.code();\n"

    val responseBody = symbolTable.createNextResponseBody()
    code += "\t\tvar $responseBody = $response.body() != null ? $response.body().string() : \"\";\n\n"

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
        .headerPair().joinToString(",") {
            it.STRING().joinToString(",") { i -> i.toString() }
        }

}

