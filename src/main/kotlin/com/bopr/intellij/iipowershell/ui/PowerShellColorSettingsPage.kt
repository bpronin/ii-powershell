package com.bopr.intellij.iipowershell.ui

import com.bopr.intellij.iipowershell.language.PowerShellFileType
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.ASSIGNMENT_OPERATOR
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BAD_CHARACTER
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BLOCK_COMMENT
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BRACE
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.BRACKET
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.COMMA
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.COMMAND_PARAMETER
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.DECIMAL_INTEGER_NUMBER
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.DOT
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.FILE_REDIRECTION_OPERATOR
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.FLOATING_POINT_NUMBER
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.FUNCTION_DECLARATION
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.HEXADECIMAL_INTEGER_NUMBER
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.KEYWORD
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.LINE_COMMENT
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.MERGING_REDIRECTION_OPERATOR
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.OTHER_OPERATOR
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.PARENTHESIS
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.REQUIRES_COMMENT
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.SEMICOLON
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.SIGNATURE
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.STRING
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.TYPE
import com.bopr.intellij.iipowershell.ui.PowerShellSyntaxHighlighter.Companion.VARIABLE
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
            AttributesDescriptor(string("bad_value"), BAD_CHARACTER),
            AttributesDescriptor(string("braces//braces"), BRACE),
            AttributesDescriptor(string("braces//brackets"), BRACKET),
            AttributesDescriptor(string("braces//parentheses"), PARENTHESIS),
            AttributesDescriptor(string("command_parameter"), COMMAND_PARAMETER),
            AttributesDescriptor(string("comments//block_comment"), BLOCK_COMMENT),
            AttributesDescriptor(string("comments//line_comment"), LINE_COMMENT),
            AttributesDescriptor(string("comments//requires_comment"), REQUIRES_COMMENT),
            AttributesDescriptor(string("comments//signature"), SIGNATURE),
            AttributesDescriptor(string("function_declaration"), FUNCTION_DECLARATION),
            AttributesDescriptor(string("keyword"), KEYWORD),
            AttributesDescriptor(string("number//decimal_integer"), DECIMAL_INTEGER_NUMBER),
            AttributesDescriptor(string("number//floating_point"), FLOATING_POINT_NUMBER),
            AttributesDescriptor(string("number//hexadecimal_integer"), HEXADECIMAL_INTEGER_NUMBER),
            AttributesDescriptor(string("operators//assignment_operators"), ASSIGNMENT_OPERATOR),
            AttributesDescriptor(string("operators//file_redirection_operators"), FILE_REDIRECTION_OPERATOR),
            AttributesDescriptor(string("operators//merging_redirection_operators"), MERGING_REDIRECTION_OPERATOR),
            AttributesDescriptor(string("operators//other_operators"), OTHER_OPERATOR),
            AttributesDescriptor(string("operators//semicolon"), SEMICOLON),
            AttributesDescriptor(string("operators//comma"), COMMA),
            AttributesDescriptor(string("operators//dot"), DOT),
            AttributesDescriptor(string("string_text"), STRING),
            AttributesDescriptor(string("type"), TYPE),
            AttributesDescriptor(string("variable"), VARIABLE),
        )
    }
}