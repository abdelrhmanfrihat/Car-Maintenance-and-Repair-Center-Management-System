package Final;


public class CenterStaffMembers extends Customer {

    CenterStaffMembers(String fullName, String address, int nationalID,
                       int contactNumber, String carType,
                       String carModel, String carPlate)

    {
        super(fullName, address, nationalID, contactNumber,
                carType, carModel, carPlate);
    }

    @Override
    public double getServiceFee() {
        return applyDiscount();

    }

    double applyDiscount()
    {
       return serviceFee * 0.5;
    }
}
