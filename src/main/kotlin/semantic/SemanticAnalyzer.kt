package semantic

import antlr4.TupParser
import antlr4.TupParserBaseListener

class SemanticAnalyzer: TupParserBaseListener() {

    private val results = SemanticAnalysisResults()
    private var hasPreviousRequests = false

    // validates for available test types
    override fun enterTestType(ctx: TupParser.TestTypeContext) {
        results.appendValidationResponse(ctx.validate())
    }

    // request step validation
    // validates if http method is valid
    override fun enterRequestStep(ctx: TupParser.RequestStepContext?) {
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
    override fun enterResponseCodeValidationStep(ctx: TupParser.ResponseCodeValidationStepContext?) {
        if(!hasPreviousRequests) {
            results.appendValidationResponse(ValidationResponse(false, "", "You cannot perform assertions without previously creating any requests!"))
        } else {
            ctx?.let { results.appendValidationResponse(it.validate()) }
        }
    }

    override fun enterResponseBodyIsStep(ctx: TupParser.ResponseBodyIsStepContext?) {
        if(!hasPreviousRequests) {
            results.appendValidationResponse(ValidationResponse(false, "", "You cannot perform assertions without previously creating any requests!"))
        }
    }

    override fun enterResponseBodyContainsFieldStep(ctx: TupParser.ResponseBodyContainsFieldStepContext?) {
        if(!hasPreviousRequests) {
            results.appendValidationResponse(ValidationResponse(false, "", "You cannot perform assertions without previously creating any requests!"))
        }
    }

    fun getResults(): SemanticAnalysisResults {
        return results
    }
}
