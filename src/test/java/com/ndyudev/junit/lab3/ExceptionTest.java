package com.ndyudev.junit.lab3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    // 1. Test ngoại lệ chia cho 0 (ArithmeticException)
    @Test
    void testArithmeticException() {
        // Mong đợi: Khi chia 10 cho 0 thì phải ném ra lỗi ArithmeticException
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
    }

    // 2. Test ngoại lệ định dạng số (NumberFormatException)
    @Test
    void testNumberFormatException() {
        String notANumber = "abc"; // Đây là chữ, không phải số

        // Mong đợi: Khi ép kiểu chuỗi "abc" sang số Int -> Phải lỗi
        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt(notANumber);
        });
    }

    // 3. Test ngoại lệ Null (NullPointerException)
    @Test
    void testNullPointerException() {
        String str = null; // Chuỗi rỗng

        // Mong đợi: Khi thao tác trên đối tượng null -> Phải lỗi
        assertThrows(NullPointerException.class, () -> {
            str.length(); // Cố tình gọi hàm length() của null
        });
    }
}