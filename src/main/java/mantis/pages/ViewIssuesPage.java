package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ViewIssuesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ViewIssuesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#buglist tbody tr")
    private List<WebElement> issues;

    @FindBy(css = "#buglist tbody tr:first-child td:nth-child(11)")
    private WebElement summaryIssue;

    @FindBy(css = "#buglist tbody tr:first-child td:nth-child(4)")
    private WebElement FirstIssue;

    public int getCountIssues() {
        return issues.size();
    }

    public String getSummaryFirstIssue() {
        return summaryIssue.getText();
    }

    public void choiceFirstIssue() {
        FirstIssue.click();
    }
}
