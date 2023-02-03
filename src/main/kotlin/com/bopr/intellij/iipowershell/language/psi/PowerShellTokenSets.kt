package com.bopr.intellij.iipowershell.language.psi

import com.bopr.intellij.iipowershell.language.psi.PowerShellTypes.*
import com.intellij.psi.tree.TokenSet

interface PowerShellTokenSets {

    companion object {

        val COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT)
        val STRINGS = TokenSet.create(STRING)
    }
}