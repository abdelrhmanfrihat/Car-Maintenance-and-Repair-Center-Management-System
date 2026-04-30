package Final;

import java.time.LocalDate;
import java.time.LocalTime;


public class Appointment {
    static int appointmentNumber = 1;
    private int appointmentID;
    private LocalDate day;
    private LocalTime start,end;
    private int nationalID;
    private String customerName;
    private String customerCarType;
    private String customerCarModel;
    private String carPlate;
    private String repairsPerformed = "Waiting to Enter the garage";
    private String partsUsed = "Waiting to Enter the garage";
    private String DiagnosticReport = "Waiting for the Technician";

    public Appointment(LocalDate day, LocalTime start, LocalTime end,
                       int nationalID,
                       String carPlate, String customerName,
                       String customerCarType, String customerCarModel)
    {
        this.appointmentID = appointmentNumber;
        this.day = day;
        this.start = start;
        this.end = end;
        this.nationalID = nationalID;
        this.carPlate = carPlate;
        this.customerName = customerName;
        appointmentNumber++;
        this.customerCarModel = customerCarModel;
        this.customerCarType = customerCarType;

    }


    public int getAppointmentID() {
        return appointmentID;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getStart() {
        return start;
    }
    public LocalTime getEnd() {
        return end;
    }

    public int getNationalID() {
        return nationalID;
    }

    public String getCarPlate() {
        return carPlate;
    }
    public String getDiagnosticReport() {
        return DiagnosticReport;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRepairsPerformed() {
        return repairsPerformed;
    }

    public String getPartsUsed() {
        return partsUsed;
    }

    public String getCustomerCarType() {
        return customerCarType;
    }

    public String getCustomerCarModel() {
        return customerCarModel;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setNationalID(int nationalID) {
        this.nationalID = nationalID;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void setDiagnosticReport(String diagnosticReport) {
        DiagnosticReport = diagnosticReport;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPartsUsed(String partsUsed) {
        this.partsUsed = partsUsed;
    }

    public void setRepairsPerformed(String repairsPerformed) {
        this.repairsPerformed = repairsPerformed;
    }

    public void setCustomerCarType(String customerCarType) {
        this.customerCarType = customerCarType;
    }

    public void setCustomerCarModel(String customerCarModel) {
        this.customerCarModel = customerCarModel;
    }
}
