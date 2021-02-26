package project3;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

public class ManagementTest {

    @org.junit.jupiter.api.Test
    void calculatePayment() {
        Profile profile = new Profile ("Doe,John", "CS", new Date());
        Management manager = new Management(profile, 85000, 1);
        Management deptHead = new Management(profile, 85000, 2);
        Management director = new Management(profile, 85000, 3);
        Management zeroManager = new Management(profile, 0, 1);
        Management zeroDeptHead = new Management(profile, 0, 2);
        Management zeroDirector = new Management(profile, 0, 3);
        DecimalFormat formatter = new DecimalFormat("$###,###,##0.00");

        //Testing calculate payment method for manager instance of Management class
        manager.calculatePayment();
        assertEquals("$3,461.54", formatter.format(manager.getPayment()));

        //Testing calculate payment method for department head instance of Management class
        deptHead.calculatePayment();
        assertEquals("$3,634.62", formatter.format(deptHead.getPayment()));

        //Testing calculate payment method for director instance of Management class
        director.calculatePayment();
        assertEquals("$3,730.77", formatter.format(director.getPayment()));

        //Testing edge case payment for Manager with $0 salary
        zeroManager.calculatePayment();
        assertEquals("$192.31", formatter.format(zeroManager.getPayment()));

        //Testing edge case payment for Department Head with $0 salary
        zeroDeptHead.calculatePayment();
        assertEquals("$365.38", formatter.format(zeroDeptHead.getPayment()));

        //Testing edge case payment for Director with $0 salary
        zeroDirector.calculatePayment();
        assertEquals("$461.54", formatter.format(zeroDirector.getPayment()));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Date date = new Date ("11/10/2019");
        Profile profile = new Profile ("Doe,John", "CS", date);
        Management manager = new Management(profile, 85000, 1);
        Management deptHead = new Management(profile, 85000, 2);
        Management director = new Management(profile, 85000, 3);

        //Testing toString method for manager instance
        assertEquals("Doe,John::CS::11/10/2019::Payment $0.00::FULL TIME::" +
                        "Annual Salary $85,000.00::Manager Compensation $192.31", manager.toString());

        //Testing toString method for department head instance
        assertEquals("Doe,John::CS::11/10/2019::Payment $0.00::FULL TIME::" +
                "Annual Salary $85,000.00::DepartmentHead Compensation $365.38", deptHead.toString());

        //Testing toString method for director instance
        assertEquals("Doe,John::CS::11/10/2019::Payment $0.00::FULL TIME::" +
                "Annual Salary $85,000.00::Director Compensation $461.54", director.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Date date = new Date ("11/10/2019");
        Profile profile = new Profile ("Doe,John", "CS", date);
        Employee employee = new Employee (profile);
        Management manager1 = new Management(profile, 85000, 1);
        Management manager2 = new Management(profile, 85000, 1);
        Management manager3 = new Management(profile, 85000, 2);

        //Test case for Management class being compared with a non-instance of Management class
        assertFalse(manager2.equals(employee));

        //Test case for two equal instances of Management being compared with one another
        assertTrue(manager1.equals(manager2));

        //Test cases for two different instances of Management being compared with each other
        assertFalse(manager1.equals(manager3));
    }
}