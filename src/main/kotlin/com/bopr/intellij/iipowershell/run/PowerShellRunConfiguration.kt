package com.bopr.intellij.iipowershell.run

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configuration.EnvironmentVariablesData
import com.intellij.execution.configurations.*
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizerUtil.*
import org.jdom.Element

class PowerShellRunConfiguration(
    project: Project, factory: ConfigurationFactory
) : LocatableConfigurationBase<Element>(
    project, factory, "PowerShell"
) {

    var interpreterPath: String = "pwsh.exe"
    var scriptPath: String = ""
    var scriptArguments: String? = null
    var workingDirectory: String? = null
    var environmentVariables: EnvironmentVariablesData = EnvironmentVariablesData.DEFAULT

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        writeField(element, TAG_INTERPRETER_PATH, interpreterPath)
        writeField(element, TAG_SCRIPT_PATH, scriptPath)
        writeField(element, TAG_SCRIPT_ARGUMENTS, scriptArguments)
        writeField(element, TAG_WORKING_DIRECTORY, workingDirectory)
        environmentVariables.writeExternal(element)
    }

    override fun readExternal(element: Element) {
        super.readExternal(element)
        interpreterPath = readField(element, TAG_INTERPRETER_PATH, "")
        scriptPath = readField(element, TAG_SCRIPT_PATH, "")
        scriptArguments = readField(element, TAG_SCRIPT_ARGUMENTS)
        workingDirectory = readField(element, TAG_WORKING_DIRECTORY)
        environmentVariables = EnvironmentVariablesData.readExternal(element)
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
                    withExePath(interpreterPath)
                    withWorkDirectory(workingDirectory)
                    addParameters("-File", scriptPath)
                    scriptArguments?.let { addParameter(it) }
                    environmentVariables.configureCommandLine(this, true)
                }

                return ProcessHandlerFactory.getInstance().createColoredProcessHandler(commandLine).apply {
                    ProcessTerminatedListener.attach(this)
                }
            }
        }
    }

    companion object {

        private const val TAG_SCRIPT_PATH = "script_path"
        private const val TAG_SCRIPT_ARGUMENTS = "script_arguments"
        private const val TAG_WORKING_DIRECTORY = "script_working_directory"
        private const val TAG_INTERPRETER_PATH = "interpreter_path"
    }
}