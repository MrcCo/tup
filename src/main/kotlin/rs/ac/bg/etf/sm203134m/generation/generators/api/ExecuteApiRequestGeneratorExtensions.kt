package rs.ac.bg.etf.sm203134m.generation.generators.api

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.escapeSingleQuoteString
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.jsonRequestBody
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.requestBuilderBuild
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.requestBuilderHeaders
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.requestBuilderMethod
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.requestBuilderURL
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.unfinishedVariableAssignment
import rs.ac.bg.etf.sm203134m.generation.generators.Commons.variableAssignment
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable
import java.util.Objects

fun TupParser.ExecuteApiRequestContext.generateOnEntry(symbolTable: SymbolTable): String {

    val request = symbolTable.createNextRequest()
    val response = symbolTable.createNextResponse()
    return unfinishedVariableAssignment(request) + "new Request.Builder()\n" +
            requestBuilderURL(getUrlString()) +
            requestBuilderMethod(getRequestMethod(), jsonRequestBody(escapeSingleQuoteString(getRequestBodyString()))) +
            requestBuilderHeadersIfNecessary() +
            requestBuilderBuild() +
            variableAssignment(response,"client.newCall($request).execute()") +
            variableAssignment(symbolTable.createNextResponseCode(),"$response.code()") +
            variableAssignment(symbolTable.createNextResponseBody(), "$response.body() != null ? $response.body().string() : \"\"")
}


private fun TupParser.ExecuteApiRequestContext.getRequestMethod(): String {
    return if (Objects.isNull(this.request().httpMethod())) {
        "get"
    } else {
        this.request().httpMethod().IDENTIFIER().toString().lowercase()
    }
}

private fun TupParser.ExecuteApiRequestContext.getUrlString(): String {
    return this.request().STRING().toString()
}

fun TupParser.ExecuteApiRequestContext.getRequestBodyString(): String {
    return this.requestBody()?.SINGLE_QUOTE_STRING()?.toString() ?: ""
}

fun TupParser.ExecuteApiRequestContext.hasHeaders(): Boolean {
    return this.requestHeaders() != null
            && this.requestHeaders().headerPair().isNotEmpty()
}

fun TupParser.ExecuteApiRequestContext.getRequestHeadersString(): String {

    return this.requestHeaders()
        .headerPair().joinToString(",") {
            it.STRING().joinToString(",") { i -> i.toString() }
        }

}

private fun TupParser.ExecuteApiRequestContext.requestBuilderHeadersIfNecessary() =
    if (hasHeaders()) requestBuilderHeaders(getRequestHeadersString()) else {
        ""
    }

