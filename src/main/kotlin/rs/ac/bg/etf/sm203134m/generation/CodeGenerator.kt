package rs.ac.bg.etf.sm203134m.generation

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.antlr4.TupParserBaseListener
import rs.ac.bg.etf.sm203134m.generation.generators.SymbolTable
import rs.ac.bg.etf.sm203134m.generation.generators.api.assertions.generateOnEntry
import rs.ac.bg.etf.sm203134m.generation.generators.ui.assertions.generateOnEntry
import rs.ac.bg.etf.sm203134m.generation.generators.api.generateOnEntry
import rs.ac.bg.etf.sm203134m.generation.generators.generateOnEntry
import rs.ac.bg.etf.sm203134m.generation.generators.generateOnExit
import rs.ac.bg.etf.sm203134m.generation.generators.setup.generateOnEntry
import rs.ac.bg.etf.sm203134m.generation.generators.ui.generateOnEntry
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata

class CodeGenerator(private val metadata: TestMetadata) : TupParserBaseListener() {


    var code = ""
    val symbolTable = SymbolTable()

    override fun enterTest(ctx: TupParser.TestContext?) {
        code += ctx?.generateOnEntry(metadata)
    }

    override fun enterTestName(ctx: TupParser.TestNameContext?) {
        code += ctx?.generateOnEntry(symbolTable)
    }

    override fun enterTestDescription(ctx: TupParser.TestDescriptionContext?) {
        code += ctx?.generateOnEntry()
    }

    override fun enterTestSteps(ctx: TupParser.TestStepsContext?) {
        code += ctx?.generateOnEntry(metadata)
    }

    override fun enterExecuteApiRequest(ctx: TupParser.ExecuteApiRequestContext?) {
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

    override fun enterOpenWebPage(ctx: TupParser.OpenWebPageContext?) {
        code += ctx?.generateOnEntry()
    }

    override fun enterAssertThatCurrentPageIs(ctx: TupParser.AssertThatCurrentPageIsContext?) {
        code += ctx?.generateOnEntry()
    }

    override fun enterAssertThatTitleIs(ctx: TupParser.AssertThatTitleIsContext?) {
        code += ctx?.generateOnEntry()
    }

    override fun exitTestSteps(ctx: TupParser.TestStepsContext?) {
        code += ctx?.generateOnExit(symbolTable)
    }

    override fun exitTest(ctx: TupParser.TestContext?) {
        code += ctx?.generateOnExit()
    }

}