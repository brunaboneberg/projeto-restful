package service;

import org.junit.jupiter.api.DisplayName;
import dto.LoginDTO;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class LoginService {
//    @Test
    @DisplayName("Gera Token")

    public static String getToken(){
        LoginDTO requestBody = new LoginDTO("admin", "password123");
        Response response = RestService.post(requestBody,"/auth");
        return response.jsonPath().getString("token");

    }
}
