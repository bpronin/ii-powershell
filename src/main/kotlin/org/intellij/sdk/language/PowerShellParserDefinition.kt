package org.intellij.sdk.language

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
import org.intellij.sdk.language.parser.PowerShellParser
import org.intellij.sdk.language.psi.PowerShellFile
import org.intellij.sdk.language.psi.PowerShellTokenSets
import org.intellij.sdk.language.psi.PowerShellTypes

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
        return TokenSet.EMPTY
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

        val FILE = IFileElementType(PowerShellLanguage.INSTANCE)
    }
}