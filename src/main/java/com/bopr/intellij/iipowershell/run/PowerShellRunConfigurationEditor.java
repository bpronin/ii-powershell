package com.bopr.intellij.iipowershell.run;

import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class PowerShellRunConfigurationEditor extends SettingsEditor<PowerShellRunConfiguration> {

    private JPanel editorPanel;
    private com.intellij.openapi.ui.TextFieldWithBrowseButton scriptEditor;
    private com.intellij.ui.components.fields.ExtendableTextField argumentsEditor;
    private com.intellij.ui.components.fields.ExtendableTextField executableEditor;

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
