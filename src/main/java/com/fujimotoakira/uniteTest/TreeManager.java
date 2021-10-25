package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.FileEntity;
import com.fujimotoakira.uniteTest.io.TestCollection;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class TreeManager {

    private String path;
    private List<FileEntity> fileEntities = new ArrayList<>();
    private Map<String, List<ASTNode>> testMethodsMap = new LinkedHashMap<>();

    public TreeManager(String path) throws IOException {
        this.path = path;

        final List<Path> testsPath = TestCollection.getTestsPath(path);
        for (Path testPath: testsPath) {
            fileEntities.add(new FileEntity(testPath));
        }

        fileEntities.stream()
                .map(JdtAnalyzer::new)
                .forEach(e -> testMethodsMap.put(e.getFileEntity().getPath(), e.getTestMethods()));
    }

    public CompilationUnit unite() {
        //　テストを1つ適当に取ってくる
        JdtAnalyzer jdtAnalyzer = new JdtAnalyzer(fileEntities.get(0));
        CompilationUnit compilationUnit = jdtAnalyzer.getCompilationUnit();
        TypeDeclaration typeDeclaration = jdtAnalyzer.getTypeDeclaration();

        typeDeclaration.accept(new MethodDeleteVisitor()); // テストメソッドを一旦削除
        final JdtAnalyzer newJdtAnalyzer = new JdtAnalyzer(new FileEntity("", compilationUnit.toString()));
        CompilationUnit templateCompilationUnit = newJdtAnalyzer.getCompilationUnit();
        TypeDeclaration templateTypeDeclaration = newJdtAnalyzer.getTypeDeclaration();

        final Document document = new Document(templateCompilationUnit.toString());
        AST ast = templateTypeDeclaration.getAST();
        final ASTRewrite rewriter = ASTRewrite.create(ast);
        final ListRewrite listRewrite = rewriter.getListRewrite(templateTypeDeclaration, TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
        testMethodsMap.forEach((key, value) -> {
            value.forEach(m -> listRewrite.insertLast(m, null));
        });

        TextEdit edits = rewriter.rewriteAST(document, null);
        try {
            edits.apply(document);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        final CompilationUnit newCompilationUnit = new JdtAnalyzer(new FileEntity(path, document.get())).getCompilationUnit();
        return newCompilationUnit;
    }

    class MethodDeleteVisitor extends ASTVisitor {

        @Override
        public boolean visit(MethodDeclaration node) {
            node.delete();
            return super.visit(node);
        }

    }

}
