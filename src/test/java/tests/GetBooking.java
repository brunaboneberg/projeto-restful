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
import static util.staticValue.PATH_BOOKING;

public class GetBooking {

    @Test
    @DisplayName("GET/ Busca lista completa de Reservas")
    void getBookingsSuccess(){

    Response allBookings = RestService.get(PATH_BOOKING);
    JsonPath jsonResponse = allBookings.jsonPath();
    assertEquals(HttpStatus.SC_OK, allBookings.statusCode());

    List<Integer> bookingsIds = jsonResponse.getList("bookingid", Integer.class);

    assertNotNull(bookingsIds);
    assertFalse(bookingsIds.isEmpty());

    }

    @Test
    @DisplayName("GET/ Busca as Reservas pelos parametros")
    void getBookingsWithParams() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("firstname", "Sally");
        queryParams.put("lastname", "Brown");
        queryParams.put("checkin", "2013-02-23");
        queryParams.put("checkout", "2014-10-23");

        Response allBookings = RestService.getWithParams(PATH_BOOKING, queryParams);
        JsonPath jsonResponse = allBookings.jsonPath();
        assertEquals(HttpStatus.SC_OK, allBookings.statusCode());

    }

}
