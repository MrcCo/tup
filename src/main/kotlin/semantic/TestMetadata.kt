package semantic

class TestMetadata {

    // okhttp requirements
    var requiresOkhttp = false
    var requiresHeaders = false

    // assertions
    var requiresAssertions = false;
    override fun toString(): String {
        return "TestMetadata(requiresOkhttp=$requiresOkhttp, requiresHeaders=$requiresHeaders, requiresAssertions=$requiresAssertions)"
    }


}