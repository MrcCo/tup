parser grammar TupParser;

options { tokenVocab=TupLexer; }

test:
    testName
    testDescription
    testType
    testSteps
;


// TEST: name.
testName: TEST? NAME COLON IDENTIFIER DOT;

// DESCRIPTION: "Description".
testDescription: TEST? DESCRIPTION COLON STRING DOT;

// TEST TYPE: REST API | SELENIUM.
testType: TEST? TYPE COLON STRING DOT;

// TEST STEPS: step...
testSteps: testStepsHeader step+ ;
testStepsHeader: TEST? STEPS COLON;

step:
    requestStep
    | assertResponseCode
    | assertResponseBody
    | assertResponseBodyContainsField
;

// Execute a REST API request to ${URL} (with headers ${headers}) ? (with body ${body})? .
requestStep: EXECUTE AN API REQUEST TO COLON request requestHeaders? requestBody? DOT ;
request: httpMethod? STRING;
httpMethod: IDENTIFIER;
requestHeaders: WITH HEADERS LEFT_SQUARE_BRACKET headerPair (COMMA headerPair)* RIGHT_SQUARE_BRACKET ;
headerPair: LEFT_CURLY_BRACKET STRING COLON STRING RIGHT_CURLY_BRACKET ;
requestBody: WITH BODY STRING;

// Assert that last response has status: ${status}.
assertResponseCode: ASSERT THAT LAST RESPONSE HAS STATUS CODE COLON INTEGER DOT ;

// Assert that last response body is: "...".
assertResponseBody: ASSERT THAT LAST RESPONSE BODY IS COLON STRING DOT ;

// Assert that last response body has field: "${name}" with value: "${value}".
assertResponseBodyContainsField: ASSERT THAT LAST RESPONSE BODY HAS FIELD COLON STRING WITH VALUE COLON STRING DOT;