package rs.ac.bg.etf.sm203134m.generation.generators

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.semantic.TestMetadata
import java.io.IOException

fun TupParser.TestContext.generateOnEntry(metadata: TestMetadata, symbolTable: SymbolTable): String {
    val imports = generateImportsFromMetadata(metadata)
    val className = this.testName().IDENTIFIER().toString()
    symbolTable.testClassName = className
    return "$imports \n\npublic class $className {\n\n"
}

fun TupParser.TestContext.generateOnExit(): String {
    return "\n}"
}

fun generateImportsFromMetadata(metadata: TestMetadata): String {
    var imports = """
        import ${Test::class.qualifiedName!!};
        import ${BeforeEach::class.qualifiedName!!};
        import ${Assertions::class.qualifiedName};
        """.trimIndent()

    imports += "\n"

    if(metadata.requiresObjectMapper) {
        imports += "\nimport ${ObjectMapper::class.qualifiedName};\n"
    }

    imports += "\n"

    if(metadata.requiresOkhttp) {
        imports += """
            import ${Headers::class.qualifiedName!!};
            import ${MediaType::class.qualifiedName!!};
            import ${OkHttpClient::class.qualifiedName!!};
            import ${Request::class.qualifiedName!!};
            import ${RequestBody::class.qualifiedName!!};
            
            import ${IOException::class.qualifiedName!!};
        """.trimIndent()
        imports += "\n\n"
    }

    return imports
}
