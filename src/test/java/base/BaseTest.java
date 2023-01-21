package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest
{
    PlaywrightFactory factory;
    protected Page page;

    @BeforeTest
    public void setupPlaywright()
    {
        factory = new PlaywrightFactory();
        page = factory.initBrowser("chrome");
    }

    @AfterTest
    public void tearDownPlaywright()
    {
        page.context().browser().close();
    }

}
