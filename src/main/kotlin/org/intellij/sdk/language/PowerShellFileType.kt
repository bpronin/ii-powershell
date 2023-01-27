package org.intellij.sdk.language

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import java.util.*
import javax.swing.Icon

class PowerShellFileType : LanguageFileType(PowerShellLanguage.INSTANCE) {

    private val r = ResourceBundle.getBundle("values.strings")
    private val iconImage = IconLoader.getIcon("/icons/powershell-file.svg", javaClass)

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
        return iconImage
    }

    companion object {

        @JvmStatic
        val INSTANCE = PowerShellFileType()
    }
}