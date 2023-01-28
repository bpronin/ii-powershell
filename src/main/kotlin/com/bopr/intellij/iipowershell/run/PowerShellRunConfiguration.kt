package com.bopr.intellij.iipowershell.run

import com.bopr.intellij.iipowershell.language.Resources.string
import com.bopr.intellij.iipowershell.util.findAbsolutePath
import com.bopr.intellij.iipowershell.util.readPath
import com.bopr.intellij.iipowershell.util.writePath
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
import com.intellij.util.io.exists
import org.jdom.Element
import kotlin.io.path.isExecutable

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

    override fun checkConfiguration() {
        val interpreter = findAbsolutePath(interpreterPath)
        when {
            !interpreter.exists() ->
                throw RuntimeConfigurationError(string("interpreter_not_found"))

            !interpreter.isExecutable() ->
                throw RuntimeConfigurationError(string("interpreter_should_be_executable"))

            !exists(scriptPath) && !exists(join(workingDirectory, scriptPath)) ->
                throw RuntimeConfigurationError(string("script_not_found"))

            workingDirectory.isNotBlank() && !exists(workingDirectory) ->
                throw RuntimeConfigurationError(string("working_dir_not_found"))
        }
    }

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
        return PtyCommandLine()
            .withExePath(interpreterPath)
            .withParameters("-File", scriptPath)
            .apply {
                scriptArguments?.let { args -> addParameter(args) }
                if (workingDirectory.isNotBlank()) withWorkDirectory(workingDirectory)
                environmentVariables.configureCommandLine(this, true)
            }
    }

    companion object {

        private const val TAG_SCRIPT_PATH = "script_path"
        private const val TAG_SCRIPT_ARGUMENTS = "script_arguments"
        private const val TAG_WORKING_DIRECTORY = "script_working_directory"
        private const val TAG_INTERPRETER_PATH = "interpreter_path"
    }
}