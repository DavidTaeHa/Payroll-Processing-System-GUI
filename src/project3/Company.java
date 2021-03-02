package project3;

/**
 * This class represents a company container that will contain instances of employees
 *
 * @author David Ha, Andrew McAvoy
 */
public class Company {
    private Employee[] emplist;
    private int numEmployee;

    final static int LIST_INCREMENT_VALUE = 4;
    final static int INVALID = -1;

    /**
     * Constructor for company class
     */
    public Company() {
        emplist = new Employee[LIST_INCREMENT_VALUE];
        numEmployee = 0;
    }

    /**
     * Getter method for number of employees
     * @return numEmployee number of employees
     */
    public int getNumEmployee() {
        return numEmployee;
    }

    /**
     * Helper method to help find an employee in the employee container
     *
     * @param employee Employee to be found
     * @return index of the employee in the container; -1 if not found
     */
    private int find(Employee employee) {
        for (int index = 0; index < emplist.length; index++) {
            if ((emplist[index] != null) && emplist[index].getProfile().equals(employee.getProfile())) {
                return index;
            }
        }
        return INVALID;
    }

    /**
     * Helper method to increase capacity of array if the container if full
     */
    private void grow() {
        Employee[] temp = new Employee[emplist.length + LIST_INCREMENT_VALUE];
        for (int i = 0; i < emplist.length; i++) {
            temp[i] = emplist[i];
        }
        emplist = temp;
    }

    /**
     * Helper method to check if an employee profile already exists in the container
     *
     * @param employee value to check if a duplicate exists
     * @return true if duplicate; false if otherwise
     */
    private boolean isDuplicate(Employee employee) {
        for (int i = 0; i < emplist.length; i++) {
            if ((emplist[i] != null) && (emplist[i].getProfile().equals(employee.getProfile()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new employee to the container
     *
     * @param employee employee to be added
     * @return true if successfully added; false if already exists
     */
    public boolean add(Employee employee) {
        if (isDuplicate(employee)) {
            return false;
        }
        emplist[numEmployee] = employee;
        numEmployee++;
        if (numEmployee == emplist.length) {
            grow();
        }
        return true;
    }

    /**
     * Removes employee from container
     *
     * @param employee employee to be removed
     * @return true if successfully removed; false if employee does not exist
     */
    public boolean remove(Employee employee) {
        int index = find(employee);
        if (index == INVALID) {
            return false;
        }
        emplist[index] = null;
        numEmployee--;
        for (int i = index; i < (emplist.length - 1); i++) {
            emplist[i] = emplist[i + 1];
        }
        return true;
    }

    /**
     * Sets number of hours for a parttime employee
     *
     * @param employee employee's hours to be modified
     * @return true if successfully modified; false if employee does not exist
     */
    public boolean setHours(Employee employee) {
        int index = find(employee);
        if (index == INVALID) {
            return false;
        }
        if (employee instanceof Parttime) {
            Parttime temp = (Parttime) emplist[index];
            temp.setHoursWorked(((Parttime) employee).getHoursWorked());
            emplist[index] = temp;
            return true;
        }
        return false;
    }

    /**
     * Calculates payments for all employees in the container
     */
    public void processPayments() {
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] != null) {
                emplist[i].calculatePayment();
            }
        }
    }

    /**
     * Prints earning statements for all employees
     */
    public String print() {
        String finalText = "";
        for (Employee employee : emplist) {
            if (employee != null) {
                finalText = finalText + employee + "\n";
            }
        }
        if(finalText.equals("")){
            return "**Employee database is empty**";
        }
        return "--Printing earning statements for all employees--\n" + finalText;
    }

    /**
     * Prints earning statements by department
     */
    public String printByDepartment() {
        int min = INVALID;
        for (int i = 0; i < emplist.length; i++) {
            for (int j = i; j < emplist.length; j++) {
                if (min == INVALID) {
                    min = j;
                    continue;
                }
                if (emplist[j] == null) {
                    continue;
                }
                if (emplist[j].getProfile().getDepartment().compareTo(emplist[min].getProfile().getDepartment()) < 0) {
                    min = j;
                }
            }
            Employee temp = emplist[i];
            emplist[i] = emplist[min];
            emplist[min] = temp;
            min = INVALID;
        }
        return "--Printing earning statements by department--\n" + print();
    }

    /**
     * Prints earning statements by date hired
     */
    public String printByDate() {
        int min = INVALID;
        for (int i = 0; i < emplist.length; i++) {
            for (int j = i; j < emplist.length; j++) {
                if (min == INVALID) {
                    min = j;
                    continue;
                }
                if (emplist[j] == null) {
                    continue;
                }
                if (emplist[j].getProfile().getDateHired().compareTo(emplist[min].getProfile().getDateHired()) == -1) {
                    min = j;
                }
            }
            Employee temp = emplist[i];
            emplist[i] = emplist[min];
            emplist[min] = temp;
            min = INVALID;
        }
        return "--Printing earning statements by date hired--\n" + print();
    }
}
