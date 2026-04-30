package Final;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class FrontDesk extends Staff {

    void registerNewCustomer(Customer customer) {


        if (customer == null) {
            System.out.println("Bad inputs!!");
        }

       else if(customerList.isEmpty()) {
            customerList.add(customer);
        }

        else if(customer != null)  {
            for (int i = 0; i < customerList.size(); i++) {

                if(customerList.get(i).getNationalID() == customer.getNationalID()) {
                    System.out.println("The Customer should not have duplicate National ID");
                }

                else {
                    customerList.add(customer);

                }

            }
        }
    }

    void calculateCustomerServiceFee(int nationalId) {

        if (customerList.isEmpty()) {
            System.out.println("There is no customer");
        }

        else {
            for (int i = 0; i < customerList.size(); i++) {
                if (nationalId == customerList.get(i).getNationalID()) {
                    System.out.print("The Service fee for this customer is equal: ");
                    System.out.println(customerList.get(i).getServiceFee());
                    break;
                }

                else {
                    System.out.println("There is no customer with this National ID");
                }
            }
        }
    }
}
