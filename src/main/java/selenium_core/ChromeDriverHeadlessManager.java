package selenium_core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverHeadlessManager extends DriverManager{
    @Override
    public void createWebDriver(String version){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver"+version+".exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=390,844");
        this.driver = new ChromeDriver(options);
    }
}
