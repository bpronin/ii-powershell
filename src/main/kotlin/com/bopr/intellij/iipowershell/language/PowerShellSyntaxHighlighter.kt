package com.bopr.intellij.iipowershell.language

import com.bopr.intellij.iipowershell.language.psi.PowerShellTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
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
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            PowerShellTypes.KEYWORD -> KEYWORD_KEYS
            PowerShellTypes.SINGLE_LINE_COMMENT -> LINE_COMMENT_KEYS
            PowerShellTypes.DELIMITED_COMMENT -> BLOCK_COMMENT_KEYS
            PowerShellTypes.DECIMAL_INTEGER_LITERAL -> DECIMAL_INTEGER_NUMBER_KEYS
            PowerShellTypes.HEXADECIMAL_INTEGER_LITERAL -> HEXADECIMAL_INTEGER_NUMBER_KEYS
            PowerShellTypes.REAL_LITERAL -> FLOATING_POINT_NUMBER_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {

        val BAD_CHARACTER_KEY = createTextAttributesKey("BAD_CHARACTER", BAD_CHARACTER)
        val KEYWORD_KEY = createTextAttributesKey("KEYWORD", KEYWORD)
        val LINE_COMMENT_KEY = createTextAttributesKey("LINE_COMMENT", LINE_COMMENT)
        val BLOCK_COMMENT_KEY = createTextAttributesKey("BLOCK_COMMENT", BLOCK_COMMENT)
        val DECIMAL_INTEGER_NUMBER_KEY = createTextAttributesKey("DECIMAL_INTEGER_NUMBER", NUMBER)
        val HEXADECIMAL_INTEGER_NUMBER_KEY = createTextAttributesKey("HEXADECIMAL_INTEGER_NUMBER", NUMBER)
        val FLOATING_POINT_NUMBER_KEY = createTextAttributesKey("FLOATING_POINT_NUMBER", NUMBER)

        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER_KEY)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT_KEY)
        private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT_KEY)
        private val KEYWORD_KEYS = arrayOf(KEYWORD_KEY)
        private val DECIMAL_INTEGER_NUMBER_KEYS = arrayOf(DECIMAL_INTEGER_NUMBER_KEY)
        private val HEXADECIMAL_INTEGER_NUMBER_KEYS = arrayOf(HEXADECIMAL_INTEGER_NUMBER_KEY)
        private val FLOATING_POINT_NUMBER_KEYS = arrayOf(FLOATING_POINT_NUMBER_KEY)
    }
}