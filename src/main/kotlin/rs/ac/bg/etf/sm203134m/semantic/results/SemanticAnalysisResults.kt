package rs.ac.bg.etf.sm203134m.semantic.results

import rs.ac.bg.etf.sm203134m.semantic.ValidationResponse

class SemanticAnalysisResults {

    var isCorrect = true
    var warnings = mutableListOf<SemanticWarning>()
    var errors =  mutableListOf<SemanticError>()


    private fun incorrect() {
        isCorrect = false
    }

    private fun addWarning(warning: String) {
        warnings += SemanticWarning(warning)
    }

    private fun addError(error: String) {
        errors += SemanticError(error)
    }

    fun appendValidationResponse(response: ValidationResponse) {
        if(!response.isValid) incorrect()
        if(response.warning.isNotEmpty()) addWarning(response.warning)
        if(response.error.isNotEmpty()) addError(response.error)
    }

    override fun toString(): String {
        return "SemanticAnalysisResults(isCorrect=$isCorrect, warnings='$warnings', errors='$errors')"
    }

}