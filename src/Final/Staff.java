package Final;

import java.util.ArrayList;
import java.util.List;

public class Staff {

   static protected List<Appointment> appointmentList = new ArrayList<>();
   static protected List<Customer> customerList = new ArrayList<>();

   void printAppointmentList() {

      if (appointmentList.isEmpty()) {
         System.out.println("There is no Appointments");
      }

      else {
            String bar = "----------------------------------------------------------------------";

            for (Appointment a : appointmentList) {
               System.out.println(bar);

               // ── first line: ID, date, start–end ────────────────────────────────
               System.out.printf("ID: %-4d   Date: %s   Time: %s–%s%n",
                       a.getAppointmentID(), a.getDay(), a.getStart(), a.getEnd());

               // ── second line: customer + national ID ───────────────────────────
               System.out.printf("Customer: %-20s   NID: %d%n",
                       a.getCustomerName(), a.getNationalID());

               // ── third line: car details ───────────────────────────────────────
               System.out.printf("Car: %-10s  Model: %-10s  Plate: %-10s%n",
                       a.getCustomerCarType(), a.getCustomerCarModel(), a.getCarPlate());

               // ── fourth line: status texts ─────────────────────────────────────
               System.out.println("Repairs Performed : " + a.getRepairsPerformed());
               System.out.println("Parts Used        : " + a.getPartsUsed());
               System.out.println("Diagnostic Report : " + a.getDiagnosticReport());
            }

            System.out.println(bar);  // final closing line
          }
      }
}
