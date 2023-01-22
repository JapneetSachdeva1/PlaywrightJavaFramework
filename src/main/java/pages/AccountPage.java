package pages;

import com.microsoft.playwright.Page;

public class AccountPage
{
    private Page page;

    //locators
    private String myAccountTextValidation = "//h2[text()='My Account']";


    public AccountPage(Page page)
    {
        this.page = page;
    }


    public boolean validateMyAccountTextPresence()
    {
       boolean elementPresence =  page.locator(myAccountTextValidation).isVisible();
        System.out.println("Element is visible: "+elementPresence);
        return elementPresence;
    }


}
