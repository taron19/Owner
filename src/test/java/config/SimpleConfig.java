package config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:${env}.properties"
})
public interface SimpleConfig extends Config {


    @Key("browser.name")
    String browserName();

    @Key("browser.version")
    String browserVersion();

    @Key("remote")
    boolean isRemote();

    @Key("remote.url")
    String remoteUrl();
}
