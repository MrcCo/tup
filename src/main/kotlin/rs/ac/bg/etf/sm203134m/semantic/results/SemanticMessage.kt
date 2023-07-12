package rs.ac.bg.etf.sm203134m.semantic.results

open class SemanticMessage(open val message: String)
data class SemanticWarning(override val message: String): SemanticMessage(message){}
data class SemanticError(override val message: String): SemanticMessage(message){}