package semantic

import TupParser.TestTypeContext
import TupParserBaseListener

class SemanticAnalyzer: TupParserBaseListener() {

    private val results = SemanticAnalysisResults()

    override fun enterTestType(ctx: TestTypeContext) {
        results.appendValidationResponse(ctx.validate())
    }

    override fun enterHttpMethod(ctx: TupParser.HttpMethodContext?) {
        ctx?.let { results.appendValidationResponse(it.validate()) }
    }

}
