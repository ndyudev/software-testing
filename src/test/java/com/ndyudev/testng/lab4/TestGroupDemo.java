package com.ndyudev.testng.lab4;
import org.testng.annotations.Test;

public class TestGroupDemo {

    // Case 1: Thuộc nhóm "chuc-nang" (Functional)
    @Test(groups = { "chuc-nang" })
    public void testLogin() {
        System.out.println("Test Login - Chạy chức năng");
    }

    // Case 2: Thuộc nhóm "giao-dien" (UI)
    @Test(groups = { "giao-dien" })
    public void testLogo() {
        System.out.println("Test Logo - Chạy giao diện");
    }

    // Case 3: Thuộc CẢ 2 nhóm (Vừa là chức năng, vừa quan trọng - Regression)
    @Test(groups = { "chuc-nang", "regression" })
    public void testRegister() {
        System.out.println("Test Register - Chạy chức năng & Hồi quy");
    }

    // Case 4: Một nhóm đặc biệt (Ví dụ test bị lỗi chưa fix xong)
    @Test(groups = { "broken" })
    public void testBiLoi() {
        System.out.println("Test này đang lỗi, đừng chạy nha");
    }
}