package com.bopr.intellij.iipowershell.language

import com.intellij.lexer.FlexAdapter

class PowerShellLexerAdapter : FlexAdapter(PowerShellLexer(null))