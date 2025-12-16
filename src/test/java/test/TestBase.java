package test;


import config.SimpleConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    private static WebDriver webDriver;

    @BeforeAll
    public static void init() throws MalformedURLException {
        SimpleConfig simpleConfig = ConfigFactory.create(SimpleConfig.class, System.getProperties());

        if (!simpleConfig.isRemote()) {
            webDriver = getWebDriver(simpleConfig.browserName());

        } else {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(simpleConfig.browserName());
            caps.setVersion(simpleConfig.browserVersion());

            webDriver = new RemoteWebDriver(new URL(simpleConfig.remoteUrl()), caps);

        }


    }

    private static WebDriver getWebDriver(String driver) {

        return switch (driver) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                yield new OperaDriverManager().getWebDriver();
            }
            default -> throw new IllegalArgumentException("Unknown browser: " + driver);
        };

    }

    public  WebDriver getWebDriver() {
        return webDriver;


    }
}
