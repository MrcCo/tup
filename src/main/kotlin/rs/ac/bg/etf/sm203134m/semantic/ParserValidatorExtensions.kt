package rs.ac.bg.etf.sm203134m.semantic

import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import org.apache.commons.validator.routines.UrlValidator

// constants
val AVAILABLE_TEST_TYPES = listOf("SELENIUM", "REST API")
val HTTP_METHODS = listOf("GET", "POST", "PATCH", "PUT", "DELETE")
val HTTP_METHODS_SUPPORTING_REQUEST_BODY = listOf("POST", "PATCH", "PUT")
val RESERVED_STATUS_CODES = listOf(
    (100..103) +
            (200..208) + 228 +
            (300..308) +
            (400..418) + (421..426) + 428 + 429 + 431 + 451 +
            (500..508) + 510 + 511
).flatten()

// response
data class ValidationResponse(val isValid: Boolean, val warning: String, val error: String) {
    constructor(isValid: Boolean) : this(isValid, "", "")
}


fun TupParser.TestTypeContext.validate(): ValidationResponse {

    val type = STRING().toString().replace("\"","")

    if (AVAILABLE_TEST_TYPES.contains(type.uppercase())) {
        return ValidationResponse(true)
    }

    return ValidationResponse(false, "", "Test type $type is not available, try Selenium or Rest Api")

}

fun TupParser.HttpMethodContext.validate(): ValidationResponse {
    val method = this.IDENTIFIER().toString()
    if (HTTP_METHODS.contains(method.uppercase())) {
        return ValidationResponse(true)
    }

    return ValidationResponse(false, "", "HTTP method $method is not supported, try one of these $HTTP_METHODS")
}

fun TupParser.RequestContext.validate(): ValidationResponse {
    val url = this.STRING().toString().substring(1, this.STRING().toString().length - 1)
    return if (UrlValidator.getInstance().isValid(url)) {
        ValidationResponse(true)
    } else {
        ValidationResponse(true, "$url is not a valid url", "")
    }
}

fun TupParser.HeaderPairContext.validate(): ValidationResponse {
    val headerKey = this.STRING(0).toString()
    val headerValue = this.STRING(1).toString()
    return if (headerKey.isNotEmpty()) {
        ValidationResponse(true)
    } else {
        if (headerValue.isEmpty()) {
            ValidationResponse(true, "Your header key is empty", "")
        } else {
            ValidationResponse(false, "", "Your header key is empty, while there is a value")
        }
    }
}

fun TupParser.RequestBodyContext.validate(): ValidationResponse {

    val request = this.getParent() as? TupParser.RequestStepContext
    val requestMethod = request?.request()?.httpMethod()?.IDENTIFIER()
        ?: return ValidationResponse(true, "GET request does not support a request body", "")

    val requestMethodSupportsRequestBody = HTTP_METHODS_SUPPORTING_REQUEST_BODY.contains(requestMethod.toString())
    return if (requestMethodSupportsRequestBody) {
        ValidationResponse(true)
    } else {
        ValidationResponse(true, "${requestMethod.toString().uppercase()} does not support a request body", "")
    }
}

fun TupParser.ResponseCodeValidationStepContext.validate(): ValidationResponse {
    val statusCode = this.statusCode().INTEGER().toString().toInt()

    return if (statusCode < 100 || statusCode > 599) {
        ValidationResponse(true, "Status codes are generally between 100 and 599", "")
    } else if (!RESERVED_STATUS_CODES.contains(statusCode)) {
        ValidationResponse(true, "$statusCode is not a reserved status code", "")
    } else {
        ValidationResponse(true)
    }
}
