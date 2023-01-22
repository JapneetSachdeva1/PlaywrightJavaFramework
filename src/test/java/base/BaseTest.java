package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;
import pages.LoginPage;

import static utils.PropertyReader.loadProperty;

public class BaseTest
{
    PlaywrightFactory factory;
    protected Page page;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected String pageKey;

    @BeforeTest(alwaysRun = true)
    public void setupPlaywright()
    {
        factory = new PlaywrightFactory();
        page = factory.initBrowser(loadProperty("browserName"));

        if(pageKey.equalsIgnoreCase("homepage"))
        {
            homePage = new HomePage(page);

        } else if (pageKey.equalsIgnoreCase("loginpage"))
        {
            homePage = new HomePage(page);
            loginPage = homePage.goToLoginPage();
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
