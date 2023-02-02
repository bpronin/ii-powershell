package com.bopr.intellij.iipowershell.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.bopr.intellij.iipowershell.language.psi.PowerShellTypes;
import static com.bopr.intellij.iipowershell.language.psi.PowerShellTypes.*;

%%

%class PowerShellLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

LINE_TERMINATOR = [\r\n] | \r\n
INPUT_CHARACTER = [^\r\n]
WHITE_SPACE = [\p{Zs}\p{Zl}\p{Zp}\t\f\u000B] | [`]?{LINE_TERMINATOR}
DASH = [-\u2013\u2014\u2015]
NUMBER_SIGN = \+ | {DASH}
INTEGER_SUFFIX = [dDlL]
NUMBER_MULTIPLIER = ([kK]|[mM]|[gG]|[tT]|[pP])[bB]
REAL_EXPONENT = [eE]{NUMBER_SIGN}?\d+{INTEGER_SUFFIX}?{NUMBER_MULTIPLIER}?
LINE_COMMENT = #{INPUT_CHARACTER}*
BLOCK_COMMENT = <#({INPUT_CHARACTER}|{LINE_TERMINATOR})*#>
DECIMAL_INTEGER_LITERAL = {NUMBER_SIGN}?\d+{INTEGER_SUFFIX}?{NUMBER_MULTIPLIER}?
HEXADECIMAL_INTEGER_LITERAL = {NUMBER_SIGN}?0x[\da-fA-F]+{INTEGER_SUFFIX}?{NUMBER_MULTIPLIER}?
REAL_LITERAL = {NUMBER_SIGN}?\d*\.\d+{REAL_EXPONENT}?
KEYWORD = begin|break|catch|class|continue|data|define|do|dynamicparam|else|elseif|end|exit
            |filter|finally|for|foreach|from|function|if|in|inlinescript|parallel|param|process
            |return|switch|throw|trap|try|until|using|var|while|workflow
%%

<YYINITIAL> {
    {WHITE_SPACE}                   { return TokenType.WHITE_SPACE; }
    {LINE_COMMENT}                  { return SINGLE_LINE_COMMENT; }
    {BLOCK_COMMENT}                 { return DELIMITED_COMMENT; }
    {KEYWORD}                       { return KEYWORD; }
    {DECIMAL_INTEGER_LITERAL}       { return DECIMAL_INTEGER_LITERAL; }
    {HEXADECIMAL_INTEGER_LITERAL}   { return HEXADECIMAL_INTEGER_LITERAL; }
    {REAL_LITERAL}                  { return REAL_LITERAL; }
}

[^]                             { return TokenType.BAD_CHARACTER; }