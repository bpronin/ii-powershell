package com.bopr.intellij.iipowershell.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import org.intellij.sdk.language.PowerShellFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class PowerShellRunConfigurationType implements ConfigurationType {

    static final String ID = "PowerShellRunConfiguration";

    @NotNull
    @Override
    public String getDisplayName() {
        return "PowerShell";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "PowerShell run configuration type";
    }

    @Override
    public Icon getIcon() {
        return PowerShellFileType.getINSTANCE().getIcon();
    }

    @NotNull
    @Override
    public String getId() {
        return ID;
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{new PowerShellRunConfigurationFactory(this)};
    }

}