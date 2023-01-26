package org.intellij.sdk.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.intellij.sdk.language.PowerShellFileType
import org.intellij.sdk.language.PowerShellLanguage

class PowerShellFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, PowerShellLanguage.INSTANCE) {

    override fun getFileType(): FileType {
        return PowerShellFileType.INSTANCE
    }

    override fun toString(): String {
        return "PowerShell Script"
    }
}