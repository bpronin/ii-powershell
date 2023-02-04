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
        return KEYS[tokenType] ?: EMPTY_KEYS
    }

    companion object {

        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
        private val KEYS: MutableMap<IElementType, Array<TextAttributesKey>> = mutableMapOf()

        private fun createKey(
            externalName: String, fallbackKey: TextAttributesKey, vararg elementTypes: IElementType
        ): TextAttributesKey {
            val key = createTextAttributesKey(externalName, fallbackKey)
            for (type in elementTypes) {
                KEYS[type] = arrayOf(key)
            }
            return key
        }

        //        val SIGNATURE = createKey("PWSH_SIGNATURE", Colors.LINE_COMMENT, Types.SIGNATURE)
        val BAD_CHARACTER = createKey("PWSH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER, TokenType.BAD_CHARACTER)
        val BLOCK_COMMENT = createKey("PWSH_BLOCK_COMMENT", Colors.BLOCK_COMMENT, Types.BLOCK_COMMENT)
        val BRACE = createKey("PWSH_BRACES", Colors.BRACES, Types.BRACE)
        val BRACKET = createKey("PWSH_BRACKETS", Colors.BRACKETS, Types.BRACKET)
        val COMMA = createKey("PWSH_COMMA", Colors.COMMA, Types.COMMA)
        val DOT = createKey("PWSH_DOT", Colors.DOT, Types.DOT)
        val FLOATING_POINT_NUMBER = createKey("PWSH_FLOATING_POINT_NUMBER", Colors.NUMBER, Types.REAL_NUMBER)
        val KEYWORD = createKey("PWSH_KEYWORD", Colors.KEYWORD, Types.KEYWORD)
        val LINE_COMMENT = createKey("PWSH_LINE_COMMENT", Colors.LINE_COMMENT, Types.LINE_COMMENT)
        val PARENTHESIS = createKey("PWSH_PARENTHESIS", Colors.PARENTHESES, Types.PARENTHESIS)
        val REQUIRES_COMMENT = createKey("PWSH_REQUIRES_COMMENT", Colors.LINE_COMMENT, Types.REQUIRES_COMMENT)
        val SEMICOLON = createKey("PWSH_SEMICOLON", Colors.SEMICOLON, Types.SEMICOLON)
        val STRING = createKey("PWSH_STRING", Colors.STRING, Types.STRING)
        val ASSIGNMENT_OPERATOR =
            createKey(
                "PWSH_ASSIGNMENT_OPERATORS", Colors.OPERATION_SIGN,
                Types.ASSIGNMENT_OPERATOR
            )
        val DECIMAL_INTEGER_NUMBER =
            createKey(
                "PWSH_DECIMAL_INTEGER_NUMBER", Colors.NUMBER,
                Types.DECIMAL_INTEGER_NUMBER
            )
        val VARIABLE =
            createKey(
                "PWSH_VARIABLE", Colors.LOCAL_VARIABLE, Types.VARIABLE_NAME,
                Types.BRACED_VARIABLE_NAME
            )
        val FILE_REDIRECTION_OPERATOR =
            createKey(
                "PWSH_FILE_REDIRECTION_OPERATORS", Colors.OPERATION_SIGN,
                Types.FILE_REDIRECTION_OPERATOR
            )
        val HEXADECIMAL_INTEGER_NUMBER =
            createKey(
                "PWSH_HEXADECIMAL_INTEGER_NUMBER", Colors.NUMBER,
                Types.HEXADECIMAL_INTEGER_NUMBER
            )
        val MERGING_REDIRECTION_OPERATOR =
            createKey(
                "PWSH_MERGING_REDIRECTION_OPERATORS", Colors.OPERATION_SIGN,
                Types.MERGING_REDIRECTION_OPERATOR
            )
        val ARITHMETIC_OPERATOR =
            createKey(
                "PWSH_ARITHMETIC_OPERATORS", Colors.OPERATION_SIGN,
                Types.ARITHMETIC_OPERATOR
            )
        val OTHER_OPERATOR =
            createKey(
                "PWSH_OTHER_OPERATORS", Colors.OPERATION_SIGN,
                Types.FORMAT_OPERATOR,
                Types.PREFIXED_OPERATOR,
                Types.IC_PREFIXED_OPERATOR,
                Types.B_PREFIXED_OPERATOR
            )
    }
}