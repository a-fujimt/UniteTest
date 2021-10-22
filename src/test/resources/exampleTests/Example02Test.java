package exampleTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Example02Test {

    @Test
    public void test01() {
        int input = 3;
        int result = closeToZero(input);
        assertEquals(result, 2);
    }

    @Test
    public void test02() {
        int input = -5;
        int result = closeToZero(input);
        assertEquals(result, -4);
    }

    @Test
    public void test03() {
        int input = 0;
        int result = closeToZero(input);
        assertEquals(result, 0);
    }

}