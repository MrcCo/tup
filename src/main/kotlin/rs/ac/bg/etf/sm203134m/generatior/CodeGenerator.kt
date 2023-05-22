package rs.ac.bg.etf.sm203134m.generatior

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.antlr4.TupParserBaseListener
import rs.ac.bg.etf.sm203134m.generatior.generators.SymbolTable
import rs.ac.bg.etf.sm203134m.generatior.generators.generateImportsFromMetadata
import rs.ac.bg.etf.sm203134m.generatior.generators.generateOnEntry
import rs.ac.bg.etf.sm203134m.generatior.generators.generateOnExit
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

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