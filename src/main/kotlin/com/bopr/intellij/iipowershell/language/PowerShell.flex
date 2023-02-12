package com.bopr.intellij.iipowershell.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.bopr.intellij.iipowershell.language.psi.PowerShellTypes.*;

%%

%{
    public PowerShellLexer() {
        this((java.io.Reader)null);
    }

//    private Stack<Integer> stateStack = new Stack<Integer>();
//
//    public void yypushState(int state) {
//        stateStack.push(yystate());
//        yybegin(state);
//    }
//
//    public void yypopState() {
//        yybegin(stateStack.pop());
//    }
%}

%public
%class PowerShellLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%caseless
%ignorecase

NEW_LINE = [\r\n]|\r\n
//WHITE_SPACE = [ \t\x0B\f]+
WHITE_SPACE = [\p{Zs}\p{Zl}\p{Zp}\u0009\u000B\u000C] | \`[\r\n]
//WHITE_SPACE = [ \t\n\x0B\f\r]+
ANY = [^] | {NEW_LINE}
DASH = [\-\–\—\―]
//SINGLE_QUOTE = [\'\u2019\u201A\u201B]
//DOUBLE_QUOTE = [\"\“\”\„]
BRACE = "{" | "}" | "@{"
PARENTHESIS = "(" | ")" | "$(" | "@("
LABEL = ":" \w+

/* Comments */

LINE_COMMENT = # .*
BLOCK_COMMENT = <# {ANY}* #>
SIGNATURE = "# SIG # Begin signature block" {NEW_LINE} {ANY}* "# SIG # End signature block"
REQUIRES_COMMENT = # {WHITE_SPACE}* requires {WHITE_SPACE}+ .*

/* Identifiers */

SIMPLE_IDENTIFIER = \w (\w | {DASH} | "?")*
//SIMPLE_IDENTIFIER = [\p{Alpha}\_] \w*
//GENERIC_IDENTIFIER = \w (\w | {DASH} | "?")*
COMMAND_PARAMETER = {DASH} [\p{Alpha}\_\?] \w* \:?
//NAMESPACE_NAME =  (\w | "?")+ ":"
NAMESPACE_NAME =  [\w\?]+ \:
//VARIABLE_NAME = (\w |"?")+
VARIABLE_NAME = [\w\?]+

/* Literals */

NUMBER_SIGN = "+" | {DASH}
INTEGER_SUFFIX = (l | d)? (kb | mb | gb | tb | pb)?
DECIMAL_INTEGER_NUMBER = {NUMBER_SIGN}? \d+ {INTEGER_SUFFIX}?
HEXADECIMAL_INTEGER_NUMBER = {NUMBER_SIGN}? 0x [\dA-Fa-f]+ {INTEGER_SUFFIX}?
REAL_NUMBER = {NUMBER_SIGN}? \d*\.\d+ (e \d+ {INTEGER_SUFFIX}?)?


STRING = \" [^\"]* \"
MULTILINE_STRING = \@\" {ANY}* \"\@
VERBATIM_STRING = \' [^\']* \'
VERBATIM_MULTILINE_STRING = \@\' {ANY}* \'\@

VERBATIM_COMMAND_ARGUMENT = "--%" [^\|\r\n]*
//todo: VERBATIM_COMMAND_ARGUMENT = "--%" ((\" [^\"]* \") | (\&[^\&]*) | ([^\|]))*

//VERBATIM_COMMAND_ARGUMENT_1 = \" [^\"]* \"
//VERBATIM_COMMAND_ARGUMENT_2 = (!\&\&)
//VERBATIM_COMMAND_ARGUMENT_3 = [^\|\r\n]
//VERBATIM_COMMAND_ARGUMENT = "--%" (
//    {VERBATIM_COMMAND_ARGUMENT_1}
////    |
////    {VERBATIM_COMMAND_ARGUMENT_2}
////    |
////    {VERBATIM_COMMAND_ARGUMENT_3}
//    ) {NEW_LINE}

/* Keywords */

//FUNCTION_KEYWORD = function|filter|workflow
KEYWORD = begin|break|catch|class|continue|data|define|do|dynamicparam|elseif|else|end|exit|filter|finally
    |foreach|for|from|function|if|in|inlinescript|parallel|param|process|return|switch|throw|trap|try|until|using|var
    |while|workflow

/* Operators */

EQUALITY_OPERATOR = {DASH} (ieq|ine|igt|ilt|ile|ige|ceq|cne|cgt|clt|cle|cge|eq|ne|gt|lt|le|ge)
MATCHING_OPERATOR = {DASH} (imatch|inotmatch|ilike|inotlike|cmatch|cnotmatch|clike|cnotlike|match|notmatch|like|notlike)
CONTAINMENT_OPERATOR = {DASH} (inotcontains|cnotcontains|notcontains|icontains|ccontains|contains|in|notin)
REPLACEMENT_OPERATOR = {DASH} (ireplace|creplace|replace)
SPLIT_OPERATOR = {DASH} (isplit|csplit|split)
SHIFT_OPERATOR = {DASH} (shl|shr)
LOGICAL_OPERATOR = {DASH} (and|or|xor)
BITWISE_LOGICAL_OPERATOR = {DASH} (band|bor|bxor)
TYPE_OPERATOR = {DASH} (is|isnot)
LOGICAL_NOT_OPERATOR = {DASH} not
BITWISE_LOGICAL_NOT_OPERATOR = {DASH} bnot
JOIN_OPERATOR = {DASH} join
CAST_OPERATOR = {DASH} as
FORMAT_OPERATOR = {DASH} f
INCREMENT_OPERATOR = "++"
DECREMENT_OPERATOR = {DASH} {DASH}
SYMBOLIC_OPERATOR = "??=" | "??" | "?." | "?[]" | ".." | "::" | "&&" | "||" | "!" | "&" | "|"
    | "," | "." | "+" | "*" | "/" | "%" | {DASH}
COMPARISON_OPERATOR={EQUALITY_OPERATOR}|{MATCHING_OPERATOR}|{CONTAINMENT_OPERATOR}|{REPLACEMENT_OPERATOR}
    |{SPLIT_OPERATOR}|{LOGICAL_OPERATOR}|{BITWISE_LOGICAL_OPERATOR}|{TYPE_OPERATOR}|{LOGICAL_NOT_OPERATOR}
    |{BITWISE_LOGICAL_NOT_OPERATOR}|{JOIN_OPERATOR}|{CAST_OPERATOR}|{SHIFT_OPERATOR}
ASSIGNMENT_OPERATOR = "+=" | "*=" | "/=" | "%=" | {DASH} "=" | "="
FILE_REDIRECTION_OPERATOR = ">"|"<"|">>"|"2>"|"2>>"|"3>"|"3>>"|"4>"|"4>>"|"5>"|"5>>"|"6>"|"6>>"|"*>"|"*>>"
MERGING_REDIRECTION_OPERATOR = "*>&1"|"2>&1"|"3>&1"|"4>&1"|"5>&1"|"6>&1"|"*>&2"|"1>&2"|"3>&2"|"4>&2"|"5>&2"|"6>&2"

/* Predefined statements params and arguments*/

SWITCH_PARAMETER = {DASH} (regex|wildcard|exact|casesensitive|parallel)
FILE_PARAMETER = {DASH} file
SUPPORTED_COMMAND_PARAMETER = {DASH} supportedcommand
DEFAULT_CLAUSE = default

/* Variables */

RESERVED_VARIABLE_NAME = "$$"|"$?"|"$^"|"$_"
VARIABLE_SCOPE = global:|local:|private:|script:|using:|workflow:|{NAMESPACE_NAME}
REGULAR_VARIABLE = (("$"|"@") {VARIABLE_SCOPE}? {VARIABLE_NAME}) | {RESERVED_VARIABLE_NAME}
BRACED_VARIABLE = "${" {VARIABLE_SCOPE}? [^}]+ [^`] "}"

