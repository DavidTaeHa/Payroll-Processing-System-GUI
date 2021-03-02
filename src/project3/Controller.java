package project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * Class that will handle all the user input and output
 *
 * @author David Ha, Andrew McAvoy
 */
public class Controller {

    @FXML
    private TextField nameField, salaryField, hoursField, hourlyField;

    @FXML
    private DatePicker dateField;

    @FXML
    private HBox departmentGroup, employeeGroup, managementGroup;

    @FXML
    private ToggleGroup department, employee, management;

    @FXML
    private Button addButton, removeButton, setHoursButton, computeButton;

    @FXML
    private MenuItem importButton, exportButton, printButton, printDateButton, printDepartmentButton;

    @FXML
    private RadioButton fulltimeButton, parttimeButton, managementButton;

    @FXML
    /**
     * Enables the text fields that are required to add a fulltime employee
     */
    void enableFulltime(ActionEvent event) {

    }

    @FXML
    /**
     * Enables the text fields that are required to add a management employee
     */
    void enableManagement(ActionEvent event) {

    }

    @FXML
    /**
     * Enables the text fields that are required to add a parttime employee
     */
    void enableParttime(ActionEvent event) {

    }

    @FXML
    /**
     * Adds employee to the container
     *
     * @param mouseEvent
     */
    public void addEmployee(MouseEvent mouseEvent) {
    }

    @FXML
    /**
     * Removes employee from the container
     *
     * @param mouseEvent
     */
    public void removeEmployee(MouseEvent mouseEvent) {
    }

    @FXML
    /**
     * Sets hours for a parttime employee
     *
     * @param mouseEvent
     */
    public void setHours(MouseEvent mouseEvent) {

    }

    @FXML
    /**
     * Imports database from a text file to the container
     *
     * @param actionEvent
     */
    public void importData(ActionEvent actionEvent) {
    }

    @FXML
    /**
     * Exports database to a text file
     *
     * @param actionEvent
     */
    public void exportData(ActionEvent actionEvent) {
    }

    @FXML
    /**
     * Prints employees from the container
     *
     * @param actionEvent
     */
    public void print(ActionEvent actionEvent) {
    }

    @FXML
    /**
     * Prints employees from the container by date hired
     *
     * @param actionEvent
     */
    public void printByDate(ActionEvent actionEvent) {
    }

    @FXML
    /**
     * Prints employees from the container by department
     *
     * @param actionEvent
     */
    public void printByDepartment(ActionEvent actionEvent) {
    }
}
