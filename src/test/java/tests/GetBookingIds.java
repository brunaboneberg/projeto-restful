package tests;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.RestService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetBookingIds {


    @Test
    @DisplayName("GET/ Busca Reserva por ID aleatório")
    void getBookingByIdList() {

        Response allBookings = RestService.get("/booking");
        assertEquals(HttpStatus.SC_OK, allBookings.statusCode());

        List<Integer> bookingIds = allBookings.jsonPath().getList("bookingid");

        String randomBookingId = String.valueOf(bookingIds.get((int) (Math.random() * bookingIds.size())));

        Response bookingById = RestService.get("/booking/" + randomBookingId);
        assertEquals(HttpStatus.SC_OK, bookingById.statusCode());

        JsonPath jsonResponse = bookingById.jsonPath();

        assertNotNull(jsonResponse.getInt("bookingid"));
        assertNotNull(jsonResponse.getString("booking.firstname"));
        assertNotNull(jsonResponse.getString("booking.lastname"));
        assertNotNull(jsonResponse.getInt("booking.totalprice"));
        assertNotNull(jsonResponse.getBoolean("booking.depositpaid"));
        assertNotNull(jsonResponse.getString("booking.bookingdates.checkin"));
        assertNotNull(jsonResponse.getString("booking.bookingdates.checkout"));
        assertNotNull(jsonResponse.getString("booking.additionalneeds"));

    }

    @Test
    @DisplayName("GET/ Busca Reserva com ID inexistente")
    void getBookingByIdNotExist(){

        Faker faker = new Faker();

        Integer idNotExist = faker.number().randomDigit();

        Response bookingByIdNotExist = RestService.get("/booking/"+ idNotExist);
        assertEquals(HttpStatus.SC_NOT_FOUND, bookingByIdNotExist.statusCode());

    }

}
