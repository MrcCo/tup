import antlr4.TupLexer
import antlr4.TupParser
import generatior.CodeGenerator
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import semantic.SemanticAnalyzer

fun main() {

    val filePath = "C:\\Users\\Windows 10\\IdeaProjects\\TUP\\src\\main\\resources\\test.tup"


    val inputStream = CharStreams.fromFileName(filePath)
    val lexer = TupLexer(inputStream)
    val tokenStream = CommonTokenStream(lexer)
    val parser = TupParser(tokenStream)
    val walker = ParseTreeWalker()

    val semanticListener = SemanticAnalyzer()
    walker.walk(semanticListener, parser.test())
    println(semanticListener.getResults())
    println(semanticListener.getMetadata())
    parser.reset()
    val generator = CodeGenerator(semanticListener.getMetadata())
    walker.walk(generator, parser.test())
    println(generator.code)
}