%state IN_BRACKETS
//%state IN_DOUBLE_QUOTES
//%state AFTER_FUNCTION_KEYWORD

%%

<YYINITIAL> {
    {WHITE_SPACE}                        { return WHITE_SPACE; }
    {NEW_LINE}                           { return NEW_LINE; }
    ";"                                  { return SEMICOLON; }
    {DASH}                               { return DASH; }
    {BRACE}                              { return BRACE; }
    {PARENTHESIS}                        { return PARENTHESIS; }
    "["                                  { yybegin(IN_BRACKETS); return BRACKET; }
    "]"                                  { return BRACKET; }

//    {DOUBLE_QUOTE}                       { yybegin(IN_DOUBLE_QUOTES); return DOUBLE_QUOTE; }
    {STRING}                             { return STRING; }
    {MULTILINE_STRING}                   { return MULTILINE_STRING; }
    {VERBATIM_STRING}                    { return VERBATIM_STRING; }
    {VERBATIM_MULTILINE_STRING}          { return VERBATIM_MULTILINE_STRING; }

    {SIGNATURE}                          { return SIGNATURE; }
    {REQUIRES_COMMENT}                   { return REQUIRES_COMMENT; }
    {LINE_COMMENT}                       { return LINE_COMMENT; }
    {BLOCK_COMMENT}                      { return BLOCK_COMMENT; }

    {ASSIGNMENT_OPERATOR}                { return ASSIGNMENT_OPERATOR; }
    {VERBATIM_COMMAND_ARGUMENT}          { return VERBATIM_COMMAND_ARGUMENT; }
    {INCREMENT_OPERATOR}                 { return INCREMENT_OPERATOR; }
    {DECREMENT_OPERATOR}                 { return DECREMENT_OPERATOR; }
    {SYMBOLIC_OPERATOR}                  { return SYMBOLIC_OPERATOR; }
    {COMPARISON_OPERATOR}                { return COMPARISON_OPERATOR; }
    {FORMAT_OPERATOR}                    { return FORMAT_OPERATOR; }
    {FILE_REDIRECTION_OPERATOR}          { return FILE_REDIRECTION_OPERATOR; }
    {MERGING_REDIRECTION_OPERATOR}       { return MERGING_REDIRECTION_OPERATOR; }

//    {FUNCTION_KEYWORD}                   { yybegin(AFTER_FUNCTION_KEYWORD); return KEYWORD; }
    {KEYWORD}                            { return KEYWORD; }
    {LABEL}                              { return LABEL; }

    {DECIMAL_INTEGER_NUMBER}             { return DECIMAL_INTEGER_NUMBER; }
    {HEXADECIMAL_INTEGER_NUMBER}         { return HEXADECIMAL_INTEGER_NUMBER; }
    {REAL_NUMBER}                        { return REAL_NUMBER; }

    {REGULAR_VARIABLE}                   { return REGULAR_VARIABLE; }
    {BRACED_VARIABLE}                    { return BRACED_VARIABLE; }

    {SWITCH_PARAMETER}                   { return SWITCH_PARAMETER; } //todo: move to IN_SWITCH state ?
    {FILE_PARAMETER}                     { return FILE_PARAMETER; }  //todo: move to IN_SWITCH state ?
    {SUPPORTED_COMMAND_PARAMETER}        { return SUPPORTED_COMMAND_PARAMETER; }  //todo: move to IN_DATA state ?
    {COMMAND_PARAMETER}                  { return COMMAND_PARAMETER; }

    {SIMPLE_IDENTIFIER}                  { return SIMPLE_IDENTIFIER; }
//    {GENERIC_IDENTIFIER}                 { return GENERIC_IDENTIFIER; }
}

