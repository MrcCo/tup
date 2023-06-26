package rs.ac.bg.etf.sm203134m.semantic

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.antlr4.TupParserBaseListener
import rs.ac.bg.etf.sm203134m.semantic.results.SemanticAnalysisResults

class SemanticAnalyzer : TupParserBaseListener() {

    val results = SemanticAnalysisResults()
    val metadata = TestMetadata()
    private var hasPreviousRequests = false


    // validates for available test types
    override fun enterTestType(ctx: TupParser.TestTypeContext) {
        if (ctx.API() != null) {
            metadata.requiresOkhttp = true
        } else if(ctx.UI() != null) {
            metadata.requiresSelenium = true
        }
    }

    // request step validation
    // todo - forbid request steps in ui tests
    // validates if http method is valid
    override fun enterExecuteApiRequest(ctx: TupParser.ExecuteApiRequestContext?) {
        hasPreviousRequests = true
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
        if (!hasPreviousRequests) {
            results.appendValidationResponse(
                ValidationResponse(
                    false,
                    "",
                    "You cannot perform assertions without previously creating any requests!"
                )
            )
        } else {
            ctx?.let { results.appendValidationResponse(it.validate()) }
        }
    }

    override fun enterAssertResponseBody(ctx: TupParser.AssertResponseBodyContext?) {
        if (!hasPreviousRequests) {
            results.appendValidationResponse(
                ValidationResponse(
                    false,
                    "",
                    "You cannot perform assertions without previously creating any requests!"
                )
            )
        }
    }

    override fun enterAssertResponseBodyContainsField(ctx: TupParser.AssertResponseBodyContainsFieldContext?) {
        if (!hasPreviousRequests) {
            results.appendValidationResponse(
                ValidationResponse(
                    false,
                    "",
                    "You cannot perform assertions without previously creating any requests!"
                )
            )
        }
        metadata.requiresObjectMapper = true
    }

    override fun enterClickOnElementWithXPath(ctx: TupParser.ClickOnElementWithXPathContext?) {

        metadata.requiresSeleniumBy = true

    }

}
