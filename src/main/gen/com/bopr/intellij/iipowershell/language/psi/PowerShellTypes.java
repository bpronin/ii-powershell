// This is a generated file. Not intended for manual editing.
package com.bopr.intellij.iipowershell.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.bopr.intellij.iipowershell.language.psi.impl.*;

public interface PowerShellTypes {

  IElementType ARRAY_TYPE_NAME = new PowerShellElementType("ARRAY_TYPE_NAME");
  IElementType ASSIGNMENT_OPERATOR = new PowerShellElementType("ASSIGNMENT_OPERATOR");
  IElementType COMMAND_PARAMETER = new PowerShellElementType("COMMAND_PARAMETER");
  IElementType COMMON_OPERATOR = new PowerShellElementType("COMMON_OPERATOR");
  IElementType COMPARISON_OPERATOR = new PowerShellElementType("COMPARISON_OPERATOR");
  IElementType DECIMAL_INTEGER_LITERAL = new PowerShellElementType("DECIMAL_INTEGER_LITERAL");
  IElementType DELIMITED_COMMENT = new PowerShellElementType("DELIMITED_COMMENT");
  IElementType FILE_REDIRECTION_OPERATOR = new PowerShellElementType("FILE_REDIRECTION_OPERATOR");
  IElementType FORMAT_OPERATOR = new PowerShellElementType("FORMAT_OPERATOR");
  IElementType GENERIC_TYPE_NAME = new PowerShellElementType("GENERIC_TYPE_NAME");
  IElementType HEXADECIMAL_INTEGER_LITERAL = new PowerShellElementType("HEXADECIMAL_INTEGER_LITERAL");
  IElementType KEYWORD = new PowerShellElementType("KEYWORD");
  IElementType MERGING_REDIRECTION_OPERATOR = new PowerShellElementType("MERGING_REDIRECTION_OPERATOR");
  IElementType REAL_LITERAL = new PowerShellElementType("REAL_LITERAL");
  IElementType SIGNATURE = new PowerShellElementType("SIGNATURE");
  IElementType SIGNATURE_BLOCK = new PowerShellElementType("SIGNATURE_BLOCK");
  IElementType SINGLE_LINE_COMMENT = new PowerShellElementType("SINGLE_LINE_COMMENT");
  IElementType STRING_LITERAL = new PowerShellElementType("STRING_LITERAL");
  IElementType TYPE_LITERAL = new PowerShellElementType("TYPE_LITERAL");
  IElementType TYPE_NAME = new PowerShellElementType("TYPE_NAME");
  IElementType VARIABLE = new PowerShellElementType("VARIABLE");

  IElementType BRACES = new PowerShellTokenType("BRACES");
  IElementType BRACKETS = new PowerShellTokenType("BRACKETS");
  IElementType COMMA = new PowerShellTokenType(",");
  IElementType DOT = new PowerShellTokenType(".");
  IElementType PARENTHESES = new PowerShellTokenType("PARENTHESES");
  IElementType SEMICOLON = new PowerShellTokenType(";");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARRAY_TYPE_NAME) {
        return new PowerShellArrayTypeNameImpl(node);
      }
      else if (type == ASSIGNMENT_OPERATOR) {
        return new PowerShellAssignmentOperatorImpl(node);
      }
      else if (type == COMMAND_PARAMETER) {
        return new PowerShellCommandParameterImpl(node);
      }
      else if (type == COMMON_OPERATOR) {
        return new PowerShellCommonOperatorImpl(node);
      }
      else if (type == COMPARISON_OPERATOR) {
        return new PowerShellComparisonOperatorImpl(node);
      }
      else if (type == DECIMAL_INTEGER_LITERAL) {
        return new PowerShellDecimalIntegerLiteralImpl(node);
      }
      else if (type == DELIMITED_COMMENT) {
        return new PowerShellDelimitedCommentImpl(node);
      }
      else if (type == FILE_REDIRECTION_OPERATOR) {
        return new PowerShellFileRedirectionOperatorImpl(node);
      }
      else if (type == FORMAT_OPERATOR) {
        return new PowerShellFormatOperatorImpl(node);
      }
      else if (type == GENERIC_TYPE_NAME) {
        return new PowerShellGenericTypeNameImpl(node);
      }
      else if (type == HEXADECIMAL_INTEGER_LITERAL) {
        return new PowerShellHexadecimalIntegerLiteralImpl(node);
      }
      else if (type == KEYWORD) {
        return new PowerShellKeywordImpl(node);
      }
      else if (type == MERGING_REDIRECTION_OPERATOR) {
        return new PowerShellMergingRedirectionOperatorImpl(node);
      }
      else if (type == REAL_LITERAL) {
        return new PowerShellRealLiteralImpl(node);
      }
      else if (type == SIGNATURE) {
        return new PowerShellSignatureImpl(node);
      }
      else if (type == SIGNATURE_BLOCK) {
        return new PowerShellSignatureBlockImpl(node);
      }
      else if (type == SINGLE_LINE_COMMENT) {
        return new PowerShellSingleLineCommentImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new PowerShellStringLiteralImpl(node);
      }
      else if (type == TYPE_LITERAL) {
        return new PowerShellTypeLiteralImpl(node);
      }
      else if (type == TYPE_NAME) {
        return new PowerShellTypeNameImpl(node);
      }
      else if (type == VARIABLE) {
        return new PowerShellVariableImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
