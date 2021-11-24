package com.fujimotoakira.uniteTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

class UniteTestMainTest {

    @Test
    public void testParseArguments() throws CmdLineException {
        String[] arguments = {"path/to/hoge", "-o", "testName", "keyword1", "keyword2"};
        UniteTestMain main = new UniteTestMain();
        main.parseArguments(arguments);
        assertEquals("path/to/hoge", main.getPath());
        assertEquals("testName", main.getFilename());
        assertArrayEquals(new String[] {"keyword1", "keyword2"}, main.getOptions());
    }

    @Test
    public void testParseArguments2() throws CmdLineException {
        String[] arguments = {"path/to/hoge", "keyword1", "keyword2"};
        UniteTestMain main = new UniteTestMain();
        main.parseArguments(arguments);
        assertEquals("path/to/hoge", main.getPath());
        assertNull(main.getFilename());
        assertArrayEquals(new String[] {"keyword1", "keyword2"}, main.getOptions());
    }

    @Test
    public void testParseArguments3() throws CmdLineException {
        String[] arguments = {"path/to/hoge", "-o", "testName"};
        UniteTestMain main = new UniteTestMain();
        main.parseArguments(arguments);
        assertEquals("path/to/hoge", main.getPath());
        assertEquals("testName", main.getFilename());
        assertNull(main.getOptions());
    }

}