package com.bopr.intellij.iipowershell.ui

import com.bopr.intellij.iipowershell.language.PowerShellLexerAdapter
import com.bopr.intellij.iipowershell.language.psi.PowerShellTypes
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

        val BAD_CHARACTER = createKey("PWSH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER, TokenType.BAD_CHARACTER)
        val BLOCK_COMMENT = createKey("PWSH_BLOCK_COMMENT", Colors.BLOCK_COMMENT, Types.BLOCK_COMMENT)
        val BRACE = createKey(
            "PWSH_BRACE", Colors.BRACES,
            Types.LBRACE, Types.RBRACE, Types.ARRAY_EXPRESSION_START, Types.HASH_EXPRESSION_START
        )
        val BRACKET = createKey(
            "PWSH_BRACKET", Colors.BRACKETS,
            Types.LBRACKET, Types.RBRACKET
        )
        val FLOATING_POINT_NUMBER = createKey("PWSH_REAL_NUMBER", Colors.NUMBER, Types.REAL_NUMBER)
        val KEYWORD = createKey("PWSH_KEYWORD", Colors.KEYWORD, Types.KEYWORD)
        val LINE_COMMENT = createKey("PWSH_LINE_COMMENT", Colors.LINE_COMMENT, Types.LINE_COMMENT)
        val PARENTHESIS = createKey(
            "PWSH_PARENTHESIS", Colors.PARENTHESES,
            Types.LPARENTHESIS, Types.RPARENTHESIS, Types.BRACED_VARIABLE_START, Types.SUB_EXPRESSION_START
        )
        val REQUIRES_COMMENT = createKey("PWSH_REQUIRES_COMMENT", Colors.LINE_COMMENT, Types.REQUIRES_COMMENT)
        val SEMICOLON = createKey("PWSH_SEMICOLON", Colors.SEMICOLON, Types.SEMICOLON)
        val COMMAND = createKey("PWSH_COMMAND", Colors.FUNCTION_CALL, Types.COMMAND_IDENTIFIER)
//        val TYPE = createKey("PWSH_TYPE", Colors.CLASS_NAME, Types.TYPE_IDENTIFIER)
//        val ATTRIBUTE = createKey("PWSH_ATTRIBUTE", Colors.METADATA, Types.ATTRIBUTE_IDENTIFIER)
        val SIGNATURE = createKey("PWSH_SIGNATURE", Colors.LINE_COMMENT, Types.SIGNATURE)
        val ASSIGNMENT_OPERATOR =
            createKey("PWSH_ASSIGNMENT_OPERATOR", Colors.OPERATION_SIGN, Types.ASSIGNMENT_OPERATOR)
        val DECIMAL_INTEGER_NUMBER =
            createKey("PWSH_DECIMAL_INTEGER_NUMBER", Colors.NUMBER, Types.DECIMAL_INTEGER_NUMBER)
        val VARIABLE =
            createKey("PWSH_VARIABLE", Colors.LOCAL_VARIABLE, Types.REGULAR_VARIABLE, Types.BRACED_VARIABLE)
        val DOT = createKey("PWSH_DOT", Colors.DOT, Types.DOT)
        val COMMA = createKey("PWSH_COMMA", Colors.COMMA, Types.COMMA)
        val LABEL = createKey("PWSH_LABEL", Colors.LABEL, Types.LABEL)
        val FUNCTION_DECLARATION =
            createKey("PWSH_FUNCTION_DECLARATION", Colors.FUNCTION_DECLARATION, Types.FUNCTION_NAME)
        val FILE_REDIRECTION_OPERATOR =
            createKey("PWSH_FILE_REDIRECTION_OPERATOR", Colors.OPERATION_SIGN, Types.FILE_REDIRECTION_OPERATOR)
        val HEXADECIMAL_INTEGER_NUMBER =
            createKey("PWSH_HEXADECIMAL_INTEGER_NUMBER", Colors.NUMBER, Types.HEXADECIMAL_INTEGER_NUMBER)
        val MERGING_REDIRECTION_OPERATOR =
            createKey("PWSH_MERGING_REDIRECTION_OPERATOR", Colors.OPERATION_SIGN, Types.MERGING_REDIRECTION_OPERATOR)
        val STRING = createKey(
            "PWSH_STRING", Colors.STRING,
            Types.STRING, Types.MULTILINE_STRING, Types.VERBATIM_STRING, Types.VERBATIM_MULTILINE_STRING,
            Types.VERBATIM_COMMAND_ARGUMENT
        )
        val COMMAND_PARAMETER = createKey(
            "PWSH_COMMAND_PARAMETER", Colors.PARAMETER,
            Types.COMMAND_PARAMETER, Types.SWITCH_PARAMETER, Types.FILE_PARAMETER, Types.SUPPORTED_COMMAND_PARAMETER
        )
        val PREDEFINED_COMMAND_PARAMETER = createKey(
            "PWSH_PREDEFINED_COMMAND_PARAMETER", Colors.PREDEFINED_SYMBOL,
            Types.FILE_PARAMETER, Types.SWITCH_PARAMETER, Types.SUPPORTED_COMMAND_PARAMETER
        )
        val OTHER_OPERATOR = createKey(
            "PWSH_OTHER_OPERATOR", Colors.OPERATION_SIGN,
            Types.FORMAT_OPERATOR, Types.COMPARISON_OPERATOR, Types.INCREMENT_OPERATOR, Types.DECREMENT_OPERATOR,
            Types.SYMBOLIC_OPERATOR
        )
    }
}
