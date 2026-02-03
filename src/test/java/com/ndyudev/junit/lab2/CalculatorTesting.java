package com.ndyudev.junit.lab2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTesting {

    Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        // Test cộng
        assertEquals(10, calculator.add(5, 5));
        assertEquals(5, calculator.add(2, 3), "2 + 3 phải bằng 5");
    }

    @Test
    void testSubtract() {
        // Test trừ
        assertEquals(0, calculator.subtract(5, 5));

        // assertNotEquals
        assertNotEquals(1, calculator.subtract(5, 3), "5-3 = 2, không thể bằng 1");
    }

    @Test
    void testMultiply() {
        // Test nhân
        assertEquals(25, calculator.multiply(5, 5));

        int result = calculator.multiply(2, 3);
        assertTrue(result > 0, "Kết quả 2*3 phải là số dương");
    }

    @Test
    void testDivide() {
        // Test chia
        assertEquals(1, calculator.divide(5, 5));

        // assertFalse
        int result = calculator.divide(10, 2);
        assertFalse(result == 2, "10 chia 2 ra 5, không thể bằng 2");
    }

    @Test
    void testDivideByZero() {
        // Test ngoại lệ (Quan trọng nhất bài Lab)
        // Vì bên file Calculator mình đã thêm 'throw new ArithmeticException'
        // Nên hàm này sẽ chạy XANH (Pass)
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(1, 0);
        }, "Chia cho 0 phải ném ra lỗi ArithmeticException");
    }

    @Test
    void testAssertNull() {
        Calculator nullCalc = null;
        assertNull(nullCalc, "Đối tượng này phải là null");
        assertNotNull(calculator, "Đối tượng calc đã được new nên không thể null");
    }
}