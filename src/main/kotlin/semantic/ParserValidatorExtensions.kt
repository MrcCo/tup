package semantic

import java.net.http.HttpRequest

// constants
val AVAILABLE_TEST_TYPES = listOf("SELENIUM", "REST API")
val HTTP_METHODS = listOf("GET", "POST", "PATCH", "PUT", "DELETE")

// response
data class ValidationResponse(val isValid: Boolean, val warning: String, val error: String) {
    constructor(isValid: Boolean) : this(isValid, "", "")
}


fun TupParser.TestTypeContext.validate(): ValidationResponse {

    val type = STRING().toString();

    if(AVAILABLE_TEST_TYPES.contains(type.uppercase())) {
        return ValidationResponse(true)
    }

    return ValidationResponse(false, "", "Test type $type is not available, try Selenium or Rest Api")

}

fun TupParser.HttpMethodContext.validate(): ValidationResponse {
    val method = this.IDENTIFIER().toString()
    if(HTTP_METHODS.contains(method.uppercase())) {
        return ValidationResponse(true)
    }

    return ValidationResponse(false, "", "HTTP method $method is not supported, try one of these $HTTP_METHODS")
}