lexer grammar TupLexer;

// literals
TEST: T E S T;
NAME: N A M E;
DESCRIPTION: D E S C R I P T I O N;
TYPE: T Y P E;
STEPS: S T E P S;
EXECUTE: E X E C U T E;
AN: A N;
API: A P I;
REQUEST: R E Q U E S T;
TO: T O;
WITH: W I T H;
HEADERS: H E A D E R S;
BODY: B O D Y;
VALIDATE: V A L I D A T E;
THAT: T H A T;
RESPONSE: R E S P O N S E;
HAS: H A S;
STATUS: S T A T U S;
CODE: C O D E;
IS: I S;
FIELD: F I E L D;
VALUE: V A L U E;

// general
INTEGER: [0-9]+ ;
// todo separate two types of strings
STRING: '"' ( ~["\\] | '\\' . )* '"' | '\'' ( ~['\\] | '\\' . )* '\'';
IDENTIFIER: [a-zA-Z_]+;


LEFT_SQUARE_BRACKET: '[';
RIGHT_SQUARE_BRACKET: ']';
LEFT_CURLY_BRACKET: '{';
RIGHT_CURLY_BRACKET: '}';
COLON: ':';
COMMA: ',';
DOT: '.';

// case insensitivity
fragment A : [aA]; // match either an 'a' or 'A'
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];

// ignore whitespace
WS: [ \t\r\n]+ -> skip ;
