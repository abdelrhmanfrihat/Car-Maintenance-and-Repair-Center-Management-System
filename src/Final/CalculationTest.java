package Final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class CalculationTest {

    @Test
    void testPrivateCarOwnerFee() {
        FrontDesk frontDesk = new FrontDesk();
        PrivateCarOwner customer = new PrivateCarOwner("Ali", "Amman", 111, 123, "Toyota", "Corolla", "A123");
        frontDesk.customerList.add(customer);

        frontDesk.calculateCustomerServiceFee(111);

        assertEquals(50, customer.getServiceFee());
    }

    @Test
    void testCenterStaffMemberFee() {
        FrontDesk frontDesk = new FrontDesk();
        CenterStaffMembers staff = new CenterStaffMembers("Sara", "Irbid", 222, 456, "Nissan", "Sunny", "B456");
        frontDesk.customerList.add(staff);

        frontDesk.calculateCustomerServiceFee(222);

        assertEquals(25, staff.getServiceFee(), 0.01);
    }

    @Test
    void testFleetCompanyClientFee() {
        FrontDesk frontDesk = new FrontDesk();
        FleetCompanyClients client = new FleetCompanyClients("CompanyX", "Zarqa", 333, 789, "Hyundai", "Tucson", "C789");
        frontDesk.customerList.add(client);

        frontDesk.calculateCustomerServiceFee(333);

        assertEquals(35, client.getServiceFee(), 0.01);
    }
}
