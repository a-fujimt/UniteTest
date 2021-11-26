package com.fujimotoakira.uniteTest.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCollection {

    public static List<Path> getTestsPath(Path path) {
        List<Path> tests = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(path)) {
            walk.filter(Files::isRegularFile)
                    .filter(e -> e.toString().endsWith("Test.java"))
                    .sorted()
                    .forEach(tests::add);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return tests;
    }

    public static List<Path> getTestsPath(String path) {
        return getTestsPath(Paths.get(path));
    }

    public static List<Path> getTestPath(Path path, String[] keywords) {
        return getTestsPath(path).stream()
                .filter(e -> {
                    for (String keyword: keywords) {
                        if (!e.toString().contains(keyword))
                            return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    public static List<Path> getTestsPath(String path, String[] keywords) {
        return getTestPath(Paths.get(path), keywords);
    }

}
