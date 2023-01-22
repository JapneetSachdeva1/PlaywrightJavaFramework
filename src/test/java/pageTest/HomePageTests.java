package pageTest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static constants.HomePageConstants.HOME_PAGE_TITLE;
import static utils.PropertyReader.loadProperty;

public class HomePageTests extends BaseTest
{

    public HomePageTests()
    {
        pageKey = "homepage";
    }

    @Test
    public void pageTitleTest()
    {
        String title = homePage.getPageTitle();
        Assert.assertEquals(title, HOME_PAGE_TITLE);
    }

    @Test
    public void pageUrlTest()
    {
        String url = homePage.getPageUrl();
        Assert.assertEquals(url, loadProperty("baseUrl"));
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
