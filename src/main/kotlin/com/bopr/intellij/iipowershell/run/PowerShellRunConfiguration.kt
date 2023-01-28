package com.bopr.intellij.iipowershell.run

import com.bopr.intellij.iipowershell.language.Resources.string
import com.bopr.intellij.iipowershell.util.*
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
import com.intellij.util.io.isDirectory
import org.jdom.Element
import java.nio.file.Path
import kotlin.io.path.pathString

class PowerShellRunConfiguration(
    project: Project, factory: ConfigurationFactory
) : LocatableConfigurationBase<Element>(
    project, factory, "PowerShell"
) {

    var interpreterPath: Path = path("pwsh.exe")
    var scriptPath: Path = EMPTY_PATH
    var scriptArguments: String? = null
    var workingDirectory: Path = path(project.basePath)
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
        when {
            !interpreterPath.canExecute() ->
                throw RuntimeConfigurationError(string("interpreter_not_found"))

            !(scriptPath.exists() || workingDirectory.resolve(scriptPath).exists()) ->
                throw RuntimeConfigurationError(string("script_not_found"))

            !(workingDirectory.isDirectory() && workingDirectory.exists()) ->
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
            .withExePath(interpreterPath.pathString)
            .withParameters("-File", scriptPath.pathString)
            .apply {
                scriptArguments?.let { args -> addParameter(args) }

                workingDirectory.pathString.run {
                    if (isNotBlank()) withWorkDirectory(this)
                }

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