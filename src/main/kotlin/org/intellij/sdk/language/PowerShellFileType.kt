package org.intellij.sdk.language

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class PowerShellFileType : LanguageFileType(PowerShellLanguage.INSTANCE) {

    override fun getName(): String {
        return "PowerShell Script"
    }

    override fun getDescription(): String {
        return "PowerShell script file"
    }

    override fun getDefaultExtension(): String {
        return "ps1"
    }

    override fun getIcon(): Icon {
        return IconLoader.getIcon("/icons/powershell-16.png", javaClass)
    }

    companion object {

        @JvmStatic
        val INSTANCE = PowerShellFileType()
    }
}