package test;

import org.junit.jupiter.api.Test;

public class TestImpl extends TestBase {


    @Test
    void openGitHubSite() {

        getWebDriver().get("https://github.com/taron19?tab=repositories");

    }


}
