package com.bopr.intellij.iipowershell.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.bopr.intellij.iipowershell.language.PowerShellFileType

class CreatePowerShellScriptAction : CreateFileFromTemplateAction(
    PowerShellFileType.POWERSHELL_FILE_NAME, "Create PowerShell script", PowerShellFileType.POWERSHELL_FILE_ICON
), DumbAware {

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New PowerShell Script")
            .setDefaultText("untitled")
            .addKind("PowerShell script file", PowerShellFileType.POWERSHELL_FILE_ICON, "powershell-file.ps1")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?) =
        "New PowerShell script$newName"

}