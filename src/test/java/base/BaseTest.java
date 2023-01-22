package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.testng.annotations.*;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;

import java.awt.image.PackedColorModel;

import static utils.PropertyReader.loadProperty;

public class BaseTest
{
    PlaywrightFactory factory;
    protected Page page;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected AccountPage accountPage;
    protected String pageKey;

    //Constants
    private final String browser = loadProperty("browserName");

    @BeforeTest(alwaysRun = true)
    public void setupPlaywright()
    {
        factory = new PlaywrightFactory();
        page = factory.initBrowser(browser);

        if(pageKey.equalsIgnoreCase("homepage"))
        {
            homePage = new HomePage(page);

        } else if (pageKey.equalsIgnoreCase("loginpage"))
        {
            homePage = new HomePage(page);
            loginPage = homePage.goToLoginPage();

        } else if (pageKey.equalsIgnoreCase("registerpage"))
        {
            homePage = new HomePage(page);
            registerPage = homePage.goToRegisterPage();
        }

        else
        {
            System.out.println("page not available!!");
        }

    }


    @AfterTest(alwaysRun = true)
    public void tearDownPlaywright()
    {
        page.context().browser().close();
    }

}
