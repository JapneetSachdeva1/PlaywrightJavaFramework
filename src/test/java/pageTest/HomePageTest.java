package pageTest;

import base.BaseTest;
import com.microsoft.playwright.Playwright;
import factory.PlaywrightFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest
{
    HomePage homePage;

    @BeforeMethod
    public void setupHomePage()
    {
        homePage = new HomePage(page);
    }

    @Test
    public void pageTitleTest()
    {
        String title = homePage.getPageTitle();
        Assert.assertEquals(title, "Your Store");
    }

    @Test
    public void pageUrlTest()
    {
        String url = homePage.getPageUrl();
        Assert.assertEquals(url, "https://naveenautomationlabs.com/opencart/index.php?route=common/home");
    }

    @DataProvider
    public Object[][] searchData()
    {
        return new Object[][]
                {
                        {"Macbook"},
                        {"iMac"},
                        {"iPhone"}
                };
    }

    @Test(dataProvider = "searchData")
    public void searchTest(String productName) throws InterruptedException
    {
        String itemName = homePage.searchItem(productName);
        Thread.sleep(5000);
        Assert.assertEquals("Search - "+productName, itemName);
        System.out.println("item: "+itemName);
    }
}
