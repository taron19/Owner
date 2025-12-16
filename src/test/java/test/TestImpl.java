package test;

import org.junit.jupiter.api.Test;

public class TestImpl extends TestBase {


    @Test
    void openHhSite() {
        //Устанавливаем значения если не хотим передвать через терминал
        /*System.setProperty("browser.name","firefox");
        System.setProperty("browser.version","latest");
        System.setProperty("remote","false");
        System.setProperty("remote.url","http://selenoid:4444/wd/hub");*/


            getWebDriver().get("https://github.com/taron19?tab=repositories");

    }


}
