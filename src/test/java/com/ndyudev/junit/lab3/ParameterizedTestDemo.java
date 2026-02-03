package com.ndyudev.junit.lab3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestDemo {

    class NumberUtils {
        public static boolean isPositive(int n) {
            return n > 0;
        }
    }

    // Cách 1: Dùng @ValueSource (Chỉ truyền được 1 tham số đầu vào)
    // Kịch bản: Kiểm tra các số này CÓ phải là số dương không?
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 100, 2024})
    void testIsPositive_True(int number) {
        Assertions.assertTrue(NumberUtils.isPositive(number), number + " là số dương");
    }

    // Kịch bản: Kiểm tra các số này KHÔNG phải là số dương?
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100, -9999})
    void testIsPositive_False(int number) {
        Assertions.assertFalse(NumberUtils.isPositive(number), number + " không phải số dương");
    }

    // Cách 2: Dùng @CsvSource (Xịn hơn - Truyền cả Input và Output mong đợi)
    // Cấu trúc: "Input, ExpectedResult"
    @ParameterizedTest
    @CsvSource({
            "10, true",   // Nhập 10 -> Mong đợi true
            "-5, false",  // Nhập -5 -> Mong đợi false
            "0, false",   // Nhập 0 -> Mong đợi false
            "1, true"     // Nhập 1 -> Mong đợi true
    })
    void testIsPositive_WithCsv(int input, boolean expectedResult) {
        boolean actualResult = NumberUtils.isPositive(input);

        // So sánh kết quả thực tế với kết quả mong đợi từ file CSV
        Assertions.assertEquals(expectedResult, actualResult);
    }
}