<IN_BRACKETS> {
    "["                                  { yypushback(1); yybegin(YYINITIAL); }
    "]"                                  { yypushback(1); yybegin(YYINITIAL); }
//    {SIMPLE_IDENTIFIER} "("              { yypushback(1); yybegin(YYINITIAL); return ATTRIBUTE_IDENTIFIER; }
//    {SIMPLE_IDENTIFIER}                  { return TYPE_IDENTIFIER; }
    {SIMPLE_IDENTIFIER}                  { return SIMPLE_IDENTIFIER; }
    "("                                  { yypushback(1); yybegin(YYINITIAL);}
    "."                                  { return SYMBOLIC_OPERATOR; }
    ","                                  { return SYMBOLIC_OPERATOR; }
}

//<IN_DOUBLE_QUOTES> {
//    {DOUBLE_QUOTE}                       { yybegin(YYINITIAL); return STRING_LITERAL;}
//    {REGULAR_VARIABLE}                   { return REGULAR_VARIABLE;}
//    {BRACED_VARIABLE}                    { return BRACED_VARIABLE;}
//}

//<AFTER_FUNCTION_KEYWORD> {
//    {WHITE_SPACE}                        { return WHITE_SPACE; }
//    {GENERIC_TOKEN}                      { yybegin(YYINITIAL); return FUNCTION_NAME; }
//}

[^]                                      { return BAD_CHARACTER; }
