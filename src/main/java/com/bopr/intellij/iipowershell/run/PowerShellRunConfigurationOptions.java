package com.bopr.intellij.iipowershell.run;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PowerShellRunConfigurationOptions extends RunConfigurationOptions {

    private final StoredProperty<String> scriptPath = string("").provideDelegate(this, "scriptPath");
    private final StoredProperty<String> scriptArguments = string("").provideDelegate(this, "scriptArguments");
    private final StoredProperty<String> executablePath = string("pwsh.exe").provideDelegate(this, "executablePath");

    @NotNull
    public String getScriptPath() {
        return scriptPath.getValue(this);
    }

    public void setScriptPath(String value) {
        scriptPath.setValue(this, value);
    }

    @Nullable
    public String getScriptArguments() {
        return scriptArguments.getValue(this);
    }

    public void setScriptArguments(String value) {
        scriptArguments.setValue(this, value);
    }

    @NotNull
    public String getExecutablePath() {
        return executablePath.getValue(this);
    }

    public void setExecutablePath(String value) {
        executablePath.setValue(this, value);
    }

}