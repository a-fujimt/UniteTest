package com.fujimotoakira.uniteTest;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
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
        assertEquals(5, treeManager.getFileEntities().size());
    }

    @Test
    public void testUnite02() throws IOException {
        String path = JdtAnalyzerTest.class.getClassLoader().getResource("patched_programs").getPath();
        final TreeManager treeManager = new TreeManager(path);
        final CompilationUnit uniteCompilationUnit = treeManager.unite();
        final TypeDeclaration td = (TypeDeclaration) uniteCompilationUnit.types().get(0);
        final MethodDeclaration md = (MethodDeclaration) td.bodyDeclarations().get(0);
        assertEquals("test01_Arja_0_seed1", md.getName().getIdentifier());
        assertEquals(9, td.bodyDeclarations().size());
        assertEquals(4, uniteCompilationUnit.imports().size());
    }

}