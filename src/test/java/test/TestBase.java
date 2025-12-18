package test;


import config.SimpleConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {
    private  WebDriver webDriver;

    @BeforeAll
    public void init() throws MalformedURLException {

        //Если мы хотим передавать данные НЕ через терминал а тут в коде!!
        //делаем перед ConfigFactory.create т.к в этот момент читаются параметры!
        /*System.setProperty("browser.name","firefox");
        System.setProperty("remote","true");
        System.setProperty("remote.url","https://user1:1234@selenoid.autotests.cloud/wd/hub");*/

        SimpleConfig simpleConfig = ConfigFactory.create(SimpleConfig.class, System.getProperties());


        if (!simpleConfig.isRemote()) {
            webDriver = getWebDriver(simpleConfig.browserName());

        } else {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(simpleConfig.browserName());

            if (!simpleConfig.browserVersion().isEmpty()) {
                caps.setVersion(simpleConfig.browserVersion());
            }

            webDriver = new RemoteWebDriver(
                    new URL(simpleConfig.remoteUrl()), caps
            );

        }


    }

    private  WebDriver getWebDriver(String driver) {
        WebDriver driver1;

        switch (driver) {
            case "chrome" -> {
                WebDriverManager.chromedriver().clearDriverCache().setup();
                driver1 = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                driver1 = new FirefoxDriver();
            }

            default -> throw new IllegalArgumentException("Unknown browser: " + driver);
        }

        return driver1;
    }

    public WebDriver getWebDriver() {
        return webDriver;


    }

    @AfterAll
    void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
