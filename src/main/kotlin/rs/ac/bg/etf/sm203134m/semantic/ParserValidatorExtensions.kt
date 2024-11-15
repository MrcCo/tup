package rs.ac.bg.etf.sm203134m.semantic

import org.apache.commons.validator.routines.UrlValidator
import rs.ac.bg.etf.sm203134m.antlr4.TupParser
import rs.ac.bg.etf.sm203134m.semantic.results.SemanticError
import rs.ac.bg.etf.sm203134m.semantic.results.SemanticWarning

// constants
val HTTP_METHODS = listOf("GET", "POST", "PATCH", "PUT", "DELETE")
val HTTP_METHODS_SUPPORTING_REQUEST_BODY = listOf("POST", "PATCH", "PUT")
val RESERVED_STATUS_CODES = listOf(
    (100..103) +
            (200..208) + 228 +
            (300..308) +
            (400..418) + (421..426) + 428 + 429 + 431 + 451 +
            (500..508) + 510 + 511
).flatten()
val SUPPORTED_BROWSERS = listOf(
    "chrome", "edge", "safari", "firefox"
)

// response
data class ValidationResponse(val isValid: Boolean, val warning: SemanticWarning?, val error: SemanticError?) {
    constructor() : this(true, null,  null)
    constructor(warning: SemanticWarning?): this(true, warning, null)
    constructor(error: SemanticError?): this(false, null, error)
    constructor(warning: SemanticWarning?, error: SemanticError?): this(false, warning, error)
}

fun TupParser.HttpMethodContext.validate(): ValidationResponse {
    val method = this.IDENTIFIER().toString()
    if (HTTP_METHODS.contains(method.uppercase())) {
        return ValidationResponse()
    }

    return ValidationResponse(SemanticError("HTTP method $method is not supported, try one of these $HTTP_METHODS"))
}

fun TupParser.RequestContext.validate(): ValidationResponse {
    val url = this.STRING().toString().substring(1, this.STRING().toString().length - 1)
    return if (UrlValidator.getInstance().isValid(url)) {
        ValidationResponse()
    } else {
        ValidationResponse(SemanticWarning("$url is not a valid url"))
    }
}

fun TupParser.HeaderPairContext.validate(): ValidationResponse {
    val headerKey = this.STRING(0).toString()
    val headerValue = this.STRING(1).toString()
    return if (headerKey.isNotEmpty()) {
        ValidationResponse()
    } else {
        if (headerValue.isEmpty()) {
            ValidationResponse(SemanticWarning("Your header key is empty"))
        } else {
            ValidationResponse(SemanticError("Your header key is empty, while there is a value"))
        }
    }
}

fun TupParser.RequestBodyContext.validate(): ValidationResponse {

    val request = this.getParent() as? TupParser.ExecuteApiRequestContext
    val requestMethod = request?.request()?.httpMethod()?.IDENTIFIER()
        ?: return ValidationResponse(SemanticWarning("GET request does not support a request body"))

    val requestMethodSupportsRequestBody = HTTP_METHODS_SUPPORTING_REQUEST_BODY.contains(requestMethod.toString())
    return if (requestMethodSupportsRequestBody) {
        ValidationResponse()
    } else {
        ValidationResponse(SemanticWarning("${requestMethod.toString().uppercase()} does not support a request body"))
    }
}

fun TupParser.AssertResponseCodeContext.validate(): ValidationResponse {
    val statusCode = this.INTEGER().toString().toInt()

    return if (statusCode < 100 || statusCode > 599) {
        ValidationResponse(SemanticWarning("Status codes are generally between 100 and 599"))
    } else if (!RESERVED_STATUS_CODES.contains(statusCode)) {
        ValidationResponse(SemanticWarning("$statusCode is not a reserved status code"))
    } else {
        ValidationResponse()
    }
}

fun TupParser.BrowserListContext.validate(): ValidationResponse {

    val browsers = this.IDENTIFIER().map { it.text.lowercase() }.toList()
    val mutableBrowserSet = browsers.toMutableSet()
    var duplicateBrowsers: SemanticWarning? = null
    if(browsers.size != mutableBrowserSet.size) {
        duplicateBrowsers = SemanticWarning("Duplicate browsers in the list")
    }

    mutableBrowserSet.removeAll(SUPPORTED_BROWSERS)
    var unsupportedBrowser: SemanticError? = null
    if(mutableBrowserSet.isNotEmpty()) {
        unsupportedBrowser = SemanticError("Browsers $mutableBrowserSet are not supported. Try with one of these $SUPPORTED_BROWSERS")
    }

    return if(duplicateBrowsers == null && unsupportedBrowser == null) {
        ValidationResponse();
    } else if(duplicateBrowsers == null) {
        ValidationResponse(unsupportedBrowser)
    } else if(unsupportedBrowser == null) {
        ValidationResponse(duplicateBrowsers)
    } else {
        ValidationResponse(duplicateBrowsers, unsupportedBrowser)
    }
}

fun TupParser.BrowserDefinitionContext.validate(): ValidationResponse {
    val steps = parent.getChild(parent.childCount - 1)
    var browserNeeded = false
    for(i in 0 until steps.childCount) {
        if(steps.getChild(i).getChild(0) is TupParser.OpenWebPageContext) {
            browserNeeded = true
            break
        }
    }

    if(!browserNeeded) {
        return ValidationResponse(SemanticWarning("You do not need to define browsers, since there are no web pages opened in the test"))
    }

    return ValidationResponse()
}


