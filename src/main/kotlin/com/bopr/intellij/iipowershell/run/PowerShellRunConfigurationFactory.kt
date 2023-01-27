package com.bopr.intellij.iipowershell.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.project.Project

class PowerShellRunConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {

    override fun getId(): String {
        return PowerShellRunConfigurationType.ID
    }

    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return PowerShellRunConfiguration(project, this)
    }

    override fun getOptionsClass(): Class<out BaseState?> {
        return PowerShellRunConfigurationOptions::class.java
    }
}