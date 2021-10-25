package exampleTests;

import com.example;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HogeESTest {

    @Test
    public void test01() {
        int input = 3;
        int result = abs(input);
        assertEquals(result, 3);
    }

    @Test
    public void test02() {
        int input = -5;
        int result = abs(input);
        assertEquals(result, 5);
    }

    @Test
    public void test03() {
        int input = 0;
        int result = closeToZero(input);
        assertEquals(result, 0);
    }

}