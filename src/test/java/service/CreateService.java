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

    public static BookingDTO CreateBooking(){
        LocalDate dateActual = LocalDate.now();

        return new BookingDTO (
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(1, 900),
                true,
                new BookingDTO.BookingDatesDTO(
                        dateActual.toString(),
                        dateActual.plusDays(2).toString()
                ),
                "Breakfast"

        );
    }
}
