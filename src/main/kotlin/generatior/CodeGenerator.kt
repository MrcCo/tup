package generatior

import antlr4.TupParser
import antlr4.TupParserBaseListener
import generatior.generators.SymbolTable
import generatior.generators.generateImportsFromMetadata
import generatior.generators.generateOnEntry
import generatior.generators.generateOnExit
import semantic.TestMetadata

class CodeGenerator(val metadata: TestMetadata) : TupParserBaseListener() {


    var code = ""

    init {
        code += generateImportsFromMetadata(metadata)
    }


    override fun enterTest(ctx: TupParser.TestContext?) {
        code += ctx?.generateOnEntry()
    }

    override fun exitTest(ctx: TupParser.TestContext?) {
        code += ctx?.generateOnExit()
    }

    override fun enterTestDescription(ctx: TupParser.TestDescriptionContext?) {
        code += ctx?.generateOnEntry()
    }

    override fun enterTestType(ctx: TupParser.TestTypeContext?) {
        code += ctx?.generateOnEntry()
    }

    override fun exitTestType(ctx: TupParser.TestTypeContext?) {
        code += ctx?.generateOnExit()
    }

    override fun enterTestSteps(ctx: TupParser.TestStepsContext?) {
        code += ctx?.generateOnEntry(metadata = this.metadata)
    }

    override fun exitTestSteps(ctx: TupParser.TestStepsContext?) {
        code += ctx?.generateOnExit(SymbolTable())
    }

    override fun enterRequestStep(ctx: TupParser.RequestStepContext?) {
        code += ctx?.generateOnEntry(SymbolTable())
    }

    override fun enterResponseCodeValidationStep(ctx: TupParser.ResponseCodeValidationStepContext?) {
        code += ctx?.generateOnEntry(SymbolTable())
    }

    override fun enterResponseBodyIsStep(ctx: TupParser.ResponseBodyIsStepContext?) {
        code += ctx?.generateOnEntry()
    }

}