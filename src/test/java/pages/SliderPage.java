package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class SliderPage extends BasePage {
    WebDriver driver;

    public SliderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#slider-range-min > span")
    public WebElement slider;

    public void slidePrice(int x) {
        slideLR(driver, slider,x);
    }
}
