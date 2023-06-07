package rs.ac.bg.etf.sm203134m.generation

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.antlr4.TupParserBaseListener
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable
import rs.ac.bg.etf.sm203134m.generation.generators.generateImportsFromMetadata
import rs.ac.bg.etf.sm203134m.generation.generators.assertions.api.generateOnEntry
import rs.ac.bg.etf.sm203134m.generation.generators.generateOnEntry
import rs.ac.bg.etf.sm203134m.generation.generators.generateOnExit
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

class CodeGenerator(val metadata: TestMetadata) : TupParserBaseListener() {


    var code = ""
    private val symbolTable = SymbolTable()
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
        code += ctx?.generateOnEntry(metadata)
    }

    override fun exitTestType(ctx: TupParser.TestTypeContext?) {
        code += ctx?.generateOnExit(metadata)
    }

    override fun enterTestSteps(ctx: TupParser.TestStepsContext?) {
        code += ctx?.generateOnEntry(metadata)
    }

    override fun exitTestSteps(ctx: TupParser.TestStepsContext?) {
        code += ctx?.generateOnExit(symbolTable)
    }

    override fun enterRequestStep(ctx: TupParser.RequestStepContext?) {
        code += ctx?.generateOnEntry(symbolTable)
    }


    override fun enterAssertResponseCode(ctx: TupParser.AssertResponseCodeContext?) {
        code += ctx?.generateOnEntry(symbolTable)
    }

    override fun enterAssertResponseBody(ctx: TupParser.AssertResponseBodyContext?) {
        code += ctx?.generateOnEntry(symbolTable)
    }

    override fun enterAssertResponseBodyContainsField(ctx: TupParser.AssertResponseBodyContainsFieldContext?) {
        code += ctx?.generateOnEntry(symbolTable)
    }

}