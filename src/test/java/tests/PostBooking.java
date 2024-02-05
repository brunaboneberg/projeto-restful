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
import static util.staticValue.PATH_BOOKING;

public class PostBooking {

    @Test
    @DisplayName("Cadastra uma reserva e valida os dados")
    void postBookingSuccess() {
        BookingDTO bookingRequest = CreateService.CreateBooking();

        Response response = RestService.post(bookingRequest, PATH_BOOKING);

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        JsonPath jsonResponse = response.jsonPath();

        assertEquals(bookingRequest.firstname(), jsonResponse.getString("booking.firstname"));
        assertEquals(bookingRequest.lastname(), jsonResponse.getString("booking.lastname"));
        assertEquals(bookingRequest.totalprice(), jsonResponse.getInt("booking.totalprice"));
        assertEquals(bookingRequest.depositpaid(), jsonResponse.getBoolean("booking.depositpaid"));
        assertEquals(bookingRequest.bookingdates().checkin(), jsonResponse.getString("booking.bookingdates.checkin"));
        assertEquals(bookingRequest.bookingdates().checkout(), jsonResponse.getString("booking.bookingdates.checkout"));
        assertEquals(bookingRequest.additionalneeds(), jsonResponse.getString("booking.additionalneeds"));
    }

    @Test
    @DisplayName("Cadastra uma reserva sem body")
    void postBookingNoBody() {
        Response response = RestService.post(null, PATH_BOOKING);
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

}
