package com.bopr.intellij.iipowershell.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.bopr.intellij.iipowershell.language.psi.PowerShellTypes.*;

%%

%{
    public PowerShellLexer() {
        this((java.io.Reader)null);
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
BRACKET = "[" | "]"
PARENTHESIS = "(" | ")" | "$(" | "@("

/* Comments */

LINE_COMMENT = # .*
BLOCK_COMMENT = <# {ANY}* #>
SIGNATURE = "# SIG # Begin signature block" {NEW_LINE} {ANY}* "# SIG # End signature block"
REQUIRES_COMMENT = # {WHITE_SPACE}* requires {WHITE_SPACE}+ .*

/* Literals */

NUMBER_SIGN = "+" | {DASH}
INTEGER_SUFFIX = (l | d)? (kb | mb | gb | tb | pb)?
DECIMAL_INTEGER_NUMBER = {NUMBER_SIGN}? \d+ {INTEGER_SUFFIX}?
HEXADECIMAL_INTEGER_NUMBER = {NUMBER_SIGN}? 0x [\dA-Fa-f]+ {INTEGER_SUFFIX}?
REAL_NUMBER = {NUMBER_SIGN}? \d*\.\d+ (e \d+ {INTEGER_SUFFIX}?)?

/* Keywords */

KEYWORD_NAME = begin|break|catch|class|continue|data|define|do|dynamicparam|else|elseif|end|exit|filter|finally|for
    |foreach|from|function|if|in|inlinescript|parallel|param|process|return|switch|throw|trap|try|until|using|var
    |while|workflow

/* Operators */
ASSIGNMENT_OPERATOR = "+=" | "*=" | "/=" | "%=" | {DASH} "=" | "="
SYMBOLIC_OPERATOR = ".." | "::" | "&&" | "||" | "!" | "&" | "|" | ";" | "," | "."
FILE_REDIRECTION_OPERATOR = ">"|"<"|">>"|"2>"|"2>>"|"3>"|"3>>"|"4>"|"4>>"|"5>"|"5>>"|"6>"|"6>>"|"*>"|"*>>"
MERGING_REDIRECTION_OPERATOR = "*>&1"|"2>&1"|"3>&1"|"4>&1"|"5>&1"|"6>&1"|"*>&2"|"1>&2"|"3>&2"|"4>&2"|"5>&2"|"6>&2"
ARITHMETIC_OPERATOR = "++" | {DASH} {DASH} | "+" | "*" | "/" | "%" | {DASH}

/* Variables */
RESERVED_VARIABLE = "$$"|"$?"|"$^"|"$_"

%%
<YYINITIAL> {
    {WHITE_SPACE}                        { return WHITE_SPACE; }
    {BRACE}                              { return BRACE; }
    {BRACKET}                            { return BRACKET; }
    {PARENTHESIS}                        { return PARENTHESIS; }

    {SIGNATURE}                          { return SIGNATURE; }
    {REQUIRES_COMMENT}                   { return REQUIRES_COMMENT; }
    {LINE_COMMENT}                       { return LINE_COMMENT; }
    {BLOCK_COMMENT}                      { return BLOCK_COMMENT; }

    {DECIMAL_INTEGER_NUMBER}             { return DECIMAL_INTEGER_NUMBER; }
    {HEXADECIMAL_INTEGER_NUMBER}         { return HEXADECIMAL_INTEGER_NUMBER; }
    {REAL_NUMBER}                        { return REAL_NUMBER; }

    {KEYWORD_NAME}                       { return KEYWORD_NAME; }

    {ASSIGNMENT_OPERATOR}                { return ASSIGNMENT_OPERATOR; }
    {FILE_REDIRECTION_OPERATOR}          { return FILE_REDIRECTION_OPERATOR; }
    {MERGING_REDIRECTION_OPERATOR}       { return MERGING_REDIRECTION_OPERATOR; }
    {SYMBOLIC_OPERATOR}                  { return SYMBOLIC_OPERATOR; }
    {ARITHMETIC_OPERATOR}                { return ARITHMETIC_OPERATOR; }

}

[^] { return BAD_CHARACTER; }
