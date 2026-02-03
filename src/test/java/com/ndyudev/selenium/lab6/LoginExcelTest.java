package com.ndyudev.selenium.lab6;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginExcelTest {

    WebDriver driver;
    // Map để lưu kết quả test: <Tên Test Case, Trạng Thái Pass/Fail>
    Map<String, String> testResults = new LinkedHashMap<>();

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://localhost:8080/cinevo-web/cinevo/user?tab=login");
    }

    @Test
    public void testLoginElements_Bai1() {
        // --- BÀI 1: THỰC HÀNH CÁC LOẠI LOCATOR TRÊN TRANG CINEVO ---

        System.out.println("Kiểm tra các phần tử GUI...");

        try {
            // 1. Locating by NAME (Ô Email & Password)
            WebElement txtEmail = driver.findElement(By.name("email"));
            WebElement txtPass = driver.findElement(By.name("password"));

            // 2. Locating by ID (Checkbox "Ghi nhớ tôi")
            // Trong code JSP bro có: id="remember"
            WebElement chkRemember = driver.findElement(By.id("remember"));

            // 3. Locating by CSS SELECTOR (Nút Đăng nhập)
            // Cú pháp CSS: tag[attribute='value'] hoặc .class
            WebElement btnLogin = driver.findElement(By.cssSelector("button[type='submit']"));

            // 4. Locating by CLASS NAME (Tiêu đề H2 "ĐĂNG NHẬP")
            // Trong JSP: <h2 class="text-center fw-bold...">
            // Lưu ý: ClassName chỉ chạy tốt nếu class đó là duy nhất, ở đây demo thôi
            WebElement headerTitle = driver.findElement(By.className("text-danger"));

            // Thao tác thử để chứng minh tìm được
            txtEmail.sendKeys("test@demo.com");
            chkRemember.click();

            // Ghi nhận kết quả PASS
            testResults.put("Test GUI Locators", "PASS");
            System.out.println("-> Đã tìm thấy đủ 4 loại Locator!");

        } catch (Exception e) {
            testResults.put("Test GUI Locators", "FAIL");
            Assert.fail("Không tìm thấy phần tử: " + e.getMessage());
        }
    }

    // --- SỬA LẠI TEST CASE 2 ---
    @Test
    public void testLoginFunction_Bai2() throws InterruptedException {
        System.out.println("--- Test Login: Fix lỗi bấm nhầm nút Search ---");

        try {
            driver.navigate().refresh();
            Thread.sleep(2000);

            // Nhập liệu (dùng hàm typeSlowly hoặc sendKeys thường đều được)
            driver.findElement(By.name("email")).sendKeys("toanpham@gmail.com");
            driver.findElement(By.name("password")).sendKeys("pass005");

            // --- KHẮC PHỤC LỖI BẤM NHẦM SEARCH ---
            System.out.println(">>> Đang tìm nút 'Đăng Nhập' xịn...");

            // Cách 1: Tìm theo Text (Khuyên dùng - Dễ hiểu nhất)
            WebElement btnRealLogin = driver.findElement(By.xpath("//button[contains(text(), 'Đăng Nhập')]"));

            // Cách 2: Tìm theo Form cha (Nếu bro thích kiểu cấu trúc)
            // WebElement btnRealLogin = driver.findElement(By.cssSelector("form[action*='login'] button[type='submit']"));

            btnRealLogin.click();

            // Đợi chuyển trang
            System.out.println(">>> Đã bấm đúng nút! Đang đợi server...");
            Thread.sleep(4000);

            // Kiểm tra kết quả
            String pageSource = driver.getPageSource();
            if (pageSource.contains("Phạm Đức Toàn")) {
                testResults.put("Test Chức Năng Login", "PASS");
                System.out.println("✅ Login ngon lành! Đã thấy tên User.");
            } else {
                testResults.put("Test Chức Năng Login", "FAIL");
                System.out.println("❌ Vẫn chưa thấy tên User. URL hiện tại: " + driver.getCurrentUrl());
                Assert.fail("Login thất bại.");
            }

        } catch (Exception e) {
            testResults.put("Test Chức Năng Login", "ERROR");
            throw e;
        }
    }

    public void typeSlowly(WebElement element, String text) {
        element.clear(); // Xóa sạch trước khi nhập
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c)); // Gõ từng chữ
            try {
                Thread.sleep(150); // Nghỉ 0.15 giây giữa các lần gõ (nhìn rất thật)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // --- BÀI 2: XUẤT KẾT QUẢ RA EXCEL SAU KHI CHẠY XONG ---
    @AfterClass
    public void tearDownAndExportExcel() {
        driver.quit();

        // Bắt đầu tạo file Excel
        System.out.println("Đang xuất file Excel...");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("KetQuaKiemThu");

            // Tạo dòng tiêu đề (Header)
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Tên Test Case");
            headerRow.createCell(1).setCellValue("Kết Quả");

            // Duyệt qua Map kết quả để ghi vào các dòng tiếp theo
            int rowNum = 1;
            for (Map.Entry<String, String> entry : testResults.entrySet()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            // Lưu file ra ổ cứng (Lưu ngay tại thư mục gốc project)
            try (FileOutputStream fileOut = new FileOutputStream("KetQuaTest_Cinevo.xlsx")) {
                workbook.write(fileOut);
                System.out.println("✅ Xuất file thành công: KetQuaTest_Cinevo.xlsx");
            }

        } catch (IOException e) {
            System.err.println("❌ Lỗi xuất file Excel: " + e.getMessage());
        }
    }
}