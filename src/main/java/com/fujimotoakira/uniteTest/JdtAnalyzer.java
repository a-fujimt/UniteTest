package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.FileEntity;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        Hashtable<String, String> options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
        parser.setCompilerOptions(options);
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
