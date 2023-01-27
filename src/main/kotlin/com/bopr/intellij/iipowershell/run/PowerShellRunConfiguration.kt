package com.bopr.intellij.iipowershell.run

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project

class PowerShellRunConfiguration(
    project: Project, factory: ConfigurationFactory
) : RunConfigurationBase<PowerShellRunConfigurationOptions>(
    project, factory, "PowerShell"
) {

    public override fun getOptions(): PowerShellRunConfigurationOptions {
        return super.getOptions() as PowerShellRunConfigurationOptions
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> {
        return PowerShellRunConfigurationEditor(project)
    }

    override fun checkConfiguration() {}

    override fun getState(executor: Executor, executionEnvironment: ExecutionEnvironment): RunProfileState {
        return object : CommandLineState(executionEnvironment) {

            @Throws(ExecutionException::class)
            override fun startProcess(): ProcessHandler {
                val commandLine = PtyCommandLine().apply {
                    withExePath(options.executablePath)
                    addParameter(options.scriptPath)
                    options.scriptArguments?.let { addParameter(it) }
                }

                return ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine).apply {
                    ProcessTerminatedListener.attach(this)
                }
            }
        }
    }
}