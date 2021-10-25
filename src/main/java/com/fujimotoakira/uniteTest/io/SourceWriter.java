package com.fujimotoakira.uniteTest.io;

import org.eclipse.jdt.core.dom.ASTNode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class SourceWriter {

    public static void write(String path, ASTNode ast) throws IOException {
        String sourceCode = ast.toString();
        Files.writeString(Path.of(path), sourceCode, Charset.defaultCharset());
    }

}
