package pageTest;

import base.BaseTest;
import org.testng.annotations.Test;

import static constants.LoginPageConstants.*;

public class LoginPageTests extends BaseTest
{
    public LoginPageTests()
    {
        pageKey = "loginpage";
    }

    @Test
    public void validUserLoginTest()
    {
        loginPage.loginUser(EMAIL, PASSWORD);
    }

}
