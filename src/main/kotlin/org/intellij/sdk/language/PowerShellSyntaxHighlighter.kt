package org.intellij.sdk.language

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import org.intellij.sdk.language.psi.PowerShellTypes

class PowerShellSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer {
        return PowerShellLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            PowerShellTypes.SEPARATOR -> SEPARATOR_KEYS
            PowerShellTypes.KEY -> KEY_KEYS
            PowerShellTypes.VALUE -> VALUE_KEYS
            PowerShellTypes.COMMENT -> COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {

        val SEPARATOR = createTextAttributesKey("SIMPLE_SEPARATOR", OPERATION_SIGN)
        val KEY = createTextAttributesKey("SIMPLE_KEY", KEYWORD)
        val VALUE = createTextAttributesKey("SIMPLE_VALUE", STRING)
        val COMMENT = createTextAttributesKey("SIMPLE_COMMENT", LINE_COMMENT)
        val BAD_CHARACTER = createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
        private val KEY_KEYS = arrayOf(KEY)
        private val VALUE_KEYS = arrayOf(VALUE)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }
}