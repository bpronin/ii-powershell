package com.bopr.intellij.iipowershell.language.psi

import com.intellij.psi.tree.TokenSet

interface PowerShellTokenSets {

    companion object {

        val IDENTIFIERS = TokenSet.create(PowerShellTypes.KEY)
        val COMMENTS = TokenSet.create(PowerShellTypes.COMMENT)
    }
}