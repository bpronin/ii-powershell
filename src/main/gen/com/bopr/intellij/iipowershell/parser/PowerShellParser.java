// This is a generated file. Not intended for manual editing.
package com.bopr.intellij.iipowershell.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.bopr.intellij.iipowershell.language.psi.PowerShellTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class PowerShellParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return input(b, l + 1);
  }

  /* ********************************************************** */
  // type-name '['
  public static boolean array_type_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "array_type_name")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARRAY_TYPE_NAME, "<array type name>");
    r = type_name(b, l + 1);
    r = r && consumeToken(b, "[");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '='|'+='|'*='|'/='|'%='|'regexp:[-\u2013\u2014\u2015]='
  public static boolean assignment_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignment_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_OPERATOR, "<assignment operator>");
    r = consumeToken(b, "=");
    if (!r) r = consumeToken(b, "+=");
    if (!r) r = consumeToken(b, "*=");
    if (!r) r = consumeToken(b, "/=");
    if (!r) r = consumeToken(b, "%=");
    if (!r) r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015]=");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:[-\u2013\u2014\u2015]\w?[\w?]+'
  public static boolean command_parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_parameter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMAND_PARAMETER, "<command parameter>");
    r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015]\\w?[\\w?]+");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // single-line-comment
  // //  | requires-comment
  //   | delimited-comment
  static boolean comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment")) return false;
    boolean r;
    r = single_line_comment(b, l + 1);
    if (!r) r = delimited_comment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // '{'|'@{'|'}'|'('|'$('|'@('|')'|'['|']'|'..'|'::'|'+'|'*'|'/'|'%'|'!'|'++'|'&'|'.'|','|'&&'|'||'|'|'|';'|
  //     'regexp:[-\u2013\u2014\u2015]'|
  //     'regexp:[-\u2013\u2014\u2015]{2}'|
  //     'regexp:[-\u2013\u2014\u2015](?i)(shl|shr)'|
  //     'regexp:[-\u2013\u2014\u2015](?i)b?(and|or|xor|not)'
  public static boolean common_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "common_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMON_OPERATOR, "<common operator>");
    r = consumeToken(b, "{");
    if (!r) r = consumeToken(b, "@{");
    if (!r) r = consumeToken(b, "}");
    if (!r) r = consumeToken(b, "(");
    if (!r) r = consumeToken(b, "$(");
    if (!r) r = consumeToken(b, "@(");
    if (!r) r = consumeToken(b, ")");
    if (!r) r = consumeToken(b, "[");
    if (!r) r = consumeToken(b, "]");
    if (!r) r = consumeToken(b, "..");
    if (!r) r = consumeToken(b, "::");
    if (!r) r = consumeToken(b, "+");
    if (!r) r = consumeToken(b, "*");
    if (!r) r = consumeToken(b, "/");
    if (!r) r = consumeToken(b, "%");
    if (!r) r = consumeToken(b, "!");
    if (!r) r = consumeToken(b, "++");
    if (!r) r = consumeToken(b, "&");
    if (!r) r = consumeToken(b, ".");
    if (!r) r = consumeToken(b, ",");
    if (!r) r = consumeToken(b, "&&");
    if (!r) r = consumeToken(b, "||");
    if (!r) r = consumeToken(b, "|");
    if (!r) r = consumeToken(b, ";");
    if (!r) r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015]");
    if (!r) r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015]{2}");
    if (!r) r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015](?i)(shl|shr)");
    if (!r) r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015](?i)b?(and|or|xor|not)");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:[-\u2013\u2014\u2015](?i)(in|notin|isnot|is|as)' |
  //     'regexp:[-\u2013\u2014\u2015](?i)[ic]?(eq|ne|gt|lt|le|ge|match|notmatch|replace|like|notlike|contains|notcontains|split|join)'
  public static boolean comparison_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comparison_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARISON_OPERATOR, "<comparison operator>");
    r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015](?i)(in|notin|isnot|is|as)");
    if (!r) r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015](?i)[ic]?(eq|ne|gt|lt|le|ge|match|notmatch|replace|like|notlike|contains|notcontains|split|join)");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:\d+(?i)[ld]?(kb|mb|gb|tb|pb)?'
  public static boolean decimal_integer_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "decimal_integer_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DECIMAL_INTEGER_LITERAL, "<decimal integer literal>");
    r = consumeToken(b, "regexp:\\d+(?i)[ld]?(kb|mb|gb|tb|pb)?");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:<#(?s).*#>'
  public static boolean delimited_comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "delimited_comment")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DELIMITED_COMMENT, "<delimited comment>");
    r = consumeToken(b, "regexp:<#(?s).*#>");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ','
  static boolean dimension(PsiBuilder b, int l) {
    return consumeToken(b, ",");
  }

  /* ********************************************************** */
  // '>'|'>>'|'2>'|'2>>'|'3>'|'3>>'|'4>'|'4>>'|'5>'|'5>>'|'6>'|'6>>'|'*>'|'*>>'|'<'
  public static boolean file_redirection_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "file_redirection_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FILE_REDIRECTION_OPERATOR, "<file redirection operator>");
    r = consumeToken(b, ">");
    if (!r) r = consumeToken(b, ">>");
    if (!r) r = consumeToken(b, "2>");
    if (!r) r = consumeToken(b, "2>>");
    if (!r) r = consumeToken(b, "3>");
    if (!r) r = consumeToken(b, "3>>");
    if (!r) r = consumeToken(b, "4>");
    if (!r) r = consumeToken(b, "4>>");
    if (!r) r = consumeToken(b, "5>");
    if (!r) r = consumeToken(b, "5>>");
    if (!r) r = consumeToken(b, "6>");
    if (!r) r = consumeToken(b, "6>>");
    if (!r) r = consumeToken(b, "*>");
    if (!r) r = consumeToken(b, "*>>");
    if (!r) r = consumeToken(b, "<");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:[-\u2013\u2014\u2015](?i)f'
  public static boolean format_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "format_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FORMAT_OPERATOR, "<format operator>");
    r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015](?i)f");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // type-spec new-lines? (',' new-lines? type-spec)*
  static boolean generic_type_arguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "generic_type_arguments")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_spec(b, l + 1);
    r = r && generic_type_arguments_1(b, l + 1);
    r = r && generic_type_arguments_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // new-lines?
  private static boolean generic_type_arguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "generic_type_arguments_1")) return false;
    new_lines(b, l + 1);
    return true;
  }

  // (',' new-lines? type-spec)*
  private static boolean generic_type_arguments_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "generic_type_arguments_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!generic_type_arguments_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "generic_type_arguments_2", c)) break;
    }
    return true;
  }

  // ',' new-lines? type-spec
  private static boolean generic_type_arguments_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "generic_type_arguments_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ",");
    r = r && generic_type_arguments_2_0_1(b, l + 1);
    r = r && type_spec(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // new-lines?
  private static boolean generic_type_arguments_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "generic_type_arguments_2_0_1")) return false;
    new_lines(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // type-name '['
  public static boolean generic_type_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "generic_type_name")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GENERIC_TYPE_NAME, "<generic type name>");
    r = type_name(b, l + 1);
    r = r && consumeToken(b, "[");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:0x(?i)[\da-f]+[ld]?(kb|mb|gb|tb|pb)?'
  public static boolean hexadecimal_integer_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "hexadecimal_integer_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, HEXADECIMAL_INTEGER_LITERAL, "<hexadecimal integer literal>");
    r = consumeToken(b, "regexp:0x(?i)[\\da-f]+[ld]?(kb|mb|gb|tb|pb)?");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // input-element* signature-block?
  static boolean input(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "input")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = input_0(b, l + 1);
    r = r && input_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // input-element*
  private static boolean input_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "input_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!input_element(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "input_0", c)) break;
    }
    return true;
  }

  // signature-block?
  private static boolean input_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "input_1")) return false;
    signature_block(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // token
  //   | comment
  //   | whitespace
  static boolean input_element(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "input_element")) return false;
    boolean r;
    r = token(b, l + 1);
    if (!r) r = comment(b, l + 1);
    if (!r) r = whitespace(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // sign? (decimal-integer-literal | hexadecimal-integer-literal)
  static boolean integer_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integer_literal")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = integer_literal_0(b, l + 1);
    r = r && integer_literal_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // sign?
  private static boolean integer_literal_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integer_literal_0")) return false;
    sign(b, l + 1);
    return true;
  }

  // decimal-integer-literal | hexadecimal-integer-literal
  private static boolean integer_literal_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "integer_literal_1")) return false;
    boolean r;
    r = decimal_integer_literal(b, l + 1);
    if (!r) r = hexadecimal_integer_literal(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // 'begin'|'break'|'catch'|'class'|'continue'|'data'|'define'|'do'|'dynamicparam'|'else'|'elseif'|'end'|'exit'|
  //     'filter'|'finally'|'for'|'foreach'|'from'|'function'|'if'|'in'|'inlinescript'|'parallel'|'param'|'process'|
  //     'return'|'switch'|'throw'|'trap'|'try'|'until'|'using'|'var'|'while'|'workflow'
  public static boolean keyword(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyword")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KEYWORD, "<keyword>");
    r = consumeToken(b, "begin");
    if (!r) r = consumeToken(b, "break");
    if (!r) r = consumeToken(b, "catch");
    if (!r) r = consumeToken(b, "class");
    if (!r) r = consumeToken(b, "continue");
    if (!r) r = consumeToken(b, "data");
    if (!r) r = consumeToken(b, "define");
    if (!r) r = consumeToken(b, "do");
    if (!r) r = consumeToken(b, "dynamicparam");
    if (!r) r = consumeToken(b, "else");
    if (!r) r = consumeToken(b, "elseif");
    if (!r) r = consumeToken(b, "end");
    if (!r) r = consumeToken(b, "exit");
    if (!r) r = consumeToken(b, "filter");
    if (!r) r = consumeToken(b, "finally");
    if (!r) r = consumeToken(b, "for");
    if (!r) r = consumeToken(b, "foreach");
    if (!r) r = consumeToken(b, "from");
    if (!r) r = consumeToken(b, "function");
    if (!r) r = consumeToken(b, "if");
    if (!r) r = consumeToken(b, "in");
    if (!r) r = consumeToken(b, "inlinescript");
    if (!r) r = consumeToken(b, "parallel");
    if (!r) r = consumeToken(b, "param");
    if (!r) r = consumeToken(b, "process");
    if (!r) r = consumeToken(b, "return");
    if (!r) r = consumeToken(b, "switch");
    if (!r) r = consumeToken(b, "throw");
    if (!r) r = consumeToken(b, "trap");
    if (!r) r = consumeToken(b, "try");
    if (!r) r = consumeToken(b, "until");
    if (!r) r = consumeToken(b, "using");
    if (!r) r = consumeToken(b, "var");
    if (!r) r = consumeToken(b, "while");
    if (!r) r = consumeToken(b, "workflow");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // integer-literal
  //   | real-literal
  //   | string-literal
  static boolean literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal")) return false;
    boolean r;
    r = integer_literal(b, l + 1);
    if (!r) r = real_literal(b, l + 1);
    if (!r) r = string_literal(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // '*>&1'|'2>&1'|'3>&1'|'4>&1'|'5>&1'|'6>&1'|'*>&2'|'1>&2'|'3>&2'|'4>&2'|'5>&2'|'6>&2'
  public static boolean merging_redirection_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "merging_redirection_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MERGING_REDIRECTION_OPERATOR, "<merging redirection operator>");
    r = consumeToken(b, "*>&1");
    if (!r) r = consumeToken(b, "2>&1");
    if (!r) r = consumeToken(b, "3>&1");
    if (!r) r = consumeToken(b, "4>&1");
    if (!r) r = consumeToken(b, "5>&1");
    if (!r) r = consumeToken(b, "6>&1");
    if (!r) r = consumeToken(b, "*>&2");
    if (!r) r = consumeToken(b, "1>&2");
    if (!r) r = consumeToken(b, "3>&2");
    if (!r) r = consumeToken(b, "4>&2");
    if (!r) r = consumeToken(b, "5>&2");
    if (!r) r = consumeToken(b, "6>&2");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:[\r\n]|\r\n'
  static boolean new_line_character(PsiBuilder b, int l) {
    return consumeToken(b, "regexp:[\\r\\n]|\\r\\n");
  }

  /* ********************************************************** */
  // new-line-character*
  static boolean new_lines(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "new_lines")) return false;
    while (true) {
      int c = current_position_(b);
      if (!new_line_character(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "new_lines", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // common-operator
  //   | assignment-operator
  //   | merging-redirection-operator
  //   | file-redirection-operator
  //   | comparison-operator
  //   | format-operator
  static boolean operator_or_punctuator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "operator_or_punctuator")) return false;
    boolean r;
    r = common_operator(b, l + 1);
    if (!r) r = assignment_operator(b, l + 1);
    if (!r) r = merging_redirection_operator(b, l + 1);
    if (!r) r = file_redirection_operator(b, l + 1);
    if (!r) r = comparison_operator(b, l + 1);
    if (!r) r = format_operator(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // sign? ('regexp:(?i)(\d*\.\d+e?|\d+e)\d+[ld]?(kb|mb|gb|tb|pb)?')
  public static boolean real_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "real_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REAL_LITERAL, "<real literal>");
    r = real_literal_0(b, l + 1);
    r = r && real_literal_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // sign?
  private static boolean real_literal_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "real_literal_0")) return false;
    sign(b, l + 1);
    return true;
  }

  // ('regexp:(?i)(\d*\.\d+e?|\d+e)\d+[ld]?(kb|mb|gb|tb|pb)?')
  private static boolean real_literal_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "real_literal_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "regexp:(?i)(\\d*\\.\\d+e?|\\d+e)\\d+[ld]?(kb|mb|gb|tb|pb)?");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '+' | 'regexp:[-\u2013\u2014\u2015]'
  static boolean sign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sign")) return false;
    boolean r;
    r = consumeToken(b, "+");
    if (!r) r = consumeToken(b, "regexp:[-\\u2013\\u2014\\u2015]");
    return r;
  }

  /* ********************************************************** */
  // 'regexp:#.*'*
  public static boolean signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature")) return false;
    Marker m = enter_section_(b, l, _NONE_, SIGNATURE, "<signature>");
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, "regexp:#.*")) break;
      if (!empty_element_parsed_guard_(b, "signature", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // '# SIG # Begin signature block' signature '# SIG # End signature block'
  public static boolean signature_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "signature_block")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIGNATURE_BLOCK, "<signature block>");
    r = consumeToken(b, "# SIG # Begin signature block");
    r = r && signature(b, l + 1);
    r = r && consumeToken(b, "# SIG # End signature block");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:#.*'
  public static boolean single_line_comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "single_line_comment")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SINGLE_LINE_COMMENT, "<single line comment>");
    r = consumeToken(b, "regexp:#.*");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:["\u201C\u201D\u201E]([^$`"\u201C\u201D\u201E]|""|)*\$?["\u201C\u201D\u201E]'
  public static boolean string_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = consumeToken(b, "regexp:[\"\\u201C\\u201D\\u201E]([^$`\"\\u201C\\u201D\\u201E]|\"\"|)*\\$?[\"\\u201C\\u201D\\u201E]");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // keyword
  //   | variable
  // //  | command
  //   | command-parameter  //interferes with negative number literals
  // //  | command-argument-token
  //   | literal
  //   | type-literal
  //   | operator-or-punctuator
  static boolean token(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "token")) return false;
    boolean r;
    r = keyword(b, l + 1);
    if (!r) r = variable(b, l + 1);
    if (!r) r = command_parameter(b, l + 1);
    if (!r) r = literal(b, l + 1);
    if (!r) r = type_literal(b, l + 1);
    if (!r) r = operator_or_punctuator(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:[\p{Lu}\p{Ll}\p{Lt}\p{Lm}\p{Lo}\p{Nd}_]+'
  static boolean type_characters(PsiBuilder b, int l) {
    return consumeToken(b, "regexp:[\\p{Lu}\\p{Ll}\\p{Lt}\\p{Lm}\\p{Lo}\\p{Nd}_]+");
  }

  /* ********************************************************** */
  // type-characters
  static boolean type_identifier(PsiBuilder b, int l) {
    return type_characters(b, l + 1);
  }

  /* ********************************************************** */
  // '[' type-spec ']'
  public static boolean type_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_LITERAL, "<type literal>");
    r = consumeToken(b, "[");
    r = r && type_spec(b, l + 1);
    r = r && consumeToken(b, "]");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // type-identifier ('.' type-identifier)*
  public static boolean type_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_name")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_NAME, "<type name>");
    r = type_identifier(b, l + 1);
    r = r && type_name_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ('.' type-identifier)*
  private static boolean type_name_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_name_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!type_name_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "type_name_1", c)) break;
    }
    return true;
  }

  // '.' type-identifier
  private static boolean type_name_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_name_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ".");
    r = r && type_identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // array-type-name new-lines? dimension* ']'
  //   | generic-type-name new-lines? generic-type-arguments ']'
  //   | type-name
  static boolean type_spec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_spec")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = type_spec_0(b, l + 1);
    if (!r) r = type_spec_1(b, l + 1);
    if (!r) r = type_name(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // array-type-name new-lines? dimension* ']'
  private static boolean type_spec_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_spec_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = array_type_name(b, l + 1);
    r = r && type_spec_0_1(b, l + 1);
    r = r && type_spec_0_2(b, l + 1);
    r = r && consumeToken(b, "]");
    exit_section_(b, m, null, r);
    return r;
  }

  // new-lines?
  private static boolean type_spec_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_spec_0_1")) return false;
    new_lines(b, l + 1);
    return true;
  }

  // dimension*
  private static boolean type_spec_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_spec_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!dimension(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "type_spec_0_2", c)) break;
    }
    return true;
  }

  // generic-type-name new-lines? generic-type-arguments ']'
  private static boolean type_spec_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_spec_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = generic_type_name(b, l + 1);
    r = r && type_spec_1_1(b, l + 1);
    r = r && generic_type_arguments(b, l + 1);
    r = r && consumeToken(b, "]");
    exit_section_(b, m, null, r);
    return r;
  }

  // new-lines?
  private static boolean type_spec_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "type_spec_1_1")) return false;
    new_lines(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // 'regexp:\$\$|\$\?|\$\^|\$_'
  //   | 'regexp:[$@][\p{Lu}\p{Ll}\p{Lt}\p{Lm}\p{Lo}\p{Nd}_?:]+'
  //   | 'regexp:\$\{[^}]+}'
  public static boolean variable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variable")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE, "<variable>");
    r = consumeToken(b, "regexp:\\$\\$|\\$\\?|\\$\\^|\\$_");
    if (!r) r = consumeToken(b, "regexp:[$@][\\p{Lu}\\p{Ll}\\p{Lt}\\p{Lm}\\p{Lo}\\p{Nd}_?:]+");
    if (!r) r = consumeToken(b, "regexp:\\$\\{[^}]+}");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'regexp:[\p{Zs}\p{Zl}\p{Zp}\u0009\u000B\u000C]'
  //   | '`' new-line-character
  static boolean whitespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whitespace")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "regexp:[\\p{Zs}\\p{Zl}\\p{Zp}\\u0009\\u000B\\u000C]");
    if (!r) r = whitespace_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '`' new-line-character
  private static boolean whitespace_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whitespace_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "`");
    r = r && new_line_character(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
