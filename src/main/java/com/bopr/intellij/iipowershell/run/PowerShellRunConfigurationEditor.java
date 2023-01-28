package com.bopr.intellij.iipowershell.run;

import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.fields.ExtendableTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static com.bopr.intellij.iipowershell.language.PowerShellFileType.POWERSHELL_FILE_EXTENSION;
import static com.bopr.intellij.iipowershell.language.Resources.string;

/* NOTE: Do not convert into Kotlin. Form designer does not work well with it. */
public class PowerShellRunConfigurationEditor extends SettingsEditor<PowerShellRunConfiguration> {

    private JPanel editorPanel;
    private TextFieldWithBrowseButton scriptEditor;
    private ExtendableTextField argumentsEditor;
    private TextFieldWithBrowseButton executableEditor;
    private TextFieldWithBrowseButton workingDirectory;
    private EnvironmentVariablesTextFieldWithBrowseButton environmentVariablesEditor;

    public PowerShellRunConfigurationEditor(Project project) {
        scriptEditor.addBrowseFolderListener(string("script"), string("choose_script"), project,
                new FileChooserDescriptor(true, false, false, false, false, false)
                        .withFileFilter(virtualFile -> POWERSHELL_FILE_EXTENSION.equals(virtualFile.getExtension()))
        );
        executableEditor.addBrowseFolderListener(string("interpreter"), string("choose_powershell_executable"), project,
                new FileChooserDescriptor(true, false, false, false, false, false)
                        .withFileFilter(virtualFile -> "exe".equals(virtualFile.getExtension()))
        );
        workingDirectory.addBrowseFolderListener(string("working_directory"), string("choose_script_working_directory"), project,
                new FileChooserDescriptor(false, true, false, false, false, false)
        );
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return editorPanel;
    }

    @Override
    protected void resetEditorFrom(@NotNull PowerShellRunConfiguration configuration) {
        scriptEditor.setText(configuration.getScriptPath());
        argumentsEditor.setText(configuration.getScriptArguments());
        executableEditor.setText(configuration.getInterpreterPath());
        workingDirectory.setText(configuration.getWorkingDirectory());
        environmentVariablesEditor.setData(configuration.getEnvironmentVariables());
    }

    @Override
    protected void applyEditorTo(@NotNull PowerShellRunConfiguration configuration) /*throws ConfigurationException*/ {
        configuration.setScriptPath(scriptEditor.getText());
        configuration.setScriptArguments(argumentsEditor.getText());
        configuration.setInterpreterPath(executableEditor.getText());
        configuration.setWorkingDirectory(workingDirectory.getText());
        configuration.setEnvironmentVariables(environmentVariablesEditor.getData());
    }

}
