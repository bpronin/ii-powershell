package com.bopr.intellij.iipowershell.ui

import com.bopr.intellij.iipowershell.language.PowerShellLexerAdapter
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.bopr.intellij.iipowershell.language.psi.PowerShellTypes as Types
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Colors

class PowerShellSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer {
        return PowerShellLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            TokenType.BAD_CHARACTER -> BAD_CHARACTER_KEYS
            Types.BLOCK_COMMENT -> BLOCK_COMMENT_KEYS
            Types.BRACED_VARIABLE_NAME -> VARIABLE_KEYS
            Types.BRACES -> BRACES_KEYS
            Types.BRACKETS -> BRACKETS_KEYS
            Types.COMMA -> COMMA_KEYS
            Types.DECIMAL_INTEGER_NUMBER -> DECIMAL_INTEGER_NUMBER_KEYS
            Types.DOT -> DOT_KEYS
            Types.HEXADECIMAL_INTEGER_NUMBER -> HEXADECIMAL_INTEGER_NUMBER_KEYS
            Types.KEYWORD -> KEYWORD_KEYS
            Types.LINE_COMMENT -> LINE_COMMENT_KEYS
            Types.PARENTHESES -> PARENTHESES_KEYS
            Types.REAL_NUMBER -> FLOATING_POINT_NUMBER_KEYS
            Types.SEMICOLON -> SEMICOLON_KEYS
            Types.STRING -> STRING_KEYS
            Types.COMMA -> COMMA_KEYS
//            Types.SIGNATURE -> SIGNATURE_KEYS
            Types.REQUIRES_COMMENT -> REQUIRES_COMMENT_KEYS
            Types.VARIABLE_NAME -> VARIABLE_KEYS
            Types.ASSIGNMENT_OPERATORS -> ASSIGNMENT_OPERATORS_KEYS
            Types.FILE_REDIRECTION_OPERATORS -> FILE_REDIRECTION_OPERATORS_KEYS
            Types.MERGING_REDIRECTION_OPERATORS -> MERGING_REDIRECTION_OPERATORS_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {

        val BAD_CHARACTER_KEY = createTextAttributesKey("PWSH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
        val KEYWORD_KEY = createTextAttributesKey("PWSH_KEYWORD", Colors.KEYWORD)
        val BRACES_KEY = createTextAttributesKey("PWSH_BRACES", Colors.BRACES)
        val DOT_KEY = createTextAttributesKey("PWSH_DOT", Colors.DOT)
        val SEMICOLON_KEY = createTextAttributesKey("PWSH_SEMICOLON", Colors.SEMICOLON)
        val COMMA_KEY = createTextAttributesKey("PWSH_COMMA", Colors.COMMA)
        val PARENTHESES_KEY = createTextAttributesKey("PWSH_PARENTHESES", Colors.PARENTHESES)
        val BRACKETS_KEY = createTextAttributesKey("PWSH_BRACKETS", Colors.BRACKETS)
        val LINE_COMMENT_KEY = createTextAttributesKey("PWSH_LINE_COMMENT", Colors.LINE_COMMENT)
        val BLOCK_COMMENT_KEY = createTextAttributesKey("PWSH_BLOCK_COMMENT", Colors.BLOCK_COMMENT)
        val REQUIRES_COMMENT_KEY = createTextAttributesKey("PWSH_REQUIRES_COMMENT", Colors.LINE_COMMENT)
//        val SIGNATURE_KEY = createTextAttributesKey("PWSH_SIGNATURE", Colors.LINE_COMMENT)
        val DECIMAL_INTEGER_NUMBER_KEY = createTextAttributesKey("PWSH_DECIMAL_INTEGER_NUMBER", Colors.NUMBER)
        val HEXADECIMAL_INTEGER_NUMBER_KEY = createTextAttributesKey("PWSH_HEXADECIMAL_INTEGER_NUMBER", Colors.NUMBER)
        val FLOATING_POINT_NUMBER_KEY = createTextAttributesKey("PWSH_FLOATING_POINT_NUMBER", Colors.NUMBER)
        val STRING_KEY = createTextAttributesKey("PWSH_STRING", Colors.STRING)
        val VARIABLE_KEY = createTextAttributesKey("PWSH_VARIABLE", Colors.LOCAL_VARIABLE)
        val ASSIGNMENT_OPERATORS_KEY = createTextAttributesKey("PWSH_ASSIGNMENT_OPERATORS", Colors.OPERATION_SIGN)
        val FILE_REDIRECTION_OPERATORS_KEY = createTextAttributesKey("PWSH_FILE_REDIRECTION_OPERATORS", Colors.OPERATION_SIGN)
        val MERGING_REDIRECTION_OPERATORS_KEY = createTextAttributesKey("PWSH_MERGING_REDIRECTION_OPERATORS", Colors.OPERATION_SIGN)

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
        private val STRING_KEYS = arrayOf(STRING_KEY)
        private val VARIABLE_KEYS = arrayOf(VARIABLE_KEY)
        private val REQUIRES_COMMENT_KEYS = arrayOf(REQUIRES_COMMENT_KEY)
//        private val SIGNATURE_KEYS = arrayOf(SIGNATURE_KEY)
        private val ASSIGNMENT_OPERATORS_KEYS = arrayOf(ASSIGNMENT_OPERATORS_KEY)
        private val FILE_REDIRECTION_OPERATORS_KEYS = arrayOf(FILE_REDIRECTION_OPERATORS_KEY)
        private val MERGING_REDIRECTION_OPERATORS_KEYS = arrayOf(MERGING_REDIRECTION_OPERATORS_KEY)
    }
}