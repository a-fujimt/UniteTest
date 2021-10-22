package com.fujimotoakira.uniteTest.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCollection {

    public static List<Path> getTestsPath(Path path) {
        List<Path> tests = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(path)) {
            walk.filter(e -> Files.isRegularFile(e))
                    .filter(e -> e.toString().endsWith("Test.java"))
                    .forEach(tests::add);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return tests;
    }

    public static List<Path> getTestsPath(String path) {
        return getTestsPath(Path.of(path));
    }

    public static List<Path> getTestPath(Path path, String keyword) {
        List<Path> tests = getTestsPath(path).stream()
                .filter(e -> e.toString().contains(keyword))
                .collect(Collectors.toList());
        return tests;
    }

    public static List<Path> getTestsPath(String path, String keyword) {
        return getTestPath(Path.of(path), keyword);
    }

}
