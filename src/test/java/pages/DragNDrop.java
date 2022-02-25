package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragNDrop extends BasePage {
    WebDriver driver;

    public DragNDrop(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'BANK')]")
    public WebElement bankDrag;
    @FindBy(css = "#bank")
    public WebElement bankDrop;

    public void dropBank() {
        dragNDrop(bankDrag, bankDrop);
    }
}
