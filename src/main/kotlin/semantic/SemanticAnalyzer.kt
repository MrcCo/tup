package semantic

import antlr4.TupParser
import antlr4.TupParserBaseListener

class SemanticAnalyzer: TupParserBaseListener() {

    private val results = SemanticAnalysisResults()
    private val metadata = TestMetadata()
    private var hasPreviousRequests = false

    // validates for available test types
    override fun enterTestType(ctx: TupParser.TestTypeContext) {
        results.appendValidationResponse(ctx.validate())
        val type = ctx.STRING().toString().replace("\"","")
        if(type.equals("rest api", true)) {
            metadata.requiresOkhttp = true
        }
    }

    // request step validation
    // validates if http method is valid
    override fun enterRequestStep(ctx: TupParser.RequestStepContext?) {
        hasPreviousRequests = true
        metadata.requiresOkhttp = true
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
        metadata.requiresAssertions = true
    }

    override fun enterResponseBodyIsStep(ctx: TupParser.ResponseBodyIsStepContext?) {
        if(!hasPreviousRequests) {
            results.appendValidationResponse(ValidationResponse(false, "", "You cannot perform assertions without previously creating any requests!"))
        }
        metadata.requiresAssertions = true
    }

    override fun enterResponseBodyContainsFieldStep(ctx: TupParser.ResponseBodyContainsFieldStepContext?) {
        if(!hasPreviousRequests) {
            results.appendValidationResponse(ValidationResponse(false, "", "You cannot perform assertions without previously creating any requests!"))
        }
        metadata.requiresAssertions = true
    }

    fun getResults(): SemanticAnalysisResults {
        return results
    }

    fun getMetadata(): TestMetadata {
        return metadata
    }
}
