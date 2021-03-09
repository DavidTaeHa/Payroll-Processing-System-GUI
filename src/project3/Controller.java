package project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that will handle all the user input and output made in the GUI
 *
 * @author David Ha, Andrew McAvoy
 */
public class Controller {

    final static int MANAGER = 1;
    final static int DEPARTMENT_HEAD = 2;
    final static int DIRECTOR = 3;
    final static int MIN_HOURS = 0;
    final static int MAX_HOURS = 100;
    final static int FIRST_PARAMETER = 0;
    final static int SECOND_PARAMETER = 1;
    final static int THIRD_PARAMETER = 2;
    final static int FOURTH_PARAMETER = 3;
    final static int FIFTH_PARAMETER = 4;
    final static int SIXTH_PARAMETER = 5;
    final static int PLACEHOLDER = -1;
    final static int EMPTY = 0;

    @FXML
    private TextField nameField, salaryField, hoursField, hourlyField;

    @FXML
    private DatePicker dateField;

    @FXML
    private HBox managementGroup;

    @FXML
    private ToggleGroup department, employee, management;

    @FXML
    private Button addButton, removeButton, setHoursButton;

    @FXML
    private RadioButton fulltimeButton, parttimeButton, managementButton,
            managerButton, deptHeadButton, directorButton;

    @FXML
    private TextArea outputText, listArea;

    private Company company = new Company();

    /**
     * Helper method used to reset selections for management roles in the case that the employee type is changed
     *
     * @param managerButton  Radio Button for manager
     * @param deptHeadButton Radio Button for department head
     * @param directorButton Radio Button for director
     */
    private void resetManagement(RadioButton managerButton, RadioButton deptHeadButton, RadioButton directorButton) {
        managerButton.setSelected(false);
        deptHeadButton.setSelected(false);
        directorButton.setSelected(false);
    }

    @FXML
    /**
     * Enables the text fields that are required to add a fulltime employee
     *
     * @param event
     */
    void enableFulltime(ActionEvent event) {
        salaryField.setDisable(false);
        hoursField.setDisable(true);
        hourlyField.setDisable(true);
        managementGroup.setDisable(true);
        hourlyField.clear();
        hoursField.clear();
        resetManagement(managerButton, deptHeadButton, directorButton);
    }

    @FXML
    /**
     * Enables the text fields that are required to add a management employee
     *
     * @param event
     */
    void enableManagement(ActionEvent event) {
        salaryField.setDisable(false);
        hoursField.setDisable(true);
        hourlyField.setDisable(true);
        hourlyField.clear();
        hoursField.clear();
        managementGroup.setDisable(false);
    }

    @FXML
    /**
     * Enables the text fields that are required to add a parttime employee
     *
     * @param event
     */
    void enableParttime(ActionEvent event) {
        salaryField.setDisable(true);
        hoursField.setDisable(false);
        hourlyField.setDisable(false);
        managementGroup.setDisable(true);
        salaryField.clear();
        resetManagement(managerButton, deptHeadButton, directorButton);
    }

    @FXML
    /**
     * Checks if hours field is filled and enables the button that is required to set the hours of a parttime employee
     *
     * @param event
     */
    void enableSetHours(KeyEvent event) {
        if (!hoursField.getText().isEmpty()) {
            setHoursButton.setDisable(false);
        } else {
            setHoursButton.setDisable(true);
        }
    }

    /**
     * Helper method to help check and see if required fields are filled to enable the add button in the GUI
     */
    private void enableAdd() {
        if (employee.getSelectedToggle() != null && employee.getSelectedToggle().equals(fulltimeButton)) {
            if (!nameField.getText().isEmpty() && department.getSelectedToggle() != null &&
                    dateField.getValue() != null && !salaryField.getText().isEmpty()) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        } else if (employee.getSelectedToggle() != null && employee.getSelectedToggle().equals(parttimeButton)) {
            if (!nameField.getText().isEmpty() && department.getSelectedToggle() != null &&
                    dateField.getValue() != null && !hourlyField.getText().isEmpty()) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        } else if (employee.getSelectedToggle() != null && employee.getSelectedToggle().equals(managementButton)) {
            if (!nameField.getText().isEmpty() && department.getSelectedToggle() != null &&
                    dateField.getValue() != null && !salaryField.getText().isEmpty() &&
                    management.getSelectedToggle() != null) {
                addButton.setDisable(false);
            } else {
                addButton.setDisable(true);
            }
        }
    }

