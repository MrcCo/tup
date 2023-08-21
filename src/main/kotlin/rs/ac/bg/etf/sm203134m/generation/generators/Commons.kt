package rs.ac.bg.etf.sm203134m.generation.generators

import org.apache.commons.text.StringEscapeUtils
import org.junit.jupiter.api.Assertions

object Commons {

    fun importStatement(classToImport: String): String {
        return "import ${classToImport};\n"
    }

    fun multilineComment(comment: String): String {
        return "\t/* $comment */\n"
    }


    fun staticFieldDefinition(fieldType: String, fieldName: String): String {
        return "\tstatic $fieldType $fieldName;\n"
    }

    fun staticFieldInitialization(fieldType: String, fieldName: String, initialValue: String): String {
        return "\tstatic $fieldType $fieldName = $initialValue;\n"
    }


    fun unfinishedVariableAssignment(variableName: String): String {
        return "\t\tvar $variableName = "
    }

    fun variableAssignment(variableName: String, variableValue: String): String {
        return "\t\tvar $variableName = $variableValue;\n"
    }


    fun annotation(annotationName: String): String {
        return "\t@${annotationName}\n"
    }

    fun staticVoidMethodDefinition(methodName: String, arguments: Map<String, String>): String {
        return "\tstatic void $methodName(${arguments.map { (k,v) -> "$k $v" }.joinToString()}) {\n"
    }

    fun staticVoidMethodDefinition(methodName: String, arguments: Map<String, String>, exception:String?): String {
        return "\tvoid $methodName(${arguments.map { (k,v) -> "$k $v" }.joinToString()})" + if(exception != null) " throws $exception" else { "" } + " {\n"
    }

    fun requestBuilderURL(url: String): String {
        return "\t\t\t.url($url)\n"
    }

    fun requestBuilderHeaders(headers: String): String {
        return "\t\t\t.headers(Headers.of($headers))\n"
    }

    fun requestBuilderMethod(methodName: String, body: String): String {
        return "\t\t\t.$methodName($body)\n"
    }

    fun requestBuilderBuild(): String {
        return "\t\t\t.build();\n"
    }


    fun jsonRequestBody(requestBody: String): String {
        return if(requestBody.isNotEmpty()) {
            val mediaType = "MediaType.parse(\"application/json\")"
            "RequestBody.create($requestBody, $mediaType)"
        } else {
            ""
        }
    }

    fun assertEqual(expected: String, actual: String): String {
        return "\t\t${Assertions::class.simpleName}.assertEquals($expected, $actual);\n"
    }

    fun closeBraces(): String {
        return "\t}"
    }

    fun openBracesInLine(): String {
        return "{\n\n"
    }

    fun escapeSingleQuoteString(singleQuotedString: String): String {
        return StringEscapeUtils.escapeJava(singleQuotedString)
            .replace("'", "\"")
            .replace(" ", "")
    }
}