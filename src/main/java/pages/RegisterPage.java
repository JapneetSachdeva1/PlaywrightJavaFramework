package pages;

import com.microsoft.playwright.Page;

public class RegisterPage
{
    private Page page;

    //locators
    private final String firstName = "//*[@id='input-firstname']";
    private final String lastName = "//*[@id='input-lastname']";
    private final String email = "//*[@id='input-email']";
    private final String telephone = "//*[@id='input-telephone']";
    private final String password = "//*[@id='input-password']";
    private final String confirmPswd = "//*[@id='input-confirm']";
    private final String agreePrivacyPolicy = "//input[@name='agree']";

    public RegisterPage(Page page)
    {
        this.page = page;
    }

    public void registerUser(String fName, String lName, String emailId, String phnNumber,
                             String pswd) throws InterruptedException {
        page.locator(firstName).fill(fName);
        Thread.sleep(1000);
        page.locator(lastName).fill(lName);
        Thread.sleep(1000);
        page.locator(email).fill(emailId);
        Thread.sleep(1000);
        page.locator(telephone).fill(phnNumber);
        Thread.sleep(1000);
        page.locator(password).fill(pswd);
        Thread.sleep(1000);
        page.locator(confirmPswd).fill(pswd);
        Thread.sleep(1000);
        page.locator(agreePrivacyPolicy).click();
        Thread.sleep(1000);
    }
}
