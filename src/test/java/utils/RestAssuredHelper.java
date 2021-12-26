package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredHelper {
    private static Response _extractResponse(Response spec) {
        return spec.then().extract().response();
    }

    public static Response get(String url) {
        return RestAssuredHelper._extractResponse(given()
                .contentType(ContentType.JSON)
                .when()
                .get(url));
    }

    public static Response post(String url, Object payload, Boolean delete, String token) {
        RequestSpecification spec = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when();

        Response res = spec.post(url);

        if (delete)
            res = spec.delete(url);

        return RestAssuredHelper._extractResponse(res);
    }
}
