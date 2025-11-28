package pageTest;

import base.BaseTest;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static utils.RetrieveJsonData.getjsonData;

public class RegisterPageTests extends BaseTest
{
    public RegisterPageTests()
    {
        pageKey = "registerpage";
    }

    @Test(dataProvider = "getRegisterUserData")
    public void testPositiveRegistrationOfUser(HashMap<String,String> input) throws InterruptedException
    {
        registerPage.registerUser(input.get("fName"),input.get("lName"),input.get("email"),
                input.get("telephone"), input.get("password"));
        System.out.println("For Git Branching Test");
//        String text = accountPage.getAccountCreatedText();
//        Assert.assertEquals(text,"Your Account Has Been Created!");
    }



    @DataProvider
    public Object[][] getRegisterUserData() throws IOException
    {
        List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir") + "/src/test/resources/testData/registrationData.json");
        return new Object[][]
                {
                        {data.get(0)},
                        {data.get(1)}
                };
    }
}
