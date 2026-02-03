package com.ndyudev.testng.lab4;
import org.testng.annotations.*; // Import của TestNG
import static org.testng.Assert.*; // Assert của TestNG

public class TestNGDemo {

    @BeforeClass
    public void setUp() {
        System.out.println("--- Chạy 1 lần trước khi bắt đầu Class ---");
    }

    @Test(priority = 1) // TestNG cho phép đặt độ ưu tiên chạy
    public void testLogin() {
        System.out.println("Test Login");
        assertEquals(1 + 1, 2);
    }

    @Test(priority = 2)
    public void testAddToCart() {
        System.out.println("Test Add to Cart");
        assertTrue(true);
    }

    @AfterClass
    public void tearDown() {
        System.out.println("--- Dọn dẹp sau khi xong hết ---");
    }
}