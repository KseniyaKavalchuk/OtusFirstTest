import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SimpleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Test
    public void Log(){
        logger.info("Info check");
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver is up");
    }

    public void openPage() {
        driver.get(cfg.url());
        logger.info("Otus page is opened");
    }

    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver is closed");
    }
}
