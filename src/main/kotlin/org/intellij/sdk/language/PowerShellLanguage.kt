package org.intellij.sdk.language

import com.intellij.lang.Language


class PowerShellLanguage : Language("PowerShell") {

    companion object {

        @JvmStatic
        val INSTANCE = PowerShellLanguage()
    }

}