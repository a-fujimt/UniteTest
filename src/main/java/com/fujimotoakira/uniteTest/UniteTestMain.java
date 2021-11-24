package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.SourceWriter;
import java.util.Arrays;
import org.eclipse.jdt.core.dom.ASTNode;

import java.io.IOException;

public class UniteTestMain {

    public static void main(String[] args) throws IOException {
        String path = args[0];
        String[] keywords = args.length >= 2 ? Arrays.copyOfRange(args, 1, args.length - 1) : null;
        new UniteTestMain().run(path, keywords);
    }

    public void run(String path, String[] keywords) throws IOException {
        final TreeManager treeManager = keywords == null ?
                new TreeManager(path) : new TreeManager(path, keywords);
        final ASTNode astNode = treeManager.unite();
        String testPath = treeManager.getFileEntities().get(0).getPath();
        SourceWriter.write(path, testPath, astNode);

        System.out.println("Done");
    }

}
