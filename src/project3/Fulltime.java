package project3;

import java.text.DecimalFormat;

/**
 * This class extends the employee class and contains data elements and operations specific to a full-time employee
 *
 * @author David Ha, Andrew McAvoy
 */
public class Fulltime extends Employee {
    private double annualSalary;

    final static double PAYPERIOD = 26.0;

    /**
     * Constructor for Fulltime class
     *
     * @param profile profile for the employee
     */
    public Fulltime(Profile profile, double annualSalary) {
        super(profile);
        this.annualSalary = annualSalary;
    }

    /**
     * Getter method for annual salary
     *
     * @return annualSalary
     */
    public double getAnnualSalary() {
        return annualSalary;
    }

    /**
     * Calculates payment of the employee
     */
    @Override
    public void calculatePayment() {
        super.setPayment(annualSalary / PAYPERIOD);
    }

    /**
     * Prints instance of this class in the following format
     * profile::payment::FULL TIME::annualSalary
     *
     * @return textual representation of this class
     */
    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("$###,###,##0.00");
        return super.toString() + "::FULL TIME::" + "Annual Salary " + formatter.format(annualSalary);
    }

    /**
     * Checks if two full-time employees are equal
     *
     * @param obj fulltime employee to be compared
     * @return true if equal; false if otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fulltime) {
            Fulltime temp = (Fulltime) obj;
            return (super.equals(temp)) && (annualSalary == temp.annualSalary);
        }
        return false;
    }
}
