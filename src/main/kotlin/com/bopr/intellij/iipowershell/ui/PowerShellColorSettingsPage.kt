package com.bopr.intellij.iipowershell.ui

import com.bopr.intellij.iipowershell.language.PowerShellFileType
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.ASSIGNMENT_OPERATORS_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BAD_CHARACTER_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BLOCK_COMMENT_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BRACES_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BRACKETS_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.COMMA_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.DECIMAL_INTEGER_NUMBER_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.DOT_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.FILE_REDIRECTION_OPERATORS_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.FLOATING_POINT_NUMBER_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.FORMAT_OPERATOR_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.HEXADECIMAL_INTEGER_NUMBER_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.KEYWORD_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.LINE_COMMENT_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.MERGING_REDIRECTION_OPERATORS_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.PARENTHESES_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.REQUIRES_COMMENT_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.SEMICOLON_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.STRING_KEY
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.VARIABLE_KEY
import com.bopr.intellij.iipowershell.util.Resources.string
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class PowerShellColorSettingsPage : ColorSettingsPage {

    override fun getDisplayName(): String {
        return "PowerShell"
    }

    override fun getIcon(): Icon {
        return PowerShellFileType.POWERSHELL_FILE_ICON
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return PowerShellSyntaxHighlighter()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
        return null
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDemoText(): String {
        return javaClass.getResource("/values/powershell-demo-text.ps1")!!.readText(Charsets.UTF_8)
    }

    companion object {

        private val DESCRIPTORS = arrayOf(
//            AttributesDescriptor(string("comments//signature"), SIGNATURE_KEY),
            AttributesDescriptor(string("bad_value"), BAD_CHARACTER_KEY),
            AttributesDescriptor(string("operators//assignment_operators"), ASSIGNMENT_OPERATORS_KEY),
            AttributesDescriptor(string("operators//file_redirection_operators"), FILE_REDIRECTION_OPERATORS_KEY),
            AttributesDescriptor(string("operators//merging_redirection_operators"), MERGING_REDIRECTION_OPERATORS_KEY),
            AttributesDescriptor(string("operators//format_operator"), FORMAT_OPERATOR_KEY),
            AttributesDescriptor(string("braces//braces"), BRACES_KEY),
            AttributesDescriptor(string("braces//brackets"), BRACKETS_KEY),
            AttributesDescriptor(string("operators//comma"), COMMA_KEY),
            AttributesDescriptor(string("operators//dot"), DOT_KEY),
            AttributesDescriptor(string("braces//parentheses"), PARENTHESES_KEY),
            AttributesDescriptor(string("operators//semicolon"), SEMICOLON_KEY),
            AttributesDescriptor(string("comments//block_comment"), BLOCK_COMMENT_KEY),
            AttributesDescriptor(string("comments//line_comment"), LINE_COMMENT_KEY),
            AttributesDescriptor(string("comments//requires_comment"), REQUIRES_COMMENT_KEY),
            AttributesDescriptor(string("keyword"), KEYWORD_KEY),
            AttributesDescriptor(string("number//decimal_integer"), DECIMAL_INTEGER_NUMBER_KEY),
            AttributesDescriptor(string("number//floating_point"), FLOATING_POINT_NUMBER_KEY),
            AttributesDescriptor(string("number//hexadecimal_integer"), HEXADECIMAL_INTEGER_NUMBER_KEY),
            AttributesDescriptor(string("variable"), VARIABLE_KEY),
            AttributesDescriptor(string("string_text"), STRING_KEY),
        )
    }
}