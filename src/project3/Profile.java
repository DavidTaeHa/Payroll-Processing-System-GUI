package project3;

/**
 * This class defines the profile of an employee
 *
 * @author David Ha, Andrew McAvoy
 */
public class Profile {
    private String name; //Lastname,Firstname
    private String department;//CS, ECE, IT
    private Date dateHired;

    /**
     * Constructor for profile class
     *
     * @param name Name of Employee
     * @param department Department of Employee
     * @param dateHired Date hired of employee
     */
    public Profile(String name, String department, Date dateHired) {
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     * Getter method for department
     *
     * @return department value
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Getter method for dateHired
     *
     * @return dateHired value
     */
    public Date getDateHired() {
        return dateHired;
    }

    /**
     * Prints instance of this class in the following format
     * name::department::dateHired
     *
     * @return textual representation of profile
     */
    @Override
    public String toString() {
        return name + "::" + department + "::" + dateHired;
    }

    /**
     * Compares two profiles to see if they are equal
     *
     * @param obj represents another profile to compare
     * @return boolean value true if they are equal false if otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile temp = (Profile) obj;
            return ((name.equals(temp.name)) && (department.equals(temp.department))
                    && (dateHired.compareTo(temp.dateHired) == 0));
        }
        return false;
    }
}
