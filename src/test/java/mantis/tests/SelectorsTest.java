package mantis.tests;

import mantis.pages.MantisSite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SelectorsTest extends BaseTest {
    private MantisSite mantisSite;

    @Test
    public void successfulLoginTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().goToViewIssuesPage();
        Thread.sleep(2000);

        WebElement selectAll = driver.findElement(By.cssSelector(".inline"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectAll);

        Thread.sleep(2000);
        String currentUserName = mantisSite.getMainPage().getUserName();
        Assertions.assertEquals("admin", currentUserName);
    }
}
