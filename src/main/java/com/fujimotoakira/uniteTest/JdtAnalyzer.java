package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.FileEntity;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.List;

public class JdtAnalyzer {

    private final FileEntity fileEntity;
    private final CompilationUnit compilationUnit;

    public JdtAnalyzer(FileEntity entity) {
        this.fileEntity = entity;
        this.compilationUnit = createTree();
    }

    public List<ASTNode> getTestMethods() {
        TestMethodVisitor visitor = new TestMethodVisitor();
        compilationUnit.accept(visitor);
        return visitor.getTestMethods();
    }

    private CompilationUnit createTree() {
        ASTParser parser = ASTParser.newParser(AST.JLS11);
        parser.setSource(fileEntity.getContents().toCharArray());
        return (CompilationUnit) parser.createAST(new NullProgressMonitor());
    }

    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

}
