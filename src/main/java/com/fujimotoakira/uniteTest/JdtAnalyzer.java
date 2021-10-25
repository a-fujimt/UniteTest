package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.FileEntity;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.*;

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

    public FileEntity getFileEntity() {
        return fileEntity;
    }

    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    public TypeDeclaration getTypeDeclaration() {
        final List types = compilationUnit.types();
        if (types.size() == 0)
            return null;
        return (TypeDeclaration) types.get(0);
    }

}
