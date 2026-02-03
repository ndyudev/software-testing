package com.ndyudev.junit.lab3;

import org.junit.jupiter.api.*;

public class JunitLifecycleTest {

    // @BeforeAll: Chạy 1 lần duy nhất trước TẤT CẢ các test case.
    // Lưu ý: Phải là static method.
    @BeforeAll
    static void initAll() {
        System.out.println("--- @BeforeAll: Kết nối Database, chuẩn bị môi trường ---");
    }

    // @AfterAll: Chạy 1 lần duy nhất sau khi TẤT CẢ test case đã chạy xong.
    // Lưu ý: Phải là static method.
    @AfterAll
    static void tearDownAll() {
        System.out.println("--- @AfterAll: Đóng kết nối Database, dọn dẹp tài nguyên ---");
    }

    // @BeforeEach: Chạy trước MỖI hàm test (@Test).
    @BeforeEach
    void init() {
        System.out.println("\n>> @BeforeEach: Khởi tạo đối tượng Calculator mới...");
    }

    // @AfterEach: Chạy sau MỖI hàm test (@Test).
    @AfterEach
    void tearDown() {
        System.out.println(">> @AfterEach: Reset dữ liệu...");
    }

    @Test
    void testCase1() {
        System.out.println("   -> Đang chạy Test Case 1");
    }

    @Test
    void testCase2() {
        System.out.println("   -> Đang chạy Test Case 2");
    }
}