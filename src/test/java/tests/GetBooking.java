package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.RestService;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GetBooking {

    @Test
    @DisplayName("GET/ Busca lista completa de Reservas")
    void getBookingsSuccess(){

    Response allBookings = RestService.get("/booking");
    JsonPath jsonResponse = allBookings.jsonPath();
    assertEquals(HttpStatus.SC_OK, allBookings.statusCode());

    List<Integer> bookingsIds = jsonResponse.getList("bookingid", Integer.class);

    assertNotNull(bookingsIds);
    assertFalse(bookingsIds.isEmpty());

    }

    @Test
    @DisplayName("GET/ Busca reserva pelos parâmetros de nome e sobrenome")
    void getBookingsWithAllParams() {

        Response allBookings = RestService.get("/booking");
        assertEquals(HttpStatus.SC_OK, allBookings.statusCode());

        List<Integer> bookingIds = allBookings.jsonPath().getList("bookingid");
        String randomBookingId = String.valueOf(bookingIds.get((int) (Math.random() * bookingIds.size())));

        Response bookingById = RestService.get("/booking/" + randomBookingId);
        assertEquals(HttpStatus.SC_OK, bookingById.statusCode());

        JsonPath jsonBookingById = bookingById.jsonPath();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("firstname", jsonBookingById.getString("firstname"));
        queryParams.put("lastname", jsonBookingById.getString("lastname"));

        Response allBookingsByFilter = RestService.getWithParams("/booking", queryParams);
        assertEquals(HttpStatus.SC_OK, allBookingsByFilter.statusCode());

        assertEquals(jsonBookingById.getString("firstname"), allBookingsByFilter.jsonPath().getString("firstname"));
        assertEquals(jsonBookingById.getString("lastname"), allBookingsByFilter.jsonPath().getString("lastname"));
    }


    @Test
    @DisplayName("GET/ Busca as Reservas com Parametros de checkin e checkout")
    void getBookingsWithParamsCheckinCheckout() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkin", "2024-01-31");
        queryParams.put("checkout", "2024-02-02");

        Response allBookings = RestService.getWithParams("/booking", queryParams);
        JsonPath jsonResponse = allBookings.jsonPath();

        assertEquals(HttpStatus.SC_OK, allBookings.statusCode());

    }

}
