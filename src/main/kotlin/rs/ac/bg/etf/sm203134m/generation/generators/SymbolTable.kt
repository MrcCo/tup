package rs.ac.bg.etf.sm203134m.generation.generators

class SymbolTable {

    var testClassName = ""

    val client = "client"
    val driver = "driver"

    val requests = mutableListOf<String>()
    val responses = mutableListOf<String>()
    val responseCodes = mutableListOf<String>()
    val responseBodies = mutableListOf<String>()
    val responseFields = mutableListOf<String>()

    fun createNextRequest(): String {
        val request = "request${requests.size}"
        requests.add(request)
        return request
    }

    fun createNextResponse(): String {
        val response = "response${responses.size}"
        responses.add(response)
        return response
    }

    fun createNextResponseField(): String {
        val responseField = "responseField${responseFields.size}"
        responseFields.add(responseField)
        return responseField
    }

    fun createNextResponseCode(): String {
        val responseCode = "responseCode${responseCodes.size}"
        responseCodes.add(responseCode)
        return responseCode
    }

    fun createNextResponseBody(): String {
        val responseBody = "responseBody${responseBodies.size}"
        responseBodies.add(responseBody)
        return responseBody
    }

}