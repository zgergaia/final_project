package utils;

import org.json.simple.JSONObject;
import org.testng.Assert;

public class RegisterUserAPI {
    String username;
    String password;

    public RegisterUserAPI(String username, String password) {
        this.username = username;
        this.password = password;

        JSONObject payload = new JSONObject();
        payload.put("userName", username);
        payload.put("password", password);

        var response = RestAssuredHelper.post("https://bookstore.toolsqa.com/Account/v1/User", payload.toJSONString(), false, null);

        Assert.assertEquals(response.statusCode(), 201);
    }
}
