package com.bopr.intellij.iipowershell.run;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class PowerShellRunConfigurationOptions extends RunConfigurationOptions {

    private final StoredProperty<String> scriptPath = string("").provideDelegate(this, "scriptPath");
    private final StoredProperty<String> scriptArguments = string("").provideDelegate(this, "scriptArguments");
    private final StoredProperty<String> executablePath = string("pwsh.exe").provideDelegate(this, "executablePath");

    public String getScriptPath() {
        return scriptPath.getValue(this);
    }

    public void setScriptPath(String value) {
        scriptPath.setValue(this, value);
    }

    public String getScriptArguments() {
        return scriptArguments.getValue(this);
    }

    public void setScriptArguments(String value) {
        scriptArguments.setValue(this, value);
    }

    public String getExecutablePath() {
        return executablePath.getValue(this);
    }

    public void setExecutablePath(String value) {
        executablePath.setValue(this, value);
    }

}