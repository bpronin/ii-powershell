package com.bopr.intellij.iipowershell.run;

import com.intellij.execution.configuration.EnvironmentVariablesTextFieldWithBrowseButton;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.components.fields.ExpandableTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.nio.file.Path;

import static com.bopr.intellij.iipowershell.language.PowerShellFileType.POWERSHELL_FILE_EXTENSION;
import static com.bopr.intellij.iipowershell.language.Resources.string;
import static com.bopr.intellij.iipowershell.util.IoUtilKt.detectPowerShellVersion;
import static java.lang.String.format;

/* NOTE: Do not convert into Kotlin. Form designer does not work well with it. */
public class PowerShellRunConfigurationEditor extends SettingsEditor<PowerShellRunConfiguration> {

    private JPanel editorPanel;
    private TextFieldWithBrowseButton scriptEditor;
    private ExpandableTextField argumentsEditor;
    private TextFieldWithBrowseButton interpreterEditor;
    private TextFieldWithBrowseButton workingDirectoryEditor;
    private EnvironmentVariablesTextFieldWithBrowseButton environmentVariablesEditor;
    private JLabel interpreterVersionLabel;

    public PowerShellRunConfigurationEditor(Project project) {
        interpreterEditor.addBrowseFolderListener(string("interpreter"), string("choose_powershell_executable"), project,
                new FileChooserDescriptor(true, false, false, false, false, false)
                        .withFileFilter(virtualFile -> "exe".equals(virtualFile.getExtension()))
        );
        interpreterEditor.getTextField().getDocument().addDocumentListener(new DocumentAdapter() {

            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                updateVersionLabel();
            }
        });

        scriptEditor.addBrowseFolderListener(string("script"), string("choose_script"), project,
                new FileChooserDescriptor(true, false, false, false, false, false)
                        .withFileFilter(virtualFile -> POWERSHELL_FILE_EXTENSION.equals(virtualFile.getExtension()))
        );

        workingDirectoryEditor.addBrowseFolderListener(string("working_directory"), string("choose_script_working_directory"), project,
                new FileChooserDescriptor(false, true, false, false, false, false)
        );

        updateVersionLabel();
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
        interpreterEditor.setText(configuration.getInterpreterPath().toString());
        workingDirectoryEditor.setText(configuration.getWorkingDirectory().toString());
        environmentVariablesEditor.setData(configuration.getEnvironmentVariables());
    }

    @Override
    protected void applyEditorTo(@NotNull PowerShellRunConfiguration configuration) /*throws ConfigurationException*/ {
        configuration.setScriptPath(Path.of(scriptEditor.getText()));
        configuration.setScriptArguments(argumentsEditor.getText());
        configuration.setInterpreterPath(Path.of(interpreterEditor.getText()));
        configuration.setWorkingDirectory(Path.of(workingDirectoryEditor.getText()));
        configuration.setEnvironmentVariables(environmentVariablesEditor.getData());
    }

    private void updateVersionLabel() {
        String version = detectPowerShellVersion(Path.of(interpreterEditor.getText()));
        String text = (version != null) ? format(string("powershell_version"), version) : string("not_a_powershell");
        interpreterVersionLabel.setText(text);
    }

}
