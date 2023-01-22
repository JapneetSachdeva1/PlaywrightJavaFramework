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
    private String myAccount = "//span[text()='My Account']";
    private String loginBtn = "//a[text()='Login']";
    private String myAccountBtn = "//span[text()='My Account']";
    private String registerBtn = "//a[text()='Register']";


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

    public LoginPage goToLoginPage()
    {
        page.click(myAccount);
        page.click(loginBtn);
        return new LoginPage(page);
    }

    public RegisterPage goToRegisterPage()
    {
        page.locator(myAccountBtn).click();
        page.locator(registerBtn).click();
        return new RegisterPage(page);
    }

}
