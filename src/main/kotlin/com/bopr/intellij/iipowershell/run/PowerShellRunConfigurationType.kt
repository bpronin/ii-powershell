package com.bopr.intellij.iipowershell.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import org.intellij.sdk.language.PowerShellFileType.Companion.POWERSHELL_FILE_ICON
import org.intellij.sdk.language.R
import javax.swing.Icon

class PowerShellRunConfigurationType : ConfigurationType {

    override fun getDisplayName(): String {
        return "PowerShell"
    }

    override fun getConfigurationTypeDescription(): String {
        return R.getString("powershell_run_configuration_type")
    }

    override fun getIcon(): Icon {
        return POWERSHELL_FILE_ICON
    }

    override fun getId(): String {
        return ID
    }

    override fun getConfigurationFactories(): Array<ConfigurationFactory> {
        return arrayOf(PowerShellRunConfigurationFactory(this))
    }

    companion object {

        const val ID = "PowerShellRunConfiguration"
    }
}