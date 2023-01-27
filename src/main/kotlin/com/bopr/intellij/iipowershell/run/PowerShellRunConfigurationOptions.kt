package com.bopr.intellij.iipowershell.run

import com.intellij.execution.configurations.RunConfigurationOptions
import com.intellij.openapi.components.StoredProperty

class PowerShellRunConfigurationOptions : RunConfigurationOptions() {

    private val scriptPathProperty: StoredProperty<String?> = string("").provideDelegate(this, "scriptPath")
    private val scriptArgumentsProperty: StoredProperty<String?> = string("").provideDelegate(this, "scriptArguments")
    private val executablePathProperty: StoredProperty<String?> =
        string("pwsh.exe").provideDelegate(this, "executablePath")

    var scriptPath
        get() = scriptPathProperty.getValue(this)!!
        set(value) = scriptPathProperty.setValue(this, value)

    var scriptArguments
        get() = scriptArgumentsProperty.getValue(this)
        set(value) = scriptArgumentsProperty.setValue(this, value)

    var executablePath: String
        get() = executablePathProperty.getValue(this)!!
        set(value) = executablePathProperty.setValue(this, value)
}