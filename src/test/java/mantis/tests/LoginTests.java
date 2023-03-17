package mantis.tests;

import mantis.pages.MantisSite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTest {

    private MantisSite mantisSite;

    @Test
    public void loginUrlTest() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://academ-it.ru/mantisbt/login_page.php", currentUrl);
    }

    @Test
    public void loginTitleTest() {
        String currentTitle = driver.getTitle();
        Assertions.assertEquals("MantisBT", currentTitle);
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        String currentUserName = mantisSite.getMainPage().getUserName();
        Assertions.assertEquals("admin", currentUserName);
        Thread.sleep(1000);
    }

    @Test
    public void negativelLoginTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.getLoginPage().login("admin");
        mantisSite.getPasswordPage().login("admin25");

        String textError = mantisSite.getLoginPage().getTextErrorField();
        if (textError.contains("Your")) {
            Assertions.assertEquals("Your account may be blocked, or the login " +
                    "name/password you entered is incorrect.", textError);
        } else {
            Assertions.assertEquals("Возможно, ваша учетная запись заблокирована, " +
                    "или введенное регистрационное имя/пароль неправильны.", textError);
        }
        Thread.sleep(1000);
    }
}
