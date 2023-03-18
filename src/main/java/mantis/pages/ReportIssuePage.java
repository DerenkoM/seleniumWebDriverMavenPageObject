package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportIssuePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ReportIssuePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#summary")
    private WebElement summary;

    @FindBy(css = "#description")
    private WebElement description;

    @FindBy(css = "[type = 'submit']")
    private WebElement submitIssue;

    @FindBy(css = "[value = 'Delete']")
    private WebElement buttonDelete;

    @FindBy(css = " [value = 'Delete Issues']")
    private WebElement buttonDeleteIssue;//

    public void sendSummaryText(String textSummary) {
        summary.sendKeys(textSummary);
    }

    public void sendDescriptionText(String textDescription) {
        description.sendKeys(textDescription);
    }

    public void submitNewIssue() {
        submitIssue.click();
    }

    public void delete() {
        buttonDelete.click();
    }

    public void deleteIssue() {
        buttonDeleteIssue.click();
    }
}
