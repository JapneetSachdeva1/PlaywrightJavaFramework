package pageTest;

import base.BaseTest;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static constants.LoginPageConstants.*;

@Listeners(utils.ReportListeners.class)

public class LoginPageTests extends BaseTest
{

    public LoginPageTests()
    {
        pageKey = "loginpage";

    }

    @Test
    public void validUserLoginTest()
    {
        accountPage = loginPage.loginUser(EMAIL, PASSWORD);
        boolean presence = accountPage.validateMyAccountTextPresence();
        Assert.assertTrue(presence);
    }

}
