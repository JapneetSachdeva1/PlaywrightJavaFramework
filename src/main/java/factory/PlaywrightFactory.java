package factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory
{
    Playwright playwright;
    Browser browser;
    BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
    BrowserContext context;
    Page page;

    public Page initBrowser (String browserName)
    {
        System.out.println("Browser name is: "+browserName);
        playwright = Playwright.create();

        if(browserName.equalsIgnoreCase("chromium"))
        {
            browser = playwright.chromium().launch(launchOptions.setHeadless(false));

        } else if(browserName.equalsIgnoreCase("firefox"))
        {
            browser = playwright.firefox().launch(launchOptions.setHeadless(false));

        } else if (browserName.equalsIgnoreCase("safari"))
        {
            browser = playwright.webkit().launch(launchOptions.setHeadless(false));

        } else if (browserName.equalsIgnoreCase("chrome"))
        {
            browser = playwright.chromium().launch(launchOptions.setChannel("chrome").setHeadless(false));

        } else
        {
            System.out.println("browser not supported!!");
        }

        context = browser.newContext();
        page = context.newPage();
        page.navigate("https://naveenautomationlabs.com/opencart/");

        return page;

    }
}
