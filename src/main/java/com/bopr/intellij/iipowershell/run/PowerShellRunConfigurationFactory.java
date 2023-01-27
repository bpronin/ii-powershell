package com.bopr.intellij.iipowershell.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PowerShellRunConfigurationFactory extends ConfigurationFactory {

    protected PowerShellRunConfigurationFactory(ConfigurationType type) {
        super(type);
    }

    @Override
    public @NotNull String getId() {
        return PowerShellRunConfigurationType.ID;
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new PowerShellRunConfiguration(project, this, "PowerShell");
    }

    @Nullable
    @Override
    public Class<? extends BaseState> getOptionsClass() {
        return PowerShellRunConfigurationOptions.class;
    }

}