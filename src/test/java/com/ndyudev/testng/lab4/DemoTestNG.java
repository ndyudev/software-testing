package com.ndyudev.testng.lab4;

import org.testng.annotations.Test;

public class DemoTestNG {

    // Test case quan trọng, cần chạy mỗi lần build (Smoke Test)
    @Test(groups = "smoke")
    public void testLogin() {
        System.out.println(">> Đang chạy Login (Smoke Test)");
    }

    // Test case chức năng phụ, chạy khi cần kiểm tra kỹ (Regression)
    @Test(groups = "regression")
    public void testForgotPassword() {
        System.out.println(">> Đang chạy Quên mật khẩu (Regression Test)");
    }

    // Một test case thuộc cả 2 nhóm
    @Test(groups = {"smoke", "regression"})
    public void testHomePage() {
        System.out.println(">> Đang chạy Trang chủ (Smoke + Regression)");
    }
}