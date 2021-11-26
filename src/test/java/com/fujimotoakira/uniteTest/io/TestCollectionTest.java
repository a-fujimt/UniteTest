package com.fujimotoakira.uniteTest.io;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCollectionTest {

    @Test
    public void testTestsList() {
        // クラスローダでファイルの位置を指定する
        String path = TestCollectionTest.class.getClassLoader().getResource("exampleTests").getPath();
        final List<Path> tests = TestCollection.getTestsPath(path);
        assertEquals(5, tests.size());
    }

    @Test
    public void testTestsListWithKeyword() {
        String path = TestCollectionTest.class.getClassLoader().getResource("exampleTests").getPath();
        String[] keywords = {"Keyword"};
        final List<Path> tests = TestCollection.getTestsPath(path, keywords);
        assertEquals(2, tests.size());
    }

    @Test
    public void testTestsListWithMultipleKeyword() {
        String path = TestCollectionTest.class.getClassLoader().getResource("exampleTests").getPath();
        String[] keywords = {"Keyword", "Example"};
        final List<Path> tests = TestCollection.getTestsPath(path, keywords);
        assertEquals(1, tests.size());
    }

    @Test
    public void testTestsListWithMultipleKeyword2() {
        String path = TestCollectionTest.class.getClassLoader().getResource("patched_programs").getPath();
        String[] keywords = {"ESTest", "seed1"};
        final List<Path> tests = TestCollection.getTestsPath(path, keywords);
        assertEquals(3, tests.size());
    }

}