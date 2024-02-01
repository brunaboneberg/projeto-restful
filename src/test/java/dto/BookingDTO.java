package dto;
public record BookingDTO(
        String firstname,
        String lastname,
        int totalprice,
        boolean depositpaid,
        BookingDatesDTO bookingdates,
        String additionalneeds
) {
    public record BookingDatesDTO(String checkin, String checkout) {
    }
}
