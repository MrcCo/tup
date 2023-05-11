parser grammar TupParser;

options { tokenVocab=TupLexer; }

test:
    testMetaData
    testSteps
;

testMetaData:
    testName
    testDescription
    testType
;

// TEST: name.
testName: testNameHeader IDENTIFIER DOT;
testNameHeader: TEST? NAME COLON;

// DESCRIPTION: "Description".
testDescription: testDescriptionHeader STRING DOT;
testDescriptionHeader: TEST? DESCRIPTION COLON;

// TEST TYPE: REST API | SELENIUM.
testType: testTypeHeader STRING DOT;
testTypeHeader: TEST? TYPE COLON;

// TEST STEPS: step...
testSteps: testStepsHeader step+ ;
testStepsHeader: TEST? STEPS COLON;

step:
    requestStep
    | responseCodeValidationStep
    | responseBodyIsStep
    | responseBodyContainsFieldStep
;

// Execute a REST API request to ${URL} (with headers ${headers}) ? (with body ${body})? .
requestStep: executeRequest request requestHeaders? requestBody? DOT ;
executeRequest: EXECUTE AN API REQUEST TO COLON;
request: httpMethod? STRING;
httpMethod: IDENTIFIER;
requestHeaders: WITH HEADERS LEFT_SQUARE_BRACKET headerPair (COMMA headerPair)* RIGHT_SQUARE_BRACKET ;
headerPair: LEFT_CURLY_BRACKET STRING COLON STRING RIGHT_CURLY_BRACKET ;
requestBody: WITH BODY STRING;

// Validate that the status code of the last request is ${status_code}.
responseCodeValidationStep: statusCodeValidation statusCode DOT ;
statusCodeValidation: VALIDATE THAT RESPONSE HAS STATUS CODE COLON;
statusCode: INTEGER;

// Validate that response body is " ... " .
responseBodyIsStep: validateThatresponseBodyIs STRING DOT ;
validateThatresponseBodyIs: VALIDATE THAT RESPONSE BODY IS COLON;

// Validate that resoibse body contains field ${name} with value ${value}.
responseBodyContainsFieldStep: validateThatResponseBodyHasField STRING withValue STRING DOT;
validateThatResponseBodyHasField: VALIDATE THAT RESPONSE BODY HAS FIELD COLON;
withValue: WITH VALUE COLON;
