package tests;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.RestService;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static util.staticValue.PATH_BOOKING;

public class GetBookingIds {

    @Test
    @DisplayName("GET/ Busca Reserva por ID aleatorio")
    void getBookingByIdList() {

        int randomIndex = new Random().nextInt(10) + 1;
        String randomBookingId = String.valueOf(randomIndex);
        
        Response bookingById = RestService.get(PATH_BOOKING + "/" + randomBookingId);
        assertEquals(HttpStatus.SC_OK, bookingById.statusCode());

        JsonPath jsonResponse = bookingById.jsonPath();

        assertNotNull(jsonResponse.getString("firstname"));
        assertNotNull(jsonResponse.getString("lastname"));
        assertNotNull(jsonResponse.getInt("totalprice"));
        assertNotNull(jsonResponse.getBoolean("depositpaid"));
        assertNotNull(jsonResponse.getString("bookingdates.checkin"));
        assertNotNull(jsonResponse.getString("bookingdates.checkout"));
        assertNotNull(jsonResponse.getString("additionalneeds"));

    }

    @Test
    @DisplayName("GET/ Busca Reserva com ID inexistente")
    void getBookingByIdNotExist(){

        Faker faker = new Faker();

        String idNotExist = faker.number().digits(20);

        Response bookingByIdNotExist = RestService.get(PATH_BOOKING + "/" + idNotExist);
        assertEquals(HttpStatus.SC_NOT_FOUND, bookingByIdNotExist.statusCode());

    }
}
