package com.bopr.intellij.iipowershell.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.bopr.intellij.iipowershell.language.PowerShellFileType.Companion.POWERSHELL_FILE_ICON
import com.bopr.intellij.iipowershell.util.Resources.string
import javax.swing.Icon

class PowerShellRunConfigurationType : ConfigurationType {

    val defaultConfigurationFactory = PowerShellRunConfigurationFactory(this)
    private val runConfigurationFactories = arrayOf<ConfigurationFactory>(defaultConfigurationFactory)

    override fun getDisplayName(): String {
        return "PowerShell"
    }

    override fun getConfigurationTypeDescription(): String {
        return string("powershell_run_configuration_type")
    }

    override fun getIcon(): Icon {
        return POWERSHELL_FILE_ICON
    }

    override fun getId(): String {
        return ID
    }

    override fun getConfigurationFactories(): Array<ConfigurationFactory> {
        return runConfigurationFactories
    }

    companion object {

        const val ID = "PowerShellRunConfiguration"
    }
}