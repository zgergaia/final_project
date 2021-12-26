package utils;

import com.codeborne.selenide.Condition;
import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.responseObjects.AuthorizationResponse;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginPage {
    String username;
    String password;

    public LoginPage(String username, String password) {
        this.username = username;
        this.password = password;

        this.loginReLogin();
        this.authorization();
    }

    private void _login() {
        $("#userName").sendKeys(username);
        $("#password").sendKeys(password);
        $("#login").click();
    }

    private void loginReLogin() {
        open("https://demoqa.com/login");

        this._login();

        Helpers.FindAndScroll($(By.xpath("//button[text()='Delete Account']"))).click();
        $("#closeSmallModal-ok").click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Alert alert = getWebDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "User Deleted.");
        alert.accept();

        this._login();

        $("#name").shouldBe(Condition.visible).shouldHave(Condition.text("Invalid username or password!"));
    }

    private void authorization() {
        JSONObject payload = new JSONObject();
        payload.put("userName", username);
        payload.put("password", password);
        var rawResponse = RestAssuredHelper.post(
                "https://bookstore.toolsqa.com/Account/v1/Authorized",
                payload.toJSONString(),
                false,
                null
        );

        AuthorizationResponse response = rawResponse.as(AuthorizationResponse.class);
        Assert.assertEquals(response.message, "User not found!");
    }
}
