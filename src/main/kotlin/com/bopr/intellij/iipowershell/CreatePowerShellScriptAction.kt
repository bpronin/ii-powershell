package com.bopr.intellij.iipowershell

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import org.intellij.sdk.language.PowerShellFileType

class CreatePowerShellScriptAction : CreateFileFromTemplateAction(
    PowerShellFileType.INSTANCE.name, "Create PowerShell script", PowerShellFileType.INSTANCE.icon
), DumbAware {

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New PowerShell Script")
            .setDefaultText("untitled")
            .addKind("PowerShell script file", PowerShellFileType.INSTANCE.icon, "powershell-file.ps1")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?) =
        "New PowerShell script$newName"

}