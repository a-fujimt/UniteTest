package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.SourceWriter;
import org.eclipse.jdt.core.dom.ASTNode;

import java.io.IOException;

public class UniteTestMain {

    public static void main(String[] args) throws IOException {
        String path = args[0];
        String keyword = args.length >= 2 ? args[1] : null;
        new UniteTestMain().run(path, keyword);
    }

    public void run(String path, String keyword) throws IOException {
        final TreeManager treeManager = keyword == null ?
                new TreeManager(path) : new TreeManager(path, keyword);
        final ASTNode astNode = treeManager.unite();
        String testPath = treeManager.getFileEntities().get(0).getPath();
        SourceWriter.write(path, testPath, astNode);

        System.out.println("Done");
    }

}
