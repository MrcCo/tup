package rs.ac.bg.etf.sm203134m.semantic

class SemanticAnalysisResults {

    private var isCorrect = true
    private var warnings = ""
    private var errors = ""


    private fun incorrect() {
        isCorrect = false
    }

    private fun addWarning(warning: String) {
        warnings += ";${warning}"
    }

    private fun addError(error: String) {
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