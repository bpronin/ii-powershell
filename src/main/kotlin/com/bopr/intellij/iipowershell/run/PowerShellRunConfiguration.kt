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
import com.intellij.openapi.util.io.FileUtil.*
import com.intellij.openapi.util.text.StringUtilRt.*
import org.jdom.Element

class PowerShellRunConfiguration(
    project: Project, factory: ConfigurationFactory
) : LocatableConfigurationBase<Element>(
    project, factory, "PowerShell"
) {

    var interpreterPath = "pwsh.exe"
    var workingDirectory = ""
    var scriptPath = ""
    var scriptArguments: String? = null
    var environmentVariables: EnvironmentVariablesData = EnvironmentVariablesData.DEFAULT

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        writePath(element, TAG_INTERPRETER_PATH, interpreterPath)
        writePath(element, TAG_SCRIPT_PATH, scriptPath)
        writeField(element, TAG_SCRIPT_ARGUMENTS, scriptArguments)
        writePath(element, TAG_WORKING_DIRECTORY, workingDirectory)
        environmentVariables.writeExternal(element)
    }

    override fun readExternal(element: Element) {
        super.readExternal(element)
        interpreterPath = readPath(element, TAG_INTERPRETER_PATH)
        scriptPath = readPath(element, TAG_SCRIPT_PATH)
        scriptArguments = readField(element, TAG_SCRIPT_ARGUMENTS)
        workingDirectory = readPath(element, TAG_WORKING_DIRECTORY)
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
                return ProcessHandlerFactory.getInstance()
                    .createColoredProcessHandler(buildCommandLine()).also {
                        ProcessTerminatedListener.attach(it)
                    }
            }
        }
    }

    private fun buildCommandLine(): GeneralCommandLine {
        return PtyCommandLine().apply {
            withExePath(interpreterPath)
            addParameters("-File", scriptPath)
            scriptArguments?.let { addParameter(it) }
            if (workingDirectory.isNotEmpty()) {
                withWorkDirectory(workingDirectory)
            }
            environmentVariables.configureCommandLine(this, true)
        }
    }

    private fun writePath(element: Element, fieldName: String, path: String) {
        val systemIndependentPath = toSystemIndependentName(path)
        val isSystemIndependentPath = (systemIndependentPath == path).toString()
        writeField(element, TAG_PREFIX + fieldName, isSystemIndependentPath)
        writeField(element, fieldName, systemIndependentPath)
    }

    private fun readPath(element: Element, fieldName: String): String {
        val systemIndependentPath = readField(element, fieldName, "")
        val isSystemIndependentPath = readField(element, TAG_PREFIX + fieldName).toBoolean()
        return if (isSystemIndependentPath) systemIndependentPath else toSystemDependentName(systemIndependentPath)
    }

    companion object {

        private const val TAG_PREFIX = "independent_"
        private const val TAG_SCRIPT_PATH = "script_path"
        private const val TAG_SCRIPT_ARGUMENTS = "script_arguments"
        private const val TAG_WORKING_DIRECTORY = "script_working_directory"
        private const val TAG_INTERPRETER_PATH = "interpreter_path"
    }
}