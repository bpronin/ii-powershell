package org.intellij.sdk.language

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import java.util.*
import javax.swing.Icon

class PowerShellFileType : LanguageFileType(PowerShellLanguage.INSTANCE) {
    
    private val r = ResourceBundle.getBundle("values.strings")

    override fun getName(): String {
        return "PowerShell Script"
    }

    override fun getDescription(): String {
        return r.getString("powershell_script_file")
    }

    override fun getDefaultExtension(): String {
        return "ps1"
    }

    override fun getIcon(): Icon {
        return ICON
    }

    companion object {

        val ICON = IconLoader.getIcon("/icons/powershell-file.svg", Companion::class.java)

        @JvmStatic
        val INSTANCE = PowerShellFileType()
    }
}