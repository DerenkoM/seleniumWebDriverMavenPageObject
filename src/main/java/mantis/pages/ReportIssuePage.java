package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportIssuePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private String textSummary;

    public ReportIssuePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
        this.textSummary = "New report test";
    }

    @FindBy(css = "#summary")
    private WebElement summary;

    @FindBy(css = "#description")
    private WebElement description;

    @FindBy(css = "[type = 'submit']")
    private WebElement submitIssue;

    public void sendSummaryText() {
        summary.sendKeys(textSummary);
    }

    public String getSummaryText() {
        return textSummary;
    }

    public void sendDescriptionText() {
        description.sendKeys("Description of new report");
    }

    public void submitNewIssue() {
        submitIssue.click();
    }
}
