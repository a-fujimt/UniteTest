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
        assertEquals(tests.size(), 5);
    }

    @Test
    public void testTestsListWithKeyword() {
        String path = TestCollectionTest.class.getClassLoader().getResource("exampleTests").getPath();
        final List<Path> tests = TestCollection.getTestsPath(path, "Keyword");
        assertEquals(tests.size(), 2);
    }

}