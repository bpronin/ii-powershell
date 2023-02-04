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
%class _PowerShellLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%caseless
%ignorecase

EOL=\R
WHITE_SPACE=\s+

BLANK_SPACE=[ \t\n\x0B\f\r]+
SIGNATURE=# SIG # Begin signature block\n(#(.|\n)*)+# SIG # End signature block
REQUIRES_COMMENT=#[ \t\n\x0B\f\r]*[rR]equires[ \t\n\x0B\f\r]+.*
LINE_COMMENT=#.*
BLOCK_COMMENT=<#(.|\n)*#>
REAL_NUMBER=[+-\u2013\u2014\u2015]?[0-9]*\.[0-9]+([eE][0-9]+[lLdD]?(([kK]|[mM]|[gG]|[tT]|[pP])[bB])?)?
DECIMAL_INTEGER_NUMBER=[+\-\u2013\u2014\u2015]?[0-9]+[lLdD]?(([kK]|[mM]|[gG]|[tT]|[pP])[bB])?
HEXADECIMAL_INTEGER_NUMBER=[+-\u2013\u2014\u2015]?0x[\da-fA-F]+[lLdD]?(([kK]|[mM]|[gG]|[tT]|[pP])[bB])?
KEYWORD=begin|break|catch|class|continue|data|define|do|dynamicparam|else|elseif|end|exit|filter|finally|for|foreach|from|function|if|in|inlinescript|parallel|param|process|return|switch|throw|trap|try|until|using|var|while|workflow
RESERVED_VARIABLE_NAME=\$\$|\$\?|\$\^|\$_
VARIABLE_NAME=[$@][\p{Lu}\p{Ll}\p{Lt}\p{Lm}\p{Lo}\p{Nd}_?:]+
BRACED_VARIABLE_NAME=\$\{[^}]+}
STRING=[\"\u201C\u201D\u201E]([^$`\"\u201C\u201D\u201E]|\"\")*\$?[\"\u201C\u201D\u201E]
ASSIGNMENT_OPERATORS=[+*/%\-\u2013\u2014\u2015]?=
FILE_REDIRECTION_OPERATORS=[><]|(>>)|(2>)|(2>>)|(3>)|(3>>)|(4>)|(4>>)|(5>)|(5>>)|(6>)|(6>>)|(\*>)|(\*>>)
MERGING_REDIRECTION_OPERATORS=(\*>&1)|(2>&1)|(3>&1)|(4>&1)|(5>&1)|(6>&1)|(\*>&2)|(1>&2)|(3>&2)|(4>&2)|(5>&2)|(6>&2)
FORMAT_OPERATOR=[-\u2013\u2014\u2015][fF]
OPERATORS_1=[-\u2013\u2014\u2015]shl|shr|in|notin|isnot|is|as
OPERATORS_2=[-\u2013\u2014\u2015][ic]?(eq|ne|gt|lt|le|ge|match|notmatch|replace|like|notlike|contains|notcontains|split|join)
OPERATORS_3=(?i)[-\u2013\u2014\u2015]b?(and|or|xor|not)
BRACES=[{}]
BRACKETS=[\[\]]]
PARENTHESES=[()]

%%
<YYINITIAL> {
  {WHITE_SPACE}                        { return WHITE_SPACE; }

  "."                                  { return DOT; }
  ","                                  { return COMMA; }
  ";"                                  { return SEMICOLON; }

  {BLANK_SPACE}                        { return BLANK_SPACE; }
  {SIGNATURE}                          { return SIGNATURE; }
  {REQUIRES_COMMENT}                   { return REQUIRES_COMMENT; }
  {LINE_COMMENT}                       { return LINE_COMMENT; }
  {BLOCK_COMMENT}                      { return BLOCK_COMMENT; }
  {REAL_NUMBER}                        { return REAL_NUMBER; }
  {DECIMAL_INTEGER_NUMBER}             { return DECIMAL_INTEGER_NUMBER; }
  {HEXADECIMAL_INTEGER_NUMBER}         { return HEXADECIMAL_INTEGER_NUMBER; }
  {KEYWORD}                            { return KEYWORD; }
  {RESERVED_VARIABLE_NAME}             { return RESERVED_VARIABLE_NAME; }
  {VARIABLE_NAME}                      { return VARIABLE_NAME; }
  {BRACED_VARIABLE_NAME}               { return BRACED_VARIABLE_NAME; }
  {STRING}                             { return STRING; }
  {ASSIGNMENT_OPERATORS}               { return ASSIGNMENT_OPERATORS; }
  {FILE_REDIRECTION_OPERATORS}         { return FILE_REDIRECTION_OPERATORS; }
  {MERGING_REDIRECTION_OPERATORS}      { return MERGING_REDIRECTION_OPERATORS; }
  {FORMAT_OPERATOR}                    { return FORMAT_OPERATOR; }
  {OPERATORS_1}                        { return OPERATORS_1; }
  {OPERATORS_2}                        { return OPERATORS_2; }
  {OPERATORS_3}                        { return OPERATORS_3; }
  {BRACES}                             { return BRACES; }
  {BRACKETS}                           { return BRACKETS; }
  {PARENTHESES}                        { return PARENTHESES; }

}

[^] { return BAD_CHARACTER; }
