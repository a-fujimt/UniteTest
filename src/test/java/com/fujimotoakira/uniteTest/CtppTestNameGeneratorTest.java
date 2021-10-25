package com.fujimotoakira.uniteTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CtppTestNameGeneratorTest {

    @Test
    public void testGenerateName() {
        final String path = "/home/ctpp-d4j/patched_programs/Math/5/Arja/0/0/estests/seed1/evosuite-tests/HogeTest.java";
        assertEquals("Arja_0_seed1", CtppTestNameGenerator.generate(path));
    }

    @Test
    public void testGenerateName02() {
        final String path = "/home/ctpp-d4j/patched_programs/Math/5/Arja/0/0/estests/seed1/evosuite-tests/HogeTest.java";
        final String testName = "test01";
        assertEquals("test01_Arja_0_seed1", CtppTestNameGenerator.generate(path, testName));
    }

}