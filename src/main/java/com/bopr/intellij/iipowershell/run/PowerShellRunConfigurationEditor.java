package com.bopr.intellij.iipowershell.run;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.fields.ExtendableTextField;
import org.intellij.sdk.language.PowerShellFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static org.intellij.sdk.language.Resources.string;

/* NOTE: Do not convert into Kotlin. Form designer does not work well with it. */
public class PowerShellRunConfigurationEditor extends SettingsEditor<PowerShellRunConfiguration> {

//    private static final ResourceBundle R = ResourceBundle.getBundle("values.strings");

    private JPanel editorPanel;
    private TextFieldWithBrowseButton scriptEditor;
    private ExtendableTextField argumentsEditor;
    private TextFieldWithBrowseButton executableEditor;

    public PowerShellRunConfigurationEditor(Project project) {
        scriptEditor.addBrowseFolderListener(string("select_Script"), string("select_script_file"), project,
                new FileChooserDescriptor(true, false, false, false, false, false)
                        .withFileFilter(virtualFile ->
                                PowerShellFileType.POWERSHELL_FILE_EXTENSION.equals(virtualFile.getExtension())
                        )
        );
        executableEditor.addBrowseFolderListener(string("select_Executable"), string("select_powershell_executable"), project,
                new FileChooserDescriptor(true, false, false, false, false, false)
                        .withFileFilter(virtualFile -> "exe".equals(virtualFile.getExtension()))
        );
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return editorPanel;
    }

    @Override
    protected void resetEditorFrom(@NotNull PowerShellRunConfiguration configuration) {
        scriptEditor.setText(configuration.getOptions().getScriptPath());
        argumentsEditor.setText(configuration.getOptions().getScriptArguments());
        executableEditor.setText(configuration.getOptions().getExecutablePath());
    }

    @Override
    protected void applyEditorTo(@NotNull PowerShellRunConfiguration configuration) /*throws ConfigurationException*/ {
        configuration.getOptions().setScriptPath(scriptEditor.getText());
        configuration.getOptions().setScriptArguments(argumentsEditor.getText());
        configuration.getOptions().setExecutablePath(executableEditor.getText());
    }

}
