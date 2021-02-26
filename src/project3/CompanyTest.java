package project3;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @org.junit.jupiter.api.Test
    void add() {
        Company company = new Company();
        Profile person1 = new Profile("Doe,John", "CS", new Date());
        Profile person2 = new Profile("Smith,Mary", "ECE", new Date());
        Profile person3 = new Profile("McDonald,Ron", "CS", new Date());
        Profile person4 = new Profile("King,Burger", "ECE", new Date());
        Profile person5 = new Profile("Potter,Harry", "ITI", new Date());
        Employee employee1 = new Employee(person1);
        Employee employee2 = new Employee(person2);
        Employee employee3 = new Employee(person3);
        Employee employee4 = new Employee(person4);
        Employee employee5 = new Employee(person5);

        //Test adding employee to empty list
        assertTrue(company.add(employee1));

        //Test adding second employee
        assertTrue(company.add(employee2));

        //Test adding duplicate employee
        assertFalse(company.add(employee2));

        //Test adding more than 4 employees to grow the array
        assertTrue(company.add(employee3));
        assertTrue(company.add(employee4));
        assertTrue(company.add(employee5));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        Company company = new Company();
        Profile person1 = new Profile("Doe,John", "CS", new Date());
        Profile person2 = new Profile("Smith,Mary", "ECE", new Date());
        Employee employee1 = new Employee(person1);
        Employee employee2 = new Employee(person2);

        //Test removing from an empty list
        assertFalse(company.remove(employee1));

        //Test removing non-existing employee
        company.add(employee1);
        assertFalse(company.remove(employee2));

        //Test removing single employee
        assertTrue(company.remove(employee1));

    }

    @org.junit.jupiter.api.Test
    void setHours() {
        Company company = new Company();
        Profile person1 = new Profile("Doe,John", "CS", new Date());
        Profile person2 = new Profile("Smith,Mary", "ECE", new Date());
        Employee employee1 = new Fulltime(person1, 30000);
        Employee employee2 = new Parttime(person2, 12.50);

        //Test setting hours on empty list
        assertFalse(company.setHours(employee2));

        //Test setting hours on non-existing employee
        company.add(employee1);
        assertFalse(company.setHours(employee2));

        //Test setting hours on full time employee
        assertFalse(company.setHours(employee1));

        //Test setting hours on part time employee
        company.add(employee2);
        assertTrue(company.setHours(employee2));
    }
}