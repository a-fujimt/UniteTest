package com.fujimotoakira.uniteTest.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileEntity {

    private final String path;
    private final String contents;

    public FileEntity(String path, String contents) {
        this.path = path;
        this.contents = contents;
    }

    public FileEntity(Path path) throws IOException {
        this.path = path.toString();
        this.contents = Files.readString(path);
    }

    public FileEntity(String path) throws IOException {
        this.path = path;
        this.contents = Files.readString(Path.of(path));
    }

    public String getPath() {
        return path;
    }

    public String getContents() {
        return contents;
    }
}
