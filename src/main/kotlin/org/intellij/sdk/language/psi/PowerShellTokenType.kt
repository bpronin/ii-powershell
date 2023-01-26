package org.intellij.sdk.language.psi

import com.intellij.psi.tree.IElementType
import org.intellij.sdk.language.PowerShellLanguage

class PowerShellTokenType(debugName: String) : IElementType(debugName, PowerShellLanguage.INSTANCE) {

    override fun toString(): String {
        return "PowerShellTokenType." + super.toString()
    }
}