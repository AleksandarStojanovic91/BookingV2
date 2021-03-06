package steps;

import excell_core.ExcelUtilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import pages.*;
import tests.BaseTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class BookingSteps extends BaseTest {

    String BROWSER = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER");
    String BROWSER_VERSION = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER_VERSION");
    String WAIT = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT");
    String ENV = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ENV");

    Map<String, String> data;
    String testDataPath = "src/test/test_data/";

    @Before
    public void setUp() throws Exception {
        setUPTest(BROWSER,BROWSER_VERSION,Integer.parseInt(WAIT));
    }

    @After
    public void tearDown() throws IOException, InterruptedException {
//        reportScreenshot("end", "screenshot on end or fail");
        quit();
    }

    @Given("I load test data from {string} {string} {string}")
    public void iLoadTestDataFrom(String fileName, String sheetName, String rowNum) throws IOException {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        data = excelUtilities.getRowData(testDataPath+fileName+".xlsx", sheetName, rowNum);
    }

    @Given("I navigate to Booking")
    public void iNavigateToBooking() throws Exception {
        startApplication(ENV);
    }

    @When("I add location")
    public void iAddLocation() throws InterruptedException {
        StaysPage staysPage = new StaysPage(driver);
        staysPage.addLocation(data.get("Location"));
    }

    @And("I add check in date and check out date")
    public void iAddCheckInDateAndCheckOutDate() throws InterruptedException {
        StaysPage staysPage = new StaysPage(driver);
        staysPage.checkInOut(data.get("CheckIn"),data.get("CheckOut"));
    }

    @And("I click search")
    public void iClickSearch() throws InterruptedException {
        StaysPage staysPage = new StaysPage(driver);
        staysPage.clickSearch();
    }

    @And("I add occupancy information")
    public void iAddOccupancyInformation() throws InterruptedException {
        StaysPage staysPage = new StaysPage(driver);
        staysPage.addOccupancyInformation(data);
    }

    @Then("I verify search criteria")
    public void iVerifySearchCriteria() throws InterruptedException, ParseException {
        SearchResultPage searchResultPage = new SearchResultPage(driver);

        searchResultPage.verifyLocation(data.get("Location"));

        searchResultPage.verifyDates(data);

        searchResultPage.openGuestInfo();

        searchResultPage.verifyAdultsNum(data.get("AdultsNum"));
        searchResultPage.verifyChildrenNum(data.get("ChildrenNum"));
        searchResultPage.verifyRoomsNum(data.get("Rooms"));
    }

    @And("I see Availability of first result")
    public void iSeeAvailabilityOfFirstResult() throws InterruptedException {
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.clickFirstResult();
    }

    @And("I verify availability information")
    public void iVerifyAvailabilityInformation() throws ParseException, InterruptedException {

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        SearchResultPage searchResultPage = new SearchResultPage(driver);

        searchResultPage.verifyLocation(data.get("Location"));

        searchResultPage.verifyDates(data);

        searchResultPage.openGuestInfo();

        searchResultPage.verifyAdultsNum(data.get("AdultsNum"));
        searchResultPage.verifyChildrenNum(data.get("ChildrenNum"));
        searchResultPage.verifyRoomsNum(data.get("Rooms"));
//        driver.switchTo().window(tabs.get(0)); Go back to the first tab
    }


    //Frame example
    @Given("I open url")
    public void iOpenUrl() {
        driver.get("https://www.w3schools.com/html/html_iframe.asp");
    }

    @And("I click inside an iframe")
    public void iClickInsideAnIframe() {
        driver.switchTo().frame(driver.findElement(By.cssSelector("[title='W3Schools HTML Tutorial']")));

        driver.findElement(By.xpath("//*[@title='Java Tutorial']")).click();

        driver.findElement(By.xpath("//h1[text()='Java ']")).getText();
        driver.findElement(By.xpath("//h1/span[text()='Tutorial']")).getText();

        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("#w3loginbtn")).click();
    }

    @Given("I am on slider URL")
    public void iAmOnSliderURL() {
        driver.get("https://jqueryui.com/slider/#rangemin");
    }

    @Given("I am on drag and drop URL")
    public void iAmOnDragAndDropURL() {
        driver.get("https://demo.guru99.com/test/drag_drop.html");
    }

    @And("I move slider")
    public void iMoveSlider() {
        //Selector elementa koji pomeramo
        //x,y osa u pixelima

        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));

        SliderPage sliderPage = new SliderPage(driver);
        sliderPage.slidePrice(150);

        driver.switchTo().defaultContent();
    }


    @And("I drag and drop element")
    public void iDragAndDropElement() {
        //Element koji pomeramo
        //Element u koji pomeramo
        //x,y

        DragNDrop dragNDrop = new DragNDrop(driver);
        dragNDrop.dropBank();
    }

    @Given("I am on youtube.com")
    public void iAmOnYoutubeCom() {
        driver.get("https://www.youtube.com/");
    }

    @And("I scroll to the bottom of the page")
    public void iScrollToTheBottomOfThePage() throws InterruptedException {
        Thread.sleep(5000);
        BasePage basePage = new BasePage(driver);
//        basePage.scrollToBottom();
//        basePage.scrollY("300");
//        takeScreenshot();
//        basePage.scrollToElement(driver.findElements(By.xpath("//div[@id=\"contents\"]//*[@class='style-scope ytd-rich-grid-row']")).get(17));
    }
}
