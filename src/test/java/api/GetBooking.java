package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CreateService;
import service.LoginService;
import service.RestService;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GetBooking extends BaseTest {

    private String token;
    @BeforeEach
    void setup() {
        token = LoginService.getToken();
    }

    @Test
    @DisplayName("GET/ Busca lista completa de Reservas")
    void GetBookingsSuccess(){

    Response allBookings = RestService.get("/booking", token);
    JsonPath jsonResponse = allBookings.jsonPath();
    assertEquals(200, allBookings.statusCode());

    List<Integer> bookingsIds = jsonResponse.getList("bookingid", Integer.class);

    assertNotNull(bookingsIds);
    assertFalse(bookingsIds.isEmpty());
    }

    @Test
    @DisplayName("GET/ Busca reserva cadastrada pelos parâmetros de nome e sobrenome")
    void GetBookingsWithAllParams() {

        Response booking = CreateService.CreateBooking();

        String id = booking.jsonPath().getString("bookingid");

        Response bookingById = RestService.get("/booking/"+ id, token);
        JsonPath jsonResponse = bookingById.jsonPath();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("firstname", jsonResponse.getString("firstname"));
        queryParams.put("lastname", jsonResponse.getString("lastname"));

        Response allBookingsByFilter = RestService.getWithParams("/booking", token, queryParams);

        String responseBody = allBookingsByFilter.body().asString();
        assertTrue(responseBody.contains(id));
        assertEquals(200, allBookingsByFilter.statusCode());

    }

    @Test
    @DisplayName("GET/ Busca as Reservas com Parametros de checkin e checkout")
    void GetBookingsWithParamsCheckinCheckout() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkin", "2024-01-31");
        queryParams.put("checkout", "2024-02-02");

        Response allBookings = RestService.getWithParams("/booking", token, queryParams);
        JsonPath jsonResponse = allBookings.jsonPath();

        assertEquals(200, allBookings.statusCode());

    }

}
