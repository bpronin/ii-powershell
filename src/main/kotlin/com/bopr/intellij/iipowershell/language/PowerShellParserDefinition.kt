package com.bopr.intellij.iipowershell.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.bopr.intellij.iipowershell.language.psi.PowerShellFile
import com.bopr.intellij.iipowershell.language.psi.PowerShellTokenSets
import com.bopr.intellij.iipowershell.language.psi.PowerShellTypes
import com.bopr.intellij.iipowershell.parser.PowerShellParser

/*
 * NOTE: To avoid unnecessary classloading when initializing the extension point implementation, all TokenSet return
 * values should use constants from dedicated $Language$TokenSets class.
 */
class PowerShellParserDefinition : ParserDefinition {

    override fun createLexer(project: Project): Lexer {
        return PowerShellLexerAdapter()
    }

    override fun getCommentTokens(): TokenSet {
        return PowerShellTokenSets.COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return PowerShellTokenSets.STRINGS
    }

    override fun createParser(project: Project): PsiParser {
        return PowerShellParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return PowerShellFile(viewProvider)
    }

    override fun createElement(node: ASTNode): PsiElement {
        return PowerShellTypes.Factory.createElement(node)
    }

    companion object {

        private val FILE = IFileElementType(PowerShellLanguage.INSTANCE)
    }
}