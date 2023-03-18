package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ReportIssueTest extends BaseTest {
    private MantisSite mantisSite;

    @Test
    public void checkReportAndDeleteIssue() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        String summaryAdd = "New report test";
        String descriptionAdd = "Description of new report";

        mantisSite.getMainPage().goToReportIssuesPage();
        Thread.sleep(3000);
        WebElement summaryLabel = driver.findElement(By.cssSelector("label[for = 'summary']")); //делала скролл, тк у меня не видно было вводимые поля
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", summaryLabel);
        mantisSite.getReportIssuePage().sendSummaryText(summaryAdd);
        Thread.sleep(3000);
        mantisSite.getReportIssuePage().sendDescriptionText(descriptionAdd);
        Thread.sleep(3000);
        mantisSite.getReportIssuePage().submitNewIssue();

        WebElement result = driver.findElement(By.xpath("//p[contains(text(),'Operation successful')]"));
        String resultText = result.getText();
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(resultText).isEqualTo("Operation successful.");
        Thread.sleep(5000);

        String actualSummaryText = mantisSite.getViewIssuesPage().getSummaryFirstIssue();
        softAssert.assertThat(actualSummaryText).isEqualTo(summaryAdd); //проверка верхней строчки

        mantisSite.getViewIssuesPage().choiceFirstIssue();
        mantisSite.getReportIssuePage().delete();
        mantisSite.getReportIssuePage().deleteIssue();

        String actualSummaryText1 = mantisSite.getViewIssuesPage().getSummaryFirstIssue();
        softAssert.assertThat(actualSummaryText1).isNotEqualTo(summaryAdd);

        softAssert.assertThat(driver.getCurrentUrl()).isEqualTo("https://academ-it.ru/mantisbt/view_all_bug_page.php");

        softAssert.assertThat(driver.getTitle()).isEqualTo("View Issues - MantisBT");

        WebElement mantisImg = driver.findElement(By.cssSelector("#powered-by-mantisbt-logo a  img"));
        softAssert.assertThat(mantisImg.isDisplayed()).isEqualTo(true);

        softAssert.assertAll();
    }
}