    @FXML
    /**
     * Checks required fields and enables the button that is required to add an employee to the container
     * with key event as parameter
     *
     * @param event
     */
    void enableAddKey(KeyEvent event) {
        enableAdd();
    }

    @FXML
    /**
     * Checks required fields and enables the button that is required to add an employee to the container
     * with mouse event as parameter
     *
     * @param event
     */
    void enableAddMouse(MouseEvent event) {
        enableAdd();
    }

    /**
     * Helper method to check if required fields are filled and enable button to remove employees from the container
     */
    private void enableRemove() {
        if (!nameField.getText().isEmpty() && dateField.getValue() != null && department.getSelectedToggle() != null) {
            removeButton.setDisable(false);
        } else {
            removeButton.setDisable(true);
        }
    }

    @FXML
    /**
     * Method to check if name field, department field, and date field are not null so that the add or remove button
     * can be enable
     */
    void enableAddRemove() {
        enableAdd();
        enableRemove();
    }

    @FXML
    /**
     * Adds new employee to the container
     *
     * @param event
     */
    public void addEmployee(ActionEvent event) {
        Date date = new Date(dateField.getValue().getMonthValue() + "/" + dateField.getValue().getDayOfMonth() +
                "/" + dateField.getValue().getYear());
        if (!date.isValid()) {
            outputText.appendText(date + " is not a valid date!\n");
            return;
        }
        try {
            if ((!salaryField.getText().isEmpty() && Double.parseDouble(salaryField.getText()) < 0) ||
                    (!hourlyField.getText().isEmpty() && Double.parseDouble(hourlyField.getText()) < 0)) {
                outputText.appendText("Salary or hourly pay cannot be negative.\n");
                return;
            }
        } catch (NumberFormatException e) {
            outputText.appendText("Salary or hourly pay must be a number.\n");
            return;
        }
        RadioButton newDepartment = (RadioButton) department.getSelectedToggle();
        Profile profile = new Profile(nameField.getText(), newDepartment.getText(), date);
        if (employee.getSelectedToggle().equals(fulltimeButton)) {
            Fulltime fulltime = new Fulltime(profile, Double.parseDouble(salaryField.getText()));
            if (!company.add(fulltime)) {
                outputText.appendText("Employee is already in the list\n");
            } else {
                outputText.appendText("Employee added.\n");
            }
        } else if (employee.getSelectedToggle().equals(parttimeButton)) {
            Parttime parttime = new Parttime(profile, Double.parseDouble(hourlyField.getText()));
            if (!company.add(parttime)) {
                outputText.appendText("Employee is already in the list\n");
            } else {
                outputText.appendText("Employee added.\n");
            }
        } else if (employee.getSelectedToggle().equals(managementButton)) {
            RadioButton newManagement = (RadioButton) management.getSelectedToggle();
            int managementRole = PLACEHOLDER;
            switch (newManagement.getText()) {
                case "Manager":
                    managementRole = MANAGER;
                    break;
                case "Department Head":
                    managementRole = DEPARTMENT_HEAD;
                    break;
                case "Director":
                    managementRole = DIRECTOR;
            }
            Management management = new Management(profile, Double.parseDouble(salaryField.getText()),
                    managementRole);
            if (!company.add(management)) {
                outputText.appendText("Employee is already in the list\n");
            } else {
                outputText.appendText("Employee added.\n");
            }
        }
    }

    @FXML
    /**
     * Removes employee from the container
     *
     * @param event
     */
    public void removeEmployee(ActionEvent event) {
        Date date = new Date(dateField.getValue().getMonthValue() + "/" + dateField.getValue().getDayOfMonth() +
                "/" + dateField.getValue().getYear());
        RadioButton newDepartment = (RadioButton) department.getSelectedToggle();
        Profile profile = new Profile(nameField.getText(), newDepartment.getText(), date);
        Employee employee = new Employee(profile);
        if (!company.remove(employee)) {
            outputText.appendText("Employee doesn’t exist.\n");
        } else {
            outputText.appendText("Employee removed.\n");
        }
    }

