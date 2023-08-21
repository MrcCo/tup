package rs.ac.bg.etf.sm203134m.semantic

import com.fasterxml.jackson.databind.ObjectMapper

class TestMetadata {

    // api test requirements
    var requiresOkhttp = false
    var requiresObjectMapper = false

    var requiresSelenium = false
    var requiresSeleniumBy = false

    var browserRequirements = mutableMapOf(
        Pair("edge", true),
        Pair("chrome", false),
        Pair("safari", false),
        Pair("firefox", false)
    )

    var hasPreviousRequests = false
    var pageOpened = false
    fun formattedBrowserRequirements(): String {
        val browsers = browserRequirements.filter { it.value }.map { listOf(mapOf(Pair("type", it.key))) }
        val resource = mapOf(
            Pair(
                "browsers",
                browsers
            )
        )
        return ObjectMapper().writeValueAsString(resource)
    }
}