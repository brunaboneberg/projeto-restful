package service;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;

import static util.staticValue.BASE_URL;

public class RestService {

    public static <T> Response post(T payload, String PATH){
        return RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(PATH)
                .header("Content-Type", ContentType.JSON)
                .body(new Gson().toJson(payload))
                .when()
                .log().all()
                .post()
                .then()
                .log().all()
                .extract().response();
    }

    public static Response get(String PATH) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(PATH)
                .header("Content-Type", ContentType.JSON)
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
    }
    public static Response getWithParams(String PATH, Map<String, Object> params) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(PATH)
                .header("Content-Type", ContentType.JSON)
                .queryParams(params)
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
    }



}
