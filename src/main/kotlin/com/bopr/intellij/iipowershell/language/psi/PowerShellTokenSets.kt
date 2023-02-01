package com.bopr.intellij.iipowershell.language.psi

import com.intellij.psi.tree.TokenSet

interface PowerShellTokenSets {

    companion object {

        val KEYWORDS = TokenSet.create(PowerShellTypes.KEYWORD)
        val COMMENTS = TokenSet.create(PowerShellTypes.COMMENT)
    }
}