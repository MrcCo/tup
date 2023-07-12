package rs.ac.bg.etf.sm203134m.semantic

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.antlr4.TupParserBaseListener
import rs.ac.bg.etf.sm203134m.semantic.results.SemanticAnalysisResults
import rs.ac.bg.etf.sm203134m.semantic.results.SemanticError

class SemanticAnalyzer : TupParserBaseListener() {

    val results = SemanticAnalysisResults()
    val metadata = TestMetadata()


    // request step validation
    override fun enterExecuteApiRequest(ctx: TupParser.ExecuteApiRequestContext?) {

        metadata.requiresOkhttp = true
        metadata.hasPreviousRequests = true

    }

    override fun enterHttpMethod(ctx: TupParser.HttpMethodContext?) {
        ctx?.let { results.appendValidationResponse(it.validate()) }
    }

    override fun enterRequest(ctx: TupParser.RequestContext?) {
        ctx?.let { results.appendValidationResponse(it.validate()) }
    }

    override fun enterHeaderPair(ctx: TupParser.HeaderPairContext?) {
        ctx?.let { results.appendValidationResponse(it.validate()) }
    }

    override fun enterRequestBody(ctx: TupParser.RequestBodyContext?) {
        ctx?.let { results.appendValidationResponse(it.validate()) }
    }

    // response code validation step validation
    override fun enterAssertResponseCode(ctx: TupParser.AssertResponseCodeContext?) {
        if (!metadata.hasPreviousRequests) {
            results.appendValidationResponse(
                ValidationResponse(SemanticError("You cannot perform assertions without previously creating any requests!"))
            )
        } else {
            ctx?.let { results.appendValidationResponse(it.validate()) }
        }
    }

    override fun enterAssertResponseBody(ctx: TupParser.AssertResponseBodyContext?) {
        if (!metadata.hasPreviousRequests) {
            results.appendValidationResponse(
                ValidationResponse(SemanticError("You cannot perform assertions without previously creating any requests!"))
            )
        }
    }

    override fun enterAssertResponseBodyContainsField(ctx: TupParser.AssertResponseBodyContainsFieldContext?) {
        if (!metadata.hasPreviousRequests) {
            results.appendValidationResponse(
                ValidationResponse(SemanticError("You cannot perform assertions without previously creating any requests!"))
            )
        }
        metadata.requiresObjectMapper = true
    }

    // todo validate available browsers
    // todo figure out how to add a warning about browser definition not needed
    override fun enterBrowserDefinition(ctx: TupParser.BrowserDefinitionContext?) {

        metadata.requiresSelenium = true

    }

    override fun enterOpenWebPage(ctx: TupParser.OpenWebPageContext?) {
        metadata.requiresSelenium = true
    }

    override fun enterClickOnElementWithXPath(ctx: TupParser.ClickOnElementWithXPathContext?) {

        metadata.requiresSelenium = true
        metadata.requiresSeleniumBy = true

    }

}
