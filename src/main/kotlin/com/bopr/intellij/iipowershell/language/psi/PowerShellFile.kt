package com.bopr.intellij.iipowershell.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.bopr.intellij.iipowershell.language.PowerShellFileType
import com.bopr.intellij.iipowershell.language.PowerShellLanguage

class PowerShellFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, PowerShellLanguage.INSTANCE) {

    override fun getFileType(): FileType {
        return PowerShellFileType.INSTANCE
    }

    override fun toString(): String {
        return "PowerShell Script"
    }
}