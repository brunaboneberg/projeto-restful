package service;

import com.github.javafaker.Faker;
import dto.BookingDTO;
import dto.LoginDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

public class CreateService {

    private static Faker faker = new Faker();

    @DisplayName("Criar uma Reserva")

    public static Response CreateBooking(){
        LocalDate dateActual = LocalDate.now();

        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        Integer fakeTotalPrice = faker.number().numberBetween(1, 900);
        Boolean fakeDepositPaid = faker.number().numberBetween(0, 2) == 1;

        String firstname = fakeFirstName;
        String lastname = fakeLastName;
        int totalprice = fakeTotalPrice;
        boolean depositpaid = fakeDepositPaid;

        String checkin = dateActual.toString();
        String checkout = dateActual.plusDays(2).toString();
        BookingDTO.BookingDatesDTO bookingDates = new BookingDTO.BookingDatesDTO(checkin, checkout);

        String additionalneeds = "Breakfast";

        BookingDTO requestBody = new BookingDTO(firstname, lastname, totalprice, depositpaid, bookingDates, additionalneeds);

        return RestService.post(requestBody,"/booking");

    }
}
