package org.intellij.sdk.language

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

        /* NOTE: It is supported to group related attributes like operators or braces by separating the nodes with /
        * like ' "Operators//Plus", "Operators//Minus" '*/
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Key", PowerShellSyntaxHighlighter.KEY),
            AttributesDescriptor("Separator", PowerShellSyntaxHighlighter.SEPARATOR),
            AttributesDescriptor("Value", PowerShellSyntaxHighlighter.VALUE),
            AttributesDescriptor("Bad value", PowerShellSyntaxHighlighter.BAD_CHARACTER)
        )
    }
}