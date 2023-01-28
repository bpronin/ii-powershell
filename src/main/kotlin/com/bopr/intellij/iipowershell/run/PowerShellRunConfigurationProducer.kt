package com.bopr.intellij.iipowershell.run

import com.bopr.intellij.iipowershell.language.psi.PowerShellFile
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

class PowerShellRunConfigurationProducer : LazyRunConfigurationProducer<PowerShellRunConfiguration>() {

    private val configurationFactory = PowerShellRunConfigurationType().defaultConfigurationFactory

    override fun getConfigurationFactory(): ConfigurationFactory {
        return configurationFactory
    }

    override fun setupConfigurationFromContext(
        configuration: PowerShellRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val element = context.psiLocation as? PowerShellFile ?: return false
        configuration.scriptPath = element.virtualFile.toNioPath()
        configuration.workingDirectory = element.virtualFile.parent.toNioPath()
        return true
    }

    override fun isConfigurationFromContext(
        configuration: PowerShellRunConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val element = context.psiLocation as? PowerShellFile ?: return false
        return element.virtualFile.toNioPath() == configuration.workingDirectory.resolve(configuration.scriptPath)
    }
}