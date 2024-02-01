package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CreateService;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBooking {

    @Test
    @DisplayName("Cadastrar Reserva")
    void CreateBookingSuccess() {
        Response booking = CreateService.CreateBooking();

        JsonPath jsonResponse = booking.jsonPath();

        assertEquals(200, booking.statusCode()); // 201 Created?

        Map<String, Object> bookingData = jsonResponse.getMap("booking");

        assertNotNull(bookingData);

        Integer bookingId = jsonResponse.getInt("bookingid");
        String firstName = jsonResponse.getString("booking.firstname");
        String lastName = jsonResponse.getString("booking.lastname");
        Integer totalPrice = jsonResponse.getInt("booking.totalprice");
        Boolean depositPaid = jsonResponse.getBoolean("booking.depositpaid");

        Map<String, String> bookingDates = jsonResponse.getMap("booking.bookingdates");
        String checkinDate = bookingDates.get("checkin");
        String checkoutDate = bookingDates.get("checkout");

        String additionalNeeds = jsonResponse.getString("additionalneeds");

        assertInstanceOf(Integer.class, bookingId);
        assertInstanceOf(String.class, firstName);
        assertInstanceOf(String.class, lastName);
        assertInstanceOf(Integer.class, totalPrice);
        assertInstanceOf(Boolean.class, depositPaid);
        assertInstanceOf(String.class, additionalNeeds);

        assertNotNull(bookingId);
        assertNotNull(firstName);
        assertNotNull(lastName);
        assertNotNull(totalPrice);
        assertNotNull(depositPaid);
        assertNotNull(additionalNeeds);
    }

    @Test
    @DisplayName("Cadastrar Reserva")
    void CreateBookingSuccessNew() {
        Response bookingResponse = CreateService.CreateBooking();

        assertEquals(200, bookingResponse.statusCode());

        JsonPath jsonResponse = bookingResponse.jsonPath();

        Map<String, Object> bookingDataMap = jsonResponse.get("booking");

        Integer bookingId = jsonResponse.getInt("bookingid");
        String firstName = (String) bookingDataMap.get("firstname");
        String lastName = (String) bookingDataMap.get("lastname");
        Integer totalPrice = (Integer) bookingDataMap.get("totalprice");
        Boolean depositPaid = (Boolean) bookingDataMap.get("depositpaid");

        Map<String, String> bookingDates = (Map<String, String>) bookingDataMap.get("bookingdates");
        String checkinDate = bookingDates.get("checkin");
        String checkoutDate = bookingDates.get("checkout");

        String additionalNeeds = (String) bookingDataMap.get("additionalneeds");

        assertInstanceOf(Integer.class, bookingId);
        assertInstanceOf(String.class, firstName);
        assertInstanceOf(String.class, lastName);
        assertInstanceOf(Integer.class, totalPrice);
        assertInstanceOf(Boolean.class, depositPaid);
        assertInstanceOf(String.class, checkinDate);
        assertInstanceOf(String.class, checkoutDate);
        assertInstanceOf(String.class, additionalNeeds);

        assertNotNull(bookingId);
        assertNotNull(firstName);
        assertNotNull(lastName);
        assertNotNull(totalPrice);
        assertNotNull(depositPaid);
        assertNotNull(checkinDate);
        assertNotNull(checkoutDate);
        assertNotNull(additionalNeeds);
    }
}
