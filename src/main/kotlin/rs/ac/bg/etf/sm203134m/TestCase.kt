package rs.ac.bg.etf.sm203134m

import rs.ac.bg.etf.sm203134m.generation.CodeGenerator
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import rs.ac.bg.etf.sm203134m.antlr4.TupLexer
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.semantic.results.SemanticAnalysisResults
import rs.ac.bg.etf.sm203134m.semantic.SemanticAnalyzer
import rs.ac.bg.etf.sm203134m.writer.CodeWriter

class TestCase(originPath: String) {

    val name: String
    val code: String
    val semanticAnalysisResults: SemanticAnalysisResults

    init {
        val tokenStream = CommonTokenStream(TupLexer(CharStreams.fromFileName(originPath)))

        val parser = TupParser(tokenStream)
        val walker = ParseTreeWalker()

        val semanticListener = SemanticAnalyzer()
        walker.walk(semanticListener, parser.test())

        parser.reset()

        val generator = CodeGenerator(semanticListener.metadata)
        walker.walk(generator, parser.test())

        name = generator.symbolTable.testClassName
        code = generator.code
        semanticAnalysisResults = semanticListener.results
    }

    fun isSemanticallyValid(): Boolean {
        return semanticAnalysisResults.isCorrect
    }

    fun getSemanticErrorsAndWarnings(): String {
        return semanticAnalysisResults.warnings.toString() + semanticAnalysisResults.errors.toString()
    }


    fun writeCode(destinationDirectory: String) {
        CodeWriter(destinationDirectory).write("${name}.java", code)
    }

}