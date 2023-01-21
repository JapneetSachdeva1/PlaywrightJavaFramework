package pages;

import com.microsoft.playwright.Page;

public class HomePage
{
    //constants
    private Page page;

    //String locators - OR
    private String searchBar = "//input[@name='search']";
    private String searchBtn = "(//input[@name='search']//following::button)[1]";
    private String searchedItemName = "//h1";


    //Constructor to capture Page
    public HomePage(Page page)
    {
        this.page = page;
    }

    public String getPageTitle()
    {
       return page.title();
    }

    public String getPageUrl()
    {
        return page.url();
    }

    public String searchItem(String itemToBeSearched)
    {
        page.fill(searchBar, itemToBeSearched);
        page.click(searchBtn);
        String itemName = page.textContent(searchedItemName);
        return itemName;
    }


}
