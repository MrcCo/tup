package rs.ac.bg.etf.sm203134m.generation.generators

import org.junit.jupiter.api.Assertions
import rs.ac.bg.etf.sm203134m.generation.generators.api.assertions.generateOnEntry

object Commons {

    fun importStatement(classToImport: String): String {
        return "import ${classToImport};\n"
    }

    fun multilineComment(comment: String): String {
        return "\t/* $comment */\n"
    }

    fun fieldDefinition(fieldType: String, fieldName: String): String {
        return "\tprivate $fieldType $fieldName;\n"
    }

    fun methodAnnotation(annotationName: String): String {

        return "\t@${annotationName}\n"

    }

    fun voidMethodDefinition(methodName: String, arguments: Map<String, String>): String {
        return "\tvoid $methodName(${arguments.map { (k,v) -> "$k $v" }.joinToString()}) {\n"
    }

    fun assertEqual(expected: String, actual: String): String {
        return "\t\t${Assertions::class.simpleName}.assertEquals($expected, $actual);\n"
    }
}