    @FXML
    /**
     * Sets hours worked for a parttime employee
     *
     * @param event
     */
    public void setHours(ActionEvent event) {
        try {
            int hours = Integer.parseInt(hoursField.getText());
            if (hours < MIN_HOURS) {
                outputText.appendText("Working hours cannot be negative.\n");
                return;
            } else if (hours > MAX_HOURS) {
                outputText.appendText("Invalid Hours: over 100\n");
                return;
            }
            Date date = new Date(dateField.getValue().getMonthValue() + "/" + dateField.getValue().getDayOfMonth() +
                    "/" + dateField.getValue().getYear());
            RadioButton newDepartment = (RadioButton) department.getSelectedToggle();
            Profile profile = new Profile(nameField.getText(), newDepartment.getText(), date);
            Parttime parttime = new Parttime(profile, PLACEHOLDER);
            parttime.setHoursWorked(hours);
            if (!company.setHours(parttime)) {
                outputText.appendText("Employee doesn’t exist.\n");
            } else {
                outputText.appendText("Working hours set.\n");
            }
        } catch (NumberFormatException e) {
            outputText.appendText("Number of hours must be a whole number.\n");
        } catch (NullPointerException e){
            outputText.appendText("Must fill in all profile fields to set hour for a parttime employee.\n");
        } catch (ClassCastException e){
            outputText.appendText("Employee is not a parttime employee.\n");
        }
    }

    @FXML
    /**
     * Calculates the payment for all employees within the container
     *
     * @param event
     */
    public void calculate(ActionEvent event) {
        listArea.clear();
        if (company.getNumEmployee() == EMPTY) {
            listArea.appendText("Database is empty.\n");
            return;
        }
        company.processPayments();
        listArea.appendText("Calculation of employee payments is done.\n");
    }

    @FXML
    /**
     * Opens up the file explorer and allows user to import database from a text file to the container
     *
     * @param event
     */
    public void importData(ActionEvent event) {
        try {
            listArea.clear();
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);
            if (!file.getName().endsWith(".txt")) {
                throw new FileNotFoundException();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String employee = scanner.nextLine();
                String[] parameters = employee.split(",");
                String command = parameters[FIRST_PARAMETER];
                Profile profile = new Profile(parameters[SECOND_PARAMETER], parameters[THIRD_PARAMETER],
                        new Date(parameters[FOURTH_PARAMETER]));
                switch (command) {
                    case "P":
                        Parttime parttime = new Parttime(profile, Double.parseDouble(parameters[FIFTH_PARAMETER]));
                        company.add(parttime);
                        break;
                    case "F":
                        Fulltime fulltime = new Fulltime(profile, Double.parseDouble(parameters[FIFTH_PARAMETER]));
                        company.add(fulltime);
                        break;
                    case "M":
                        Management management = new Management(profile, Double.parseDouble(parameters[FIFTH_PARAMETER]),
                                Integer.parseInt(parameters[SIXTH_PARAMETER]));
                        company.add(management);
                }
            }
            scanner.close();
            listArea.appendText("Employee database successfully imported.\n");
        } catch (FileNotFoundException e) {
            listArea.appendText("Invalid file format.\n");
        } catch (ArrayIndexOutOfBoundsException e){
            listArea.appendText("Text file is not formatted correctly for importation.");
        }
    }

    @FXML
    /**
     * Exports database to a text file in the project folder
     *
     * @param event
     */
    public void exportData(ActionEvent event) {
        try {
            listArea.clear();
            if (company.getNumEmployee() == 0) {
                listArea.appendText("Database is empty.\n");
                return;
            }
            listArea.appendText("Database successfully exported.\n");
            company.exportDatabase();
        } catch (IOException e) {
            listArea.appendText("IO exception.\n");
        }
    }

    @FXML
    /**
     * Prints employees from the container in its current state
     *
     * @param event
     */
    public void print(ActionEvent event) {
        listArea.clear();
        if (company.getNumEmployee() == EMPTY) {
            listArea.appendText("Database is empty.\n");
            return;
        }
        listArea.appendText("--Printing earning statements for all employees--\n" + company.print());
    }

    @FXML
    /**
     * Prints employees from the container by date hired
     *
     * @param event
     */
    public void printByDate(ActionEvent event) {
        listArea.clear();
        if (company.getNumEmployee() == EMPTY) {
            listArea.appendText("Database is empty.\n");
            return;
        }
        listArea.appendText("--Printing earning statements by date hired--\n" + company.printByDate());
    }

    @FXML
    /**
     * Prints employees from the container by department
     *
     * @param event
     */
    public void printByDepartment(ActionEvent event) {
        listArea.clear();
        if (company.getNumEmployee() == EMPTY) {
            listArea.appendText("Database is empty.\n");
            return;
        }
        listArea.appendText("--Printing earning statements by department--\n" + company.printByDepartment());
    }
}
