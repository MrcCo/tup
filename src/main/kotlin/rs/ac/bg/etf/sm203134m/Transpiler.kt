package rs.ac.bg.etf.sm203134m

import rs.ac.bg.etf.sm203134m.antlr4.TupLexer
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.generatior.CodeGenerator
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import rs.ac.bg.etf.sm203134m.semantic.SemanticAnalyzer
import rs.ac.bg.etf.sm203134m.writer.CodeWriter

class Transpiler(private val originPath: String) {

    val parser: TupParser
    val walker: ParseTreeWalker
    init {
        val inputStream = CharStreams.fromFileName(originPath)
        val lexer = TupLexer(inputStream)
        val tokenStream = CommonTokenStream(lexer)

        parser = TupParser(tokenStream)
        walker = ParseTreeWalker()
    }

    fun writeCode(destinationDirectory: String) {
        val semanticListener = SemanticAnalyzer()
        walker.walk(semanticListener, parser.test())
        parser.reset()
        val generator = CodeGenerator(semanticListener.getMetadata())
        walker.walk(generator, parser.test())
        CodeWriter(destinationDirectory).write("${generator.metadata.testName}.java", generator.code)
    }

}