package Final;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Technician extends Staff {

    Scanner scanner = new Scanner(System.in);
    int choice;

    void addDiagnosticResultReport(){
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

                    System.out.println("Enter the Diagnostic Result");
                    String diagnosticResults = scanner.nextLine();

                    appointmentList.get(i).setDiagnosticReport(diagnosticResults);
                }
            }
        }
    }

    void generateReport() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

        for (Appointment a : appointmentList) {
            if (!a.getRepairsPerformed().equals("Waiting to Enter the garage") &&
                    !a.getDiagnosticReport().equals("Waiting for the Technician")) {

                // File name based on national ID and appointment time
                String fileName = "appointment_" + a.getNationalID() + "_" + a.getStart().format(DateTimeFormatter.ofPattern("HHmm")) + ".txt";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    writer.write("Customer NID       : " + a.getNationalID());
                    writer.newLine();
                    writer.write("Car Plate Number   : " + a.getCarPlate());
                    writer.newLine();
                    writer.write("Repair Notes       : " + a.getRepairsPerformed());
                    writer.newLine();
                    writer.write("Diagnostic Report  : " + a.getDiagnosticReport());
                    writer.newLine();
                    writer.write("Appointment Time   : " + a.getStart().format(timeFormat) +
                            " - " + a.getEnd().format(timeFormat));
                    writer.newLine();
                    writer.write("--------------------------------------------------");
                    writer.newLine();

                    System.out.println("✓ Report generated: " + fileName);
                } catch (IOException e) {
                    System.err.println("× Failed to write report for NID " + a.getNationalID() + ": " + e.getMessage());
                }
            }
        }
    }
}
