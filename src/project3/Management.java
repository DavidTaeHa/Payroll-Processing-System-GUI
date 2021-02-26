package project3;

import java.text.DecimalFormat;

/**
 * This class extends the employee class and contains data elements and operations specific to a full-time employee
 * with a management role
 *
 * @author David Ha, Andrew McAvoy
 */
public class Management extends Fulltime {
    private int managementRole;

    final static int MANAGER = 1;
    final static int DEPARTMENT_HEAD = 2;
    final static int DIRECTOR = 3;

    final static double MANAGER_COMP = 5000.0 / 26;
    final static double DEPARTMENT_HEAD_COMP = 9500.0 / 26;
    final static double DIRECTOR_COMP = 12000.0 / 26;

    /**
     * Constructor for Management class
     *
     * @param profile      Profile for the employee
     * @param annualSalary Annual salary of employee
     * @param managementRole Management role of employee
     */
    public Management(Profile profile, double annualSalary, int managementRole) {
        super(profile, annualSalary);
        this.managementRole = managementRole;
    }

    /**
     * Calculates payment of the employee
     */
    @Override
    public void calculatePayment() {
        switch (managementRole) {
            case MANAGER:
                super.setPayment(super.getAnnualSalary() / 26 + MANAGER_COMP);
                break;
            case DEPARTMENT_HEAD:
                super.setPayment(super.getAnnualSalary() / 26 + DEPARTMENT_HEAD_COMP);
                break;
            case DIRECTOR:
                super.setPayment(super.getAnnualSalary() / 26 + DIRECTOR_COMP);
                break;
        }
    }

    /**
     * Prints instance of this class in the following format
     * profile::payment::FULL TIME::annualSalary::Role Compensation
     *
     * @return Textaul representation of an instance of management class
     */
    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("$###,###,##0.00");
        switch (managementRole) {
            case MANAGER:
                return super.toString() + "::Manager Compensation " + formatter.format(MANAGER_COMP);
            case DEPARTMENT_HEAD:
                return super.toString() + "::DepartmentHead Compensation " + formatter.format(DEPARTMENT_HEAD_COMP);
            case DIRECTOR:
                return super.toString() + "::Director Compensation " + formatter.format(DIRECTOR_COMP);
        }
        return "ERROR";
    }

    /**
     * Checks if two management employees are equal
     *
     * @param obj management employee to be compared
     * @return true if equal; false if otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Management) {
            Management temp = (Management) obj;
            return (super.equals(temp)) && (managementRole == temp.managementRole);
        }
        return false;
    }
}
