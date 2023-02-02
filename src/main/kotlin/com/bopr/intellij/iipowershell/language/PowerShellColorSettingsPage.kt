package com.bopr.intellij.iipowershell.language

import com.bopr.intellij.iipowershell.language.PowerShellSyntaxHighlighter.Companion.BAD_CHARACTER_KEY
import com.bopr.intellij.iipowershell.language.PowerShellSyntaxHighlighter.Companion.BLOCK_COMMENT_KEY
import com.bopr.intellij.iipowershell.language.PowerShellSyntaxHighlighter.Companion.DECIMAL_INTEGER_NUMBER_KEY
import com.bopr.intellij.iipowershell.language.PowerShellSyntaxHighlighter.Companion.FLOATING_POINT_NUMBER_KEY
import com.bopr.intellij.iipowershell.language.PowerShellSyntaxHighlighter.Companion.HEXADECIMAL_INTEGER_NUMBER_KEY
import com.bopr.intellij.iipowershell.language.PowerShellSyntaxHighlighter.Companion.KEYWORD_KEY
import com.bopr.intellij.iipowershell.language.PowerShellSyntaxHighlighter.Companion.LINE_COMMENT_KEY
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
          return  javaClass.getResource("/values/powershell-demo-text.ps1")!!.readText(Charsets.UTF_8)
    }

    companion object {

        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor(string("bad_value"), BAD_CHARACTER_KEY),
            AttributesDescriptor(string("keyword"), KEYWORD_KEY),
            AttributesDescriptor(string("comments//line_comment"), LINE_COMMENT_KEY),
            AttributesDescriptor(string("comments//block_comment"), BLOCK_COMMENT_KEY),
            AttributesDescriptor(string("number//decimal_integer"), DECIMAL_INTEGER_NUMBER_KEY),
            AttributesDescriptor(string("number//hexadecimal_integer"), HEXADECIMAL_INTEGER_NUMBER_KEY),
            AttributesDescriptor(string("number//floating_point"), FLOATING_POINT_NUMBER_KEY)
        )
    }
}