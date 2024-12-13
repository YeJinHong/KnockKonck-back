package com.test.knockknockback.api.crawling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Duration;
import java.util.HashMap;

@Component
public class WebDriverUtil {

    private static String USER_AGENT;
    private static String WEB_DRIVER_PATH;
    @Value("${selenium.user-agent}")
    public void setUserAgent(String userAgent){
        USER_AGENT = userAgent;
    }
    @Value("${selenium.web-driver-path}")
    public void setWebDriverPath(String webDriverPath){
        WEB_DRIVER_PATH = webDriverPath;
    }

    public static WebDriver getChromeDriver(){

        if(ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))){
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }

        // WebDriver 옵션 설정
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("user-agent=" +USER_AGENT);

        // 속도 최적화 - 브라우저 인터페이스가 필요하지 않은 경우 헤드리스 크롬 사용
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--ignore-ssl-errors=yes");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--log-level=3");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--incognito");

        // 이미지 로딩 비활성화
        chromeOptions.addArguments("--disable-images");
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 2);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        WebDriver driver = new ChromeDriver(chromeOptions);
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;

    }

    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }
    public static void close(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.close();
        }
    }
}
