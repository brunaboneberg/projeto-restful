package api;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CreateService;
import service.LoginService;
import service.RestService;

import static org.junit.jupiter.api.Assertions.*;

public class GetBookingIds extends BaseTest {

    private String token;
    @BeforeEach
    void setup() {
        token = LoginService.getToken();
    }

    @Test
    @DisplayName("GET/ Busca Reserva por ID")
    void GetBookingById(){
        Response booking = CreateService.CreateBooking();

        String id = booking.jsonPath().getString("bookingid");

        Response bookingById = RestService.get("/booking/"+ id, token);
        JsonPath jsonResponse = bookingById.jsonPath();
        assertEquals(200, bookingById.statusCode());


        String firstName = jsonResponse.getString("firstname");
        String lastName = jsonResponse.getString("lastname");
        Integer totalPrice = jsonResponse.getInt("totalprice");
        Boolean depositPaid = jsonResponse.getBoolean("depositpaid");
//      Object bookingDates = jsonResponse.getString("bookingdates");
        String additionalNeeds = jsonResponse.getString("additionalneeds");


        assertInstanceOf(String.class, firstName);
        assertInstanceOf(String.class, lastName);
        assertInstanceOf(Integer.class, totalPrice);
        assertInstanceOf(Boolean.class, depositPaid);
        assertInstanceOf(String.class, additionalNeeds);

        assertNotNull(firstName);
        assertNotNull(lastName);
        assertNotNull(totalPrice);
        assertNotNull(depositPaid);
        assertNotNull(additionalNeeds);
    }

    @Test
    @DisplayName("GET/ Busca Reserva com ID inexistente")
    void GetBookingByIdNotExist(){

        Faker faker = new Faker();

        Integer idNotExist = faker.number().randomDigit();

        Response bookingByIdNotExist = RestService.get("/booking/"+ idNotExist, token);
        assertEquals(404, bookingByIdNotExist.statusCode());

    }

}
