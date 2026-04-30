package Final;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class RegisterCustomerTest {

    @Test
    void testAddCustomerToEmptyList() {
        FrontDesk frontDesk = new FrontDesk();

        Customer customer = new Customer("Ali", "Amman", 123, 123, "Toyota", "Corolla", "A123");

        frontDesk.registerNewCustomer(customer);

        assertEquals(1, frontDesk.customerList.size());
        assertEquals(123, frontDesk.customerList.get(0).getNationalID());
    }

    @Test
    void testNullCustomerNotAdded() {
        FrontDesk frontDesk = new FrontDesk();

        frontDesk.registerNewCustomer(null);

        assertEquals(0, frontDesk.customerList.size());
    }

    @Test
    void testDuplicateCustomerNotAdded() {
        FrontDesk frontDesk = new FrontDesk();

        Customer customer1 = new Customer("Ali", "Amman", 123, 123, "Toyota", "Corolla", "A123");
        Customer customer2 = new Customer("Ali Copy", "Zarqa", 123, 456, "Kia", "Rio", "B456");

        frontDesk.customerList.add(customer1);
        frontDesk.registerNewCustomer(customer2);

        // Should not add duplicate ID
        assertEquals(1, frontDesk.customerList.size());
        assertEquals("Ali", frontDesk.customerList.get(0).getFullName());
    }
}
