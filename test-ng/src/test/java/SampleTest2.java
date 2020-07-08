import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SampleTest2 {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest2.class);
    private ServerConfig cfg2 = ConfigFactory.create(ServerConfig.class);

    @BeforeTest
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver is up");
    }

    @Test
    public void openPage() {
        driver.get(cfg2.url());
        logger.info("Otus is opened");
    }

    @AfterTest
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Driver is closed");
        }
    }

}
