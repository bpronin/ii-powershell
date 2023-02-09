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

    private Stack<Integer> stateStack = new Stack<Integer>();

    public void yypushState(int state) {
        stateStack.push(yystate());
        yybegin(state);
    }

    public void yypopState() {
        yybegin(stateStack.pop());
    }
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
WHITE_SPACE = [ \t\n\x0B\f\r]+
ANY = [^] | {NEW_LINE}
DASH = [-–—―]
BRACE = "{" | "} " | "@{"
PARENTHESIS = "(" | ")" | "$(" | "@("

/* Comments */

LINE_COMMENT = # .*
BLOCK_COMMENT = <# {ANY}* #>
SIGNATURE = "# SIG # Begin signature block" {NEW_LINE} {ANY}* "# SIG # End signature block"
REQUIRES_COMMENT = # {WHITE_SPACE}* requires {WHITE_SPACE}+ .*

/* Identifiers */
TYPE_NAME = \w+ (\w | {DASH} | "?")+
VARIABLE_NAME = (\w | {DASH} | "?")+
NAMESPACE_NAME =  (\w | {DASH} | "?")+ ":"

/* Literals */

NUMBER_SIGN = "+" | {DASH}
INTEGER_SUFFIX = (l | d)? (kb | mb | gb | tb | pb)?
DECIMAL_INTEGER_NUMBER = {NUMBER_SIGN}? \d+ {INTEGER_SUFFIX}?
HEXADECIMAL_INTEGER_NUMBER = {NUMBER_SIGN}? 0x [\dA-Fa-f]+ {INTEGER_SUFFIX}?
REAL_NUMBER = {NUMBER_SIGN}? \d*\.\d+ (e \d+ {INTEGER_SUFFIX}?)?

/* Keywords */

KEYWORD = begin|break|catch|class|continue|data|define|do|dynamicparam|else|elseif|end|exit|filter|finally|for
    |foreach|from|function|if|in|inlinescript|parallel|param|process|return|switch|throw|trap|try|until|using|var
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
ASSIGNMENT_OPERATOR = "+=" | "*=" | "/=" | "%=" | {DASH} "=" | "="
SYMBOLIC_OPERATOR = ".." | "::" | "&&" | "||" | "!" | "&" | "|" | ";" | "," | "."
FILE_REDIRECTION_OPERATOR = ">"|"<"|">>"|"2>"|"2>>"|"3>"|"3>>"|"4>"|"4>>"|"5>"|"5>>"|"6>"|"6>>"|"*>"|"*>>"
MERGING_REDIRECTION_OPERATOR = "*>&1"|"2>&1"|"3>&1"|"4>&1"|"5>&1"|"6>&1"|"*>&2"|"1>&2"|"3>&2"|"4>&2"|"5>&2"|"6>&2"
ARITHMETIC_OPERATOR = "++" | {DASH} {DASH} | "+" | "*" | "/" | "%" | {DASH}

/* Predefined params */

//FILE_PARAM = {DASH} file

/* Variables */
RESERVED_VARIABLE_NAME = "$$"|"$?"|"$^"|"$_"
SCOPE = global:|local:|private:|script:|using:|workflow:|{NAMESPACE_NAME}
VARIABLE = ("$" (SCOPE)? {VARIABLE_NAME}) | {RESERVED_VARIABLE_NAME}

%state BRACKETS

%%

<YYINITIAL> {
    {WHITE_SPACE}                        { return WHITE_SPACE; }
    "["                                  { yypushState(BRACKETS); return BRACKET; }
    {BRACE}                              { return BRACE; }
    {PARENTHESIS}                        { return PARENTHESIS; }

    {SIGNATURE}                          { return SIGNATURE; }
    {REQUIRES_COMMENT}                   { return REQUIRES_COMMENT; }
    {LINE_COMMENT}                       { return LINE_COMMENT; }
    {BLOCK_COMMENT}                      { return BLOCK_COMMENT; }

    {SHIFT_OPERATOR}                     { return SHIFT_OPERATOR; }
    {EQUALITY_OPERATOR}                  { return EQUALITY_OPERATOR; }
    {MATCHING_OPERATOR}                  { return MATCHING_OPERATOR; }
    {CONTAINMENT_OPERATOR}               { return CONTAINMENT_OPERATOR; }
    {REPLACEMENT_OPERATOR}               { return REPLACEMENT_OPERATOR; }
    {SPLIT_OPERATOR}                     { return SPLIT_OPERATOR; }
    {LOGICAL_OPERATOR}                   { return LOGICAL_OPERATOR; }
    {BITWISE_LOGICAL_OPERATOR}           { return BITWISE_LOGICAL_OPERATOR; }
    {LOGICAL_NOT_OPERATOR}               { return LOGICAL_NOT_OPERATOR; }
    {BITWISE_LOGICAL_NOT_OPERATOR}       { return BITWISE_LOGICAL_NOT_OPERATOR; }
    {JOIN_OPERATOR}                      { return JOIN_OPERATOR; }
    {CAST_OPERATOR}                      { return CAST_OPERATOR; }
    {TYPE_OPERATOR}                      { return TYPE_OPERATOR; }
    {FORMAT_OPERATOR}                    { return FORMAT_OPERATOR; }
    {ASSIGNMENT_OPERATOR}                { return ASSIGNMENT_OPERATOR; }
    {FILE_REDIRECTION_OPERATOR}          { return FILE_REDIRECTION_OPERATOR; }
    {MERGING_REDIRECTION_OPERATOR}       { return MERGING_REDIRECTION_OPERATOR; }
    {SYMBOLIC_OPERATOR}                  { return SYMBOLIC_OPERATOR; }
    {ARITHMETIC_OPERATOR}                { return ARITHMETIC_OPERATOR; }

    {KEYWORD}                            { return KEYWORD_NAME; }

    {DECIMAL_INTEGER_NUMBER}             { return DECIMAL_INTEGER_NUMBER; }
    {HEXADECIMAL_INTEGER_NUMBER}         { return HEXADECIMAL_INTEGER_NUMBER; }
    {REAL_NUMBER}                        { return REAL_NUMBER; }

    {VARIABLE}                           { return VARIABLE_NAME; }
}

<BRACKETS> {
    "]"                                  { yypopState(); return BRACKET;}
     {TYPE_NAME}                         { return TYPE_LITERAL; }
    "["                                  { yypushState(BRACKETS); return BRACKET;}
}



[^] { return BAD_CHARACTER; }
