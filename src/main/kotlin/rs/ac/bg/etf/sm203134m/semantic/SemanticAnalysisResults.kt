package rs.ac.bg.etf.sm203134m.semantic

class SemanticAnalysisResults {

    var isCorrect = true
    var warnings = ""
    var errors = ""


    fun incorrect() {
        isCorrect = false
    }

    fun addWarning(warning: String) {
        warnings += ";${warning}"
    }

    fun addError(error: String) {
        errors += ";${error}"
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