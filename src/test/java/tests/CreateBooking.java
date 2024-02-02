package tests;

import dto.BookingDTO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CreateService;
import service.RestService;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBooking {

    @Test
    @DisplayName("Cadastrar Reserva")
    void createBookingSuccess() {
        BookingDTO bookingRequest = CreateService.CreateBooking();

        Response response = RestService.post(bookingRequest, "/booking");

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath jsonResponse = response.jsonPath();

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
    @DisplayName("Cadastrar uma Reserva sem Body")
    void createBookingNoBody() {
        Response response = RestService.post(null, "/booking");
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }
}
