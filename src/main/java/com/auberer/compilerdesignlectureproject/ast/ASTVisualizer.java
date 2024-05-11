package com.auberer.compilerdesignlectureproject.ast;

import java.util.Stack;

public class ASTVisualizer extends ASTVisitor<String> {

  Stack<String> parentIds = new Stack<>();

  @Override
  public String visit(ASTNode node) {
    StringBuilder result = new StringBuilder();

    // Prepare strings
    String typeName = node.getClass().getTypeName();
    String codeLoc = node.getCodeLoc().toString();
    String nodeId = codeLoc + "_" + typeName;

    // Build result
    result.append(nodeId).append(" [label=\"").append(typeName).append("\"];\n");

    // Link parent node with the current one
    if (node.getParent() != null)
      result.append(" ").append(parentIds.peek()).append(" -> ").append(nodeId).append(";\n");

    // Push current node to the stack
    parentIds.push(nodeId);

    // Visit children
    for (ASTNode child : node.getChildren())
      result.append(visit(child));

    // Remove current node from the stack
    parentIds.pop();

    return result.toString();
  }
}
