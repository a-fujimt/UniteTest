package com.fujimotoakira.uniteTest.io;

import org.eclipse.jdt.core.dom.ASTNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SourceWriter {

    public static void write(String path, ASTNode ast) throws IOException {
        String sourceCode = ast.toString();
        Files.write(Paths.get(path), sourceCode.getBytes(StandardCharsets.UTF_8));
    }

    public static void write(String rootPath, String testPath, ASTNode ast) throws IOException {
        final String[] split = testPath.split("/");
        String filename = split[split.length - 1];
        String outputPath = rootPath + "/" + filename;
        write(outputPath, ast);
    }

}
