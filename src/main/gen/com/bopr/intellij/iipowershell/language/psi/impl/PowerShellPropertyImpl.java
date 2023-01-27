// This is a generated file. Not intended for manual editing.
package com.bopr.intellij.iipowershell.language.psi.impl;

import com.bopr.intellij.iipowershell.psi.PowerShellProperty;
import com.bopr.intellij.iipowershell.psi.PowerShellVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class PowerShellPropertyImpl extends ASTWrapperPsiElement implements PowerShellProperty {

    public PowerShellPropertyImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull PowerShellVisitor visitor) {
        visitor.visitProperty(this);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PowerShellVisitor) accept((PowerShellVisitor) visitor);
        else super.accept(visitor);
    }

}
