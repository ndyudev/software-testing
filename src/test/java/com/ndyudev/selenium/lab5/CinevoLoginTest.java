package com.ndyudev.selenium.lab5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CinevoLoginTest {

    WebDriver driver;

    String loginUrl = "http://localhost:8080/cinevo-web/cinevo/user?tab=login";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(loginUrl);
    }

    // CASE 1: Đăng nhập thành công với data bro cung cấp
    @Test
    public void testLoginSuccess() throws InterruptedException {
        System.out.println("--- Test Login Thành Công ---");

        // 1. Nhập Email (Dựa vào name="email" trong JSP)
        WebElement txtEmail = driver.findElement(By.name("email"));
        txtEmail.sendKeys("toanpham@gmail.com");

        // 2. Nhập Password (Dựa vào name="password" trong JSP)
        WebElement txtPass = driver.findElement(By.name("password"));
        txtPass.sendKeys("pass005");

        // 3. Click nút Đăng nhập (Dựa vào class hoặc type=submit)
        // Dùng xpath này là chuẩn nhất cho cái form của bro
        WebElement btnLogin = driver.findElement(By.xpath("//button[@type='submit']"));
        btnLogin.click();

        // 4. Assert (Kiểm tra)
        // Logic: Nếu login thành công thường sẽ chuyển về trang chủ hoặc trang index
        Thread.sleep(2000); // Chờ xíu để web chuyển trang
        String currentUrl = driver.getCurrentUrl();

        // Kiểm tra URL không còn chứa chữ "login" nữa
        Assert.assertFalse(currentUrl.contains("login"), "Vẫn còn ở trang login, chưa chuyển hướng!");
        System.out.println("Login thành công, URL hiện tại: " + currentUrl);
    }

    // CASE 2: Đăng nhập sai để kiểm tra header có thay đổi không.
    @Test
    public void testLoginFailed_RedirectToHome() throws InterruptedException {
        System.out.println("--- Test Login Thất Bại (Logic hiện tại: Về Home, không Alert) ---");

        // 1. Nhập Email đúng
        driver.findElement(By.name("email")).sendKeys("toanpham@gmail.com");

        // 2. Nhập Password SAI
        driver.findElement(By.name("password")).sendKeys("mat_khau_bay_ba_ne");

        // 3. Click Login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // 4. Chờ chuyển trang (Vì bạn bảo nó nhảy về Home)
        Thread.sleep(2000);

        // --- KIỂM TRA (ASSERTION) ---

        WebElement userLabel = driver.findElement(By.xpath("//div[contains(@class,'dropdown')]/a/span"));
        String headerText = userLabel.getText().trim();

        System.out.println("Header đang hiển thị: " + headerText);

        Assert.assertEquals(headerText, "Tài khoản", "Lỗi: Đăng nhập sai mà header lại đổi tên!");


        String pageSource = driver.getPageSource();
        boolean isUserLoggedIn = pageSource.contains("Phạm Đức Toàn");

        Assert.assertFalse(isUserLoggedIn, "Lỗi: Nhập sai pass mà vẫn thấy tên User hiện ra!");

        System.out.println("Check: Không thấy tên user -> Test Pass ✅");
    }

    @AfterMethod
    public void tearDown() {
         driver.quit();
    }
}