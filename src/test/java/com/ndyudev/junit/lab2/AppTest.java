package com.ndyudev.junit.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    App app = new App();

    @Test
    void testIsEventNumber_WithEvenNumber() {
        assertTrue(app.isEventNumber(2), "2 là số chẵn nên phải trả về true");
    }

    @Test
    void testIsEventNumber_WithOddNumber() {
        assertFalse(app.isEventNumber(3), "3 là số lẻ nên phải trả về false");
    }

    @Test
    void testIsEventNumber_WithZero() {
        assertTrue(app.isEventNumber(0), "0 là số chẵn nên phải trả về true");
    }

    @Test
    void testIsEventNumber_WithNegativeEvenNumber() {
        assertTrue(app.isEventNumber(-2), "-2 là số chẵn âm nên phải trả về true");
    }

    @Test
    void testIsEventNumber_WithNegativeOddNumber() {
        assertFalse(app.isEventNumber(-5), "-5 là số lẻ âm nên phải trả về false");
    }
}
