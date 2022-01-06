package com.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumFMain {
    static WebDriver driver;
    static ExcelUtils excel;

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        excel = new ExcelUtils(BaseClass.FILE_PATH);
        excel.setExcelFile(BaseClass.FILE_PATH, BaseClass.SHEET_NAME);
        driver.get("https://www.facebook.com/");
        int rowCount = excel.getRowCountInSheet();
        for (int i = 1; i <= rowCount; i++) {
            driver.findElement(By.linkText("Create New Account")).click();
            String firstname = excel.getCellData(i, 0);
            System.out.println(firstname);
            String Email = excel.getCellData(i, 1);
            System.out.println(Email);
            String Password = excel.getCellData(i, 2);
            System.out.println(Password);
            driver.findElement(By.name("firstname")).sendKeys(excel.getCellData(i, 0));
            driver.findElement(By.name("reg_email__")).sendKeys(excel.getCellData(i, 1));
            driver.findElement(By.name("reg_passwd__")).sendKeys(excel.getCellData(i, 2));
            driver.findElement(By.name("websubmit")).click();
            driver.navigate().refresh();
        }
    }
}
