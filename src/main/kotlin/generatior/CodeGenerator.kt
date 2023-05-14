package generatior

import antlr4.TupParser
import antlr4.TupParserBaseListener
import generatior.generators.generateImportsFromMetadata
import generatior.generators.generateOnEntry
import generatior.generators.generateOnExit
import semantic.TestMetadata

class CodeGenerator(metadata: TestMetadata) : TupParserBaseListener() {


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
}