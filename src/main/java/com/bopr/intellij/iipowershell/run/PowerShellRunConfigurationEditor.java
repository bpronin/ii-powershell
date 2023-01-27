package com.bopr.intellij.iipowershell.run;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.components.fields.ExtendableTextField;
import org.intellij.sdk.language.PowerShellFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

public class PowerShellRunConfigurationEditor extends SettingsEditor<PowerShellRunConfiguration> {

    private JPanel editorPanel;
    private TextFieldWithBrowseButton scriptEditor;
    private ExtendableTextField argumentsEditor;
    private TextFieldWithBrowseButton executableEditor;

    public PowerShellRunConfigurationEditor(Project project) {
        scriptEditor.addBrowseFolderListener("Select Script", "Select script", project,
                new FileChooserDescriptor(true, false, false, false, false, false)
                        .withFileFilter(virtualFile ->
                                Objects.equals(virtualFile.getExtension(), PowerShellFileType.getINSTANCE().getDefaultExtension())
                        )
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

    private void createUIComponents() {
//        scriptFileEditor = LabeledComponent<TextFieldWithBrowseButton>().apply {
//            component = TextFieldWithBrowseButton()
//        }
//        scriptArgumentsEditor = LabeledComponent<TextFieldWithBrowseButton>().apply {
//            component = TextFieldWithBrowseButton()
//        }
//        executableEditor = LabeledComponent<ExtendableTextField>().apply {
//            component = ExtendableTextField()
//        }
    }
}
