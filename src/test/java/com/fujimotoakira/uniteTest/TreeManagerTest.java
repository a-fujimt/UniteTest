package com.fujimotoakira.uniteTest;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TreeManagerTest {

    @Test
    public void testUnite() throws IOException {
        // クラスローダでファイルの位置を指定する
        String path = JdtAnalyzerTest.class.getClassLoader().getResource("exampleTests").getPath();
        final TreeManager treeManager = new TreeManager(path);
        final CompilationUnit uniteCompilationUnit = treeManager.unite();
        assertEquals(15, ((TypeDeclaration) uniteCompilationUnit.types().get(0)).bodyDeclarations().size());
    }

}