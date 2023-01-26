package factory;

import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.Base64;

public class PlaywrightFactory
{
    Playwright playwright;
    Browser browser;
    BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
    BrowserContext context;
    private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

    public static Page getPage() {
        return tlPage.get();
    }

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
        tlPage.set(context.newPage());
        getPage().navigate("https://naveenautomationlabs.com/opencart/");

        return getPage();
    }

    public static String takeScreenshot(String methodName)
    {
        String path = System.getProperty("user.dir") +"/screenshots/"+methodName+".png";

//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get(path))
//                .setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }
}
