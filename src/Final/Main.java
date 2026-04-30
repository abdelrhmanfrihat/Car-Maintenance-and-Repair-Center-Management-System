package Final;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

   static Scanner scanner = new Scanner(System.in);
   static char choice;

    public static void main(String[] args) {
        menu();
    }

    static void menu() {

        FrontDesk frontDesk = new FrontDesk();
        FrontDesk_ScheduleAppointment frontDesk_scheduleAppointment = new FrontDesk_ScheduleAppointment();
        Mechanic mechanic = new Mechanic();
        Technician technician = new Technician();

        while (true) {
            System.out.println("Choose you choice:");
            System.out.println("A-Receptionist");
            System.out.println("B-Mechanic");
            System.out.println("C-Technician");
            System.out.println("D-exit");

            choice = scanner.next().trim().toUpperCase().charAt(0);

            if (choice == 'A') {
                Receptionist(frontDesk, frontDesk_scheduleAppointment);
            }

            else if (choice == 'B') {
                Mechanic(mechanic);
            }

            else if (choice == 'C') {
                Technician(technician);
            }

            else  {
                break;
            }
        }
    }

    static void Technician(Technician technician) {

        while (true) {
            System.out.println("A- Add diagnostic results (issues found, recommended actions):");
            System.out.println("B- Generate a daily report in text format for each serviced car:");

            choice = scanner.next().trim().toUpperCase().charAt(0);

            if (choice == 'A') {
                technician.addDiagnosticResultReport();
            }
            else if (choice == 'B'){
                technician.generateReport();
            }
            else {
                System.out.println("Invalid choice");
                break;
            }
        }

    }

    static void Mechanic(Mechanic mechanic) {

        while (true) {

            System.out.println("A- View list of all scheduled service appointments");
            System.out.println("B- Add service report for each appointment (repairs performed, parts used)");

            choice = scanner.next().trim().toUpperCase().charAt(0);

            if (choice == 'A') {
                mechanic.printAppointmentList();
            }
            else if (choice == 'B'){
                mechanic.addServiceReport();
            }
            else {
                System.out.println("Invalid choice");
                break;
            }
        }
    }

    static void Receptionist(FrontDesk frontDesk, FrontDesk_ScheduleAppointment frontDesk_scheduleAppointment) {

            while (true) {

                System.out.println("A-Register new customers and their cars");
                System.out.println("B-Schedule a same-day service appointment");
                System.out.println("C-Calculate the service bill and print it based on national ID");
                System.out.println("D-Print all daily appointments with customer and car details\n");

                System.out.println("Enter option (A-D): ");

                choice = scanner.next().trim().toUpperCase().charAt(0);

                if(choice == 'A'){
                    newCustomer(frontDesk);
                }

                else if(choice == 'B') {

                    newAppointment(frontDesk_scheduleAppointment);

                }

                else if (choice == 'C') {
                    calculateCustomerServiceFee(frontDesk);
                }

                else if (choice == 'D') {
                    frontDesk.printAppointmentList();
                }

                else {
                    break;
                }
            }
        }

        static void calculateCustomerServiceFee(FrontDesk frontDesk){

        System.out.println("Enter the National Id for the customer:");

            int nationalId = scanner.nextInt();

            frontDesk.calculateCustomerServiceFee(nationalId);

        }

        static void newCustomer ( FrontDesk frontDesk) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("National ID: ");
        int nationalID = scanner.nextInt();
        scanner.nextLine();                  // clear newline

        System.out.print("Contact number: ");
        int contactNumber = scanner.nextInt();
        scanner.nextLine();                  // clear newline

        System.out.print("Car type (e.g., Sedan): ");
        String carType = scanner.nextLine();

        System.out.print("Car model (e.g., Corolla): ");
        String carModel = scanner.nextLine();

        System.out.print("Car plate: ");
        String carPlate = scanner.nextLine();

        System.out.println("Choose your customer");
        System.out.println("1- Private car owner");
        System.out.println("2- Center Staff Member");
        System.out.println("3- Choose your Fleet Company Client");

        int choice = scanner.nextInt();

        while (true)
        if(choice == 1) {
            Customer customer = new PrivateCarOwner(fullName,address,nationalID,contactNumber,carType,carModel,carPlate);
            frontDesk.registerNewCustomer(customer);
            break;
        }
        else if (choice == 2) {
            Customer customer = new CenterStaffMembers(fullName,address,nationalID,contactNumber,carType,carModel,carPlate);
            frontDesk.registerNewCustomer(customer);
            break;
        }
        else if (choice == 3) {
            Customer customer = new FleetCompanyClients(fullName,address,nationalID,contactNumber,carType,carModel,carPlate);
            frontDesk.registerNewCustomer(customer);
            break;
        }
        else{
            System.out.println("Wrong choice");
        }

    }

   static void newAppointment(FrontDesk_ScheduleAppointment frontDesk){

       System.out.println("Select which customer:");

       for (int i = 0; i < frontDesk.customerList.size(); i++) {
           System.out.println( (i + 1) + "- " + frontDesk.customerList.get(i).getFullName());
       }

       int customerChoice = scanner.nextInt();

       customerChoice--;

        System.out.println("Enter the day");
        int day = scanner.nextInt();
        System.out.println("Enter the month");
        int month = scanner.nextInt();
        System.out.println("Enter the the start hour");
        int startHour = scanner.nextInt();
        System.out.println("Enter the end hour");
        int endHour = scanner.nextInt();

        try {
            frontDesk.scheduleCustomerAppointment(LocalDate.of(2025, month, day),
                    LocalTime.of(startHour, 0),
                    LocalTime.of(endHour, 0), customerChoice);
        }
        catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}
