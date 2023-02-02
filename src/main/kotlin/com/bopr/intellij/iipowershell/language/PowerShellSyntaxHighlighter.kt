package com.bopr.intellij.iipowershell.language

import com.bopr.intellij.iipowershell.language.psi.PowerShellTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.BAD_CHARACTER
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class PowerShellSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer {
        return PowerShellLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            TokenType.BAD_CHARACTER -> BAD_CHARACTER_KEYS
            PowerShellTypes.KEYWORD -> KEYWORD_KEYS
            PowerShellTypes.BRACES -> BRACES_KEYS
            PowerShellTypes.DOT -> DOT_KEYS
            PowerShellTypes.SEMICOLON -> SEMICOLON_KEYS
            PowerShellTypes.COMMA -> COMMA_KEYS
            PowerShellTypes.PARENTHESES -> PARENTHESES_KEYS
            PowerShellTypes.BRACKETS -> BRACKETS_KEYS
            PowerShellTypes.SINGLE_LINE_COMMENT -> LINE_COMMENT_KEYS
            PowerShellTypes.DELIMITED_COMMENT -> BLOCK_COMMENT_KEYS
            PowerShellTypes.DECIMAL_INTEGER_LITERAL -> DECIMAL_INTEGER_NUMBER_KEYS
            PowerShellTypes.HEXADECIMAL_INTEGER_LITERAL -> HEXADECIMAL_INTEGER_NUMBER_KEYS
            PowerShellTypes.REAL_LITERAL -> FLOATING_POINT_NUMBER_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {

        val BAD_CHARACTER_KEY = createTextAttributesKey("POWERSHELL_BAD_CHARACTER", BAD_CHARACTER)
        val KEYWORD_KEY = createTextAttributesKey("POWERSHELL_KEYWORD", KEYWORD)
        val BRACES_KEY = createTextAttributesKey("POWERSHELL_BRACES", BRACES);
        val DOT_KEY = createTextAttributesKey("POWERSHELL_DOT", DOT)
        val SEMICOLON_KEY = createTextAttributesKey("POWERSHELL_SEMICOLON", SEMICOLON)
        val COMMA_KEY = createTextAttributesKey("POWERSHELL_COMMA", COMMA)
        val PARENTHESES_KEY = createTextAttributesKey("POWERSHELL_PARENTHESES", PARENTHESES)
        val BRACKETS_KEY = createTextAttributesKey("POWERSHELL_BRACKETS", BRACKETS)
        val LINE_COMMENT_KEY = createTextAttributesKey("POWERSHELL_LINE_COMMENT", LINE_COMMENT)
        val BLOCK_COMMENT_KEY = createTextAttributesKey("POWERSHELL_BLOCK_COMMENT", BLOCK_COMMENT)
        val DECIMAL_INTEGER_NUMBER_KEY = createTextAttributesKey("POWERSHELL_DECIMAL_INTEGER_NUMBER", NUMBER)
        val HEXADECIMAL_INTEGER_NUMBER_KEY = createTextAttributesKey("POWERSHELL_HEXADECIMAL_INTEGER_NUMBER", NUMBER)
        val FLOATING_POINT_NUMBER_KEY = createTextAttributesKey("POWERSHELL_FLOATING_POINT_NUMBER", NUMBER)

        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
        private val BAD_CHARACTER_KEYS = arrayOf(BAD_CHARACTER_KEY)
        private val DOT_KEYS = arrayOf(DOT_KEY)
        private val BRACES_KEYS = arrayOf(BRACES_KEY)
        private val SEMICOLON_KEYS = arrayOf(SEMICOLON_KEY)
        private val COMMA_KEYS = arrayOf(COMMA_KEY)
        private val PARENTHESES_KEYS = arrayOf(PARENTHESES_KEY)
        private val BRACKETS_KEYS = arrayOf(BRACKETS_KEY)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT_KEY)
        private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT_KEY)
        private val KEYWORD_KEYS = arrayOf(KEYWORD_KEY)
        private val DECIMAL_INTEGER_NUMBER_KEYS = arrayOf(DECIMAL_INTEGER_NUMBER_KEY)
        private val HEXADECIMAL_INTEGER_NUMBER_KEYS = arrayOf(HEXADECIMAL_INTEGER_NUMBER_KEY)
        private val FLOATING_POINT_NUMBER_KEYS = arrayOf(FLOATING_POINT_NUMBER_KEY)
    }
}