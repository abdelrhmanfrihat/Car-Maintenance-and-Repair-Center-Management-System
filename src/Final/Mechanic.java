package Final;

import java.util.Scanner;

public class Mechanic extends Staff{

    Scanner scanner = new Scanner(System.in);
    int choice;

    public void addServiceReport(){

        if (appointmentList.isEmpty()) {
            System.out.println("There is no Appointments");
        }

        else {
            printAppointmentList();

            System.out.println("Choose Appointment");

            choice = scanner.nextInt();
            choice--;

            for (int i = 0; i < appointmentList.size(); i++) {

                if (choice == i) {
                    scanner.nextLine();

                    System.out.println("Enter the repaired performed");
                    String repairPerformed = scanner.nextLine();

                    System.out.println("Enter the partsUsed");
                    String partsUsed = scanner.nextLine();
                    appointmentList.get(i).setRepairsPerformed(repairPerformed);
                    appointmentList.get(i).setPartsUsed(partsUsed);

                }

            }

        }

    }
}
