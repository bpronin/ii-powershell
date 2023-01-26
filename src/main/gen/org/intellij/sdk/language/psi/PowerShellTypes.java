// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.intellij.sdk.language.psi.impl.*;

public interface PowerShellTypes {

  IElementType PROPERTY = new PowerShellElementType("PROPERTY");

  IElementType COMMENT = new PowerShellTokenType("COMMENT");
  IElementType CRLF = new PowerShellTokenType("CRLF");
  IElementType KEY = new PowerShellTokenType("KEY");
  IElementType SEPARATOR = new PowerShellTokenType("SEPARATOR");
  IElementType VALUE = new PowerShellTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new PowerShellPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
