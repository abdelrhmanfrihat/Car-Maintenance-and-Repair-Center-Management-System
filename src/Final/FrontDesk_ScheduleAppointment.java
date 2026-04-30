package Final;

import java.time.LocalDate;
import java.time.LocalTime;

public class FrontDesk_ScheduleAppointment extends Staff {

    void scheduleCustomerAppointment(LocalDate day, LocalTime start, LocalTime end, int choice){

        if (customerList.isEmpty()) {
            System.out.println("there is no customer to schedule appointments for");
        }

        else {

            if (!end.equals(start.plusHours(1))) {
                throw new IllegalArgumentException("Appointments must be exactly 1 hour");
            }

            if(start.getHour() < 9 || start.getHour() > 17 || start.getMinute() != 0) {
                throw new IllegalArgumentException("Invalid Time Slot");
            }

            for (Appointment a: appointmentList) {
                if (a.getDay().equals(day) && a.getStart().equals(start)) {
                    throw new IllegalArgumentException("Slot Already Booked");
                }
            }

            for (int i = 0; i < customerList.size(); i++) {

                if (choice == i) {

                    Appointment appointment = new Appointment( day,start,end, customerList.get(i).getNationalID()
                            ,customerList.get(i).getCarPlate(), customerList.get(i).getFullName(),
                            customerList.get(i).getCarType(), customerList.get(i).getCarModel() );
                    appointmentList.add(appointment);
                    break;
                }
            }
        }
    }
}
