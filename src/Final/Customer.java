package Final;

public class Customer {

    private String fullName;
    private String address;
    private int nationalID;
    private int contactNumber;
    private String carType;
    private String carModel;
    private String carPlate;
    protected double serviceFee = 50;


    public Customer(String fullName, String address, int nationalID,
                    int contactNumber, String carType,
                    String carModel, String carPlate) {

        this.fullName = fullName;
        this.address = address;
        this.nationalID = nationalID;
        this.contactNumber = contactNumber;
        this.carType = carType;
        this.carModel = carModel;
        this.carPlate = carPlate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public int getNationalID() {
        return nationalID;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getCarType() {
        return carType;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationalID(int nationalID) {
        this.nationalID = nationalID;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
}
