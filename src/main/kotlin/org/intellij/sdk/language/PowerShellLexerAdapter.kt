package org.intellij.sdk.language

import com.intellij.lexer.FlexAdapter

class PowerShellLexerAdapter : FlexAdapter(PowerShellLexer(null))