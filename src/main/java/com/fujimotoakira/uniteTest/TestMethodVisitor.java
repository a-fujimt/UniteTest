package com.fujimotoakira.uniteTest;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public class TestMethodVisitor extends ASTVisitor {

    private List<ASTNode> testMethods = new ArrayList<>();

    public List<ASTNode> getTestMethods() {
        return testMethods;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        testMethods.add(node);
        return super.visit(node);
    }

}
