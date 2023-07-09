package rs.ac.bg.etf.sm203134m.semantic.results

import rs.ac.bg.etf.sm203134m.semantic.ValidationResponse

class SemanticAnalysisResults {

    var isCorrect = true
    var warnings = mutableListOf<SemanticWarning>()
    var errors =  mutableListOf<SemanticError>()


    private fun incorrect() {
        isCorrect = false
    }

    private fun addWarning(warning: SemanticWarning) {
        warnings += warning
    }

    private fun addError(error: SemanticError) {
        errors += error
    }

    fun appendValidationResponse(response: ValidationResponse) {
        if(!response.isValid) incorrect()
        if(response.warning != null) addWarning(response.warning)
        if(response.error != null) addError(response.error)
    }

    override fun toString(): String {
        return "SemanticAnalysisResults(isCorrect=$isCorrect, warnings='$warnings', errors='$errors')"
    }

}