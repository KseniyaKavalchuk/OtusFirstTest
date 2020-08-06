//Lesson6 - Ожидания в Selenium. Работа с явными и неявными ожиданиями, сравнение подходов.
//Знакомство с Expected Conditions
//Задание:
//Зайти на сайт https://ng-bootstrap.github.io/#/components/alert/examples
//Найти блок: Self closing alert
//Если нажимать раз в секунду на кнопку, то в всплывающем блоке будет меняться текст
//Имплементировать тест на сверку двух значений внутри блока текста после первого нажатия и после второго (интервал 1 секунда)

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Lesson6 {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SimpleTest.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options.addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        logger.info("Driver is up");
    }

    @Test
    public void testBootstrap() throws InterruptedException {
        driver.get("https://ng-bootstrap.github.io/#/components/alert/examples");
        logger.info("Page is opened");
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Change message')]"));
        logger.info("Button found");
        String alertText = GetAlertText(element);
        logger.info("alertText =" + alertText);
        logger.info("Sleep started");
        Thread.sleep(1000);
        logger.info("Sleep over");
        String alertText2 = GetAlertText(element);
        logger.info("alertText2 =" + alertText);
        Assert.assertNotEquals(alertText, alertText2);

    }

    private String GetAlertText(WebElement element){
        element.click();
        WebElement alertBox = driver.findElement(By.xpath("//div[@class='card-body']//ngb-alert[contains(text(), 'Message successfully changed')]"));
        new WebDriverWait(driver,4).until(visibilityOf(alertBox));
        return alertBox.getText();
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver is closed");
    }

}