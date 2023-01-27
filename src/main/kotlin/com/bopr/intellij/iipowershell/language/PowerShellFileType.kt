package com.bopr.intellij.iipowershell.language

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import com.bopr.intellij.iipowershell.language.Resources.string
import javax.swing.Icon

class PowerShellFileType : LanguageFileType(PowerShellLanguage.INSTANCE) {

    override fun getName(): String {
        return POWERSHELL_FILE_NAME
    }

    override fun getDescription(): String {
        return string("powershell_script_file")
    }

    override fun getDefaultExtension(): String {
        return POWERSHELL_FILE_EXTENSION
    }

    override fun getIcon(): Icon {
        return POWERSHELL_FILE_ICON
    }

    companion object {

        const val POWERSHELL_FILE_NAME = "PowerShell Script"
        const val POWERSHELL_FILE_EXTENSION = "ps1"
        val POWERSHELL_FILE_ICON = IconLoader.getIcon("/icons/powershell-file.svg", Companion::class.java)

        @JvmStatic
        val INSTANCE = PowerShellFileType()
    }
}