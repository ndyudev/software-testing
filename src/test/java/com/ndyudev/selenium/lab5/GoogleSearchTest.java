package com.ndyudev.selenium.lab5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class GoogleSearchTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testSearchOnGoogle() {
        driver.get("https://www.google.com");

        String pageTitle = driver.getTitle();
        assertEquals("Google", pageTitle, "Tiêu đề trang không đúng!");

        // 3. Tìm ô tìm kiếm (trên Google name="q")
        WebElement searchBox = driver.findElement(By.name("q"));

        // 4. Nhập từ khóa "Selenium Java" và nhấn Enter (submit)
        searchBox.sendKeys("Selenium Java");
        searchBox.submit();

        // 5. Kiểm tra kết quả: Tiêu đề trang mới phải chứa từ khóa vừa tìm
        // (Lưu ý: Google thường đổi tiêu đề thành "Từ khóa - Tìm trên Google")
        String resultPageTitle = driver.getTitle();
        assertTrue(resultPageTitle.startsWith("Selenium Java"), "Kết quả tìm kiếm không khớp!");
    }

    // Chạy sau mỗi Test Case: Tắt trình duyệt
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            // driver.quit() sẽ đóng toàn bộ cửa sổ trình duyệt và giải phóng RAM
            driver.quit();
        }
    }
}