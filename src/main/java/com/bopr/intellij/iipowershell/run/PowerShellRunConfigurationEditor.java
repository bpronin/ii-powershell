package com.bopr.intellij.iipowershell.run;

import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.fields.ExtendableTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.file.Path;

import static com.bopr.intellij.iipowershell.language.PowerShellFileType.POWERSHELL_FILE_EXTENSION;
import static com.bopr.intellij.iipowershell.language.Resources.string;

/* NOTE: Do not convert into Kotlin. Form designer does not work well with it. */
public class PowerShellRunConfigurationEditor extends SettingsEditor<PowerShellRunConfiguration> {

    private JPanel editorPanel;
    private TextFieldWithBrowseButton scriptEditor;
    private ExtendableTextField argumentsEditor;
    private TextFieldWithBrowseButton executableEditor;
    private TextFieldWithBrowseButton workingDirectoryEditor;
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
        workingDirectoryEditor.addBrowseFolderListener(string("working_directory"), string("choose_script_working_directory"), project,
                new FileChooserDescriptor(false, true, false, false, false, false)
        );

//        MacrosDialog.addMacroSupport(workingDirectoryEditor, MacrosDialog.Filters.ALL, () -> false);
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return editorPanel;
    }

    @Override
    protected void resetEditorFrom(@NotNull PowerShellRunConfiguration configuration) {
        scriptEditor.setText(configuration.getScriptPath().toString());
        argumentsEditor.setText(configuration.getScriptArguments());
        executableEditor.setText(configuration.getInterpreterPath().toString());
        workingDirectoryEditor.setText(configuration.getWorkingDirectory().toString());
        environmentVariablesEditor.setData(configuration.getEnvironmentVariables());
    }

    @Override
    protected void applyEditorTo(@NotNull PowerShellRunConfiguration configuration) /*throws ConfigurationException*/ {
        configuration.setScriptPath(Path.of(scriptEditor.getText()));
        configuration.setScriptArguments(argumentsEditor.getText());
        configuration.setInterpreterPath(Path.of(executableEditor.getText()));
        configuration.setWorkingDirectory(Path.of(workingDirectoryEditor.getText()));
        configuration.setEnvironmentVariables(environmentVariablesEditor.getData());
    }

}
