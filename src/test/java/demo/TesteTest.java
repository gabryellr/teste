package demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TesteTest {
    @Test
    public void testPositive() {
        AA myClass = new AA();
        assertEquals("Positive", myClass.checkValue(5));
    }

    @Test
    public void testNonPositive() {
        AA myClass = new AA();
        assertEquals("Non-positive", myClass.checkValue(-1));
    }
}