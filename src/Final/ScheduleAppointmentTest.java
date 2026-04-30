package Final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleAppointmentTest {

    @Test
    void testValidTimeSlot() {
        FrontDesk_ScheduleAppointment scheduler = new FrontDesk_ScheduleAppointment();

        scheduler.customerList.add(new PrivateCarOwner("Ali", "Amman", 111, 123, "Toyota", "Corolla", "A123"));

        // Valid time: 10:00 to 11:00
        LocalDate date = LocalDate.of(2025, 6, 10);
        LocalTime start = LocalTime.of(10, 0);
        LocalTime end = LocalTime.of(11, 0);

        scheduler.scheduleCustomerAppointment(date, start, end, 0);

        assertEquals(1, scheduler.appointmentList.size());
    }

    @Test
    void testInvalidTimeSlot_NotFullHour() {
        FrontDesk_ScheduleAppointment scheduler = new FrontDesk_ScheduleAppointment();

        scheduler.customerList.add(new PrivateCarOwner("Ali", "Amman", 111, 123, "Toyota", "Corolla", "A123"));

        // Invalid time: 10:00 to 11:30
        LocalDate date = LocalDate.of(2025, 6, 10);
        LocalTime start = LocalTime.of(10, 0);
        LocalTime end = LocalTime.of(11, 30);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            scheduler.scheduleCustomerAppointment(date, start, end, 0);
        });

        assertEquals("Appointments must be exactly 1 hour", e.getMessage());
    }

    @Test
    void testInvalidTimeSlot_OutOfWorkingHours() {
        FrontDesk_ScheduleAppointment scheduler = new FrontDesk_ScheduleAppointment();

        scheduler.customerList.add(new PrivateCarOwner("Ali", "Amman", 111, 123, "Toyota", "Corolla", "A123"));


        // Invalid time: 8:00 to 9:00 (before working hours)
        LocalDate date = LocalDate.of(2025, 6, 10);
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(9, 0);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            scheduler.scheduleCustomerAppointment(date, start, end, 0);
        });

        assertEquals("Invalid Time Slot", e.getMessage());
    }

    @Test
    void testSlotAlreadyBooked() {
        FrontDesk_ScheduleAppointment scheduler = new FrontDesk_ScheduleAppointment();

        PrivateCarOwner customer = new PrivateCarOwner("Ali", "Amman", 111, 123, "Toyota", "Corolla", "A123");
        scheduler.customerList.add(customer);

        // Existing appointment at 10:00
        LocalDate date = LocalDate.of(2025, 6, 10);
        LocalTime start = LocalTime.of(10, 0);
        LocalTime end = LocalTime.of(11, 0);
        Appointment existing = new Appointment(date, start, end, 111, "A123", "Ali", "Toyota", "Corolla");

        scheduler.appointmentList.add(existing);

        // Try to book the same slot again
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            scheduler.scheduleCustomerAppointment(date, start, end, 0);
        });

        assertEquals("Slot Already Booked", e.getMessage());
    }
}
