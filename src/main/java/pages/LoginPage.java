package pages;

import com.microsoft.playwright.Page;

public class LoginPage
{
    private Page page;

    //locators
    private String emailIdfield = "//input[@name='email']";
    private String passwordfield = "//input[@name='password']";
    private String loginBtn = "//input[@value='Login']";


    public LoginPage(Page page)
    {
        this.page = page;
    }

    public AccountPage loginUser(String email, String password)
    {
        page.fill(emailIdfield, email);
        page.fill(passwordfield, password);
        page.click(loginBtn);
        return new AccountPage(page);
    }

}
