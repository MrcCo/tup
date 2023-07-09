package rs.ac.bg.etf.sm203134m

import rs.ac.bg.etf.sm203134m.semantic.results.SemanticAnalysisResults
import rs.ac.bg.etf.sm203134m.semantic.results.SemanticError
import java.lang.RuntimeException

class TestSuite(val tests: List<Test>) {

    val validationResult = SemanticAnalysisResults()

    init {

        val testsWithSameNames = tests.groupingBy { it.name }.eachCount().filter { (_, v) -> v > 1 }
        if(testsWithSameNames.isNotEmpty()) {
            validationResult.isCorrect = false
            validationResult.errors += SemanticError("Multiple tests have same names: ${testsWithSameNames.keys}")
        }

        tests.forEach {
            validationResult.isCorrect =  validationResult.isCorrect && it.semanticAnalysisResults.isCorrect
            validationResult.warnings += it.semanticAnalysisResults.warnings
            validationResult.errors += it.semanticAnalysisResults.errors
        }

    }

    fun write(path: String) {
        if(!validationResult.isCorrect) {
            throw RuntimeException("Cannot write an incorrect test suit!")
        }

        tests.forEach {
            it.writeCode(path)
        }
    }

}