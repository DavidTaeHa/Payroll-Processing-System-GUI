package project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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
    private HBox managementGroup;

    @FXML
    private ToggleGroup department, employee, management;

    @FXML
    private Button addButton, removeButton, setHoursButton, computeButton;

    @FXML
    private MenuItem importButton, exportButton, printButton, printDateButton, printDepartmentButton;

    @FXML
    private RadioButton fulltimeButton, parttimeButton, managementButton,
            managerButton, deptHeadButton, directorButton;

    @FXML
    private TextArea outputText;

    private Company company = new Company();

    private void resetManagement(RadioButton managerButton, RadioButton deptHeadButton, RadioButton directorButton){
        managerButton.setSelected(false);
        deptHeadButton.setSelected(false);
        directorButton.setSelected(false);
    }

    @FXML
    /**
     * Enables the text fields that are required to add a fulltime employee
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
     * Enables the button that is required to set the hours of a parttime employee
     */
    void enableSetHours(KeyEvent event) {
        if(!hoursField.getText().isEmpty()){
            setHoursButton.setDisable(false);
        }
        else{
            setHoursButton.setDisable(true);
        }
    }

    /**
     * Helper method to see if required fields are filled to enable the add button in the GUI
     */
    private void enableAdd(){
        if(employee.getSelectedToggle() != null && employee.getSelectedToggle().equals(fulltimeButton)){
            if(!nameField.getText().isEmpty() && department.getSelectedToggle() != null &&
                    dateField.getValue() != null && !salaryField.getText().isEmpty()){
                addButton.setDisable(false);
            }
            else{
                addButton.setDisable(true);
            }
        }
        else if(employee.getSelectedToggle() != null && employee.getSelectedToggle().equals(parttimeButton)){
            if(!nameField.getText().isEmpty() && department.getSelectedToggle() != null &&
                    dateField.getValue() != null && !hourlyField.getText().isEmpty()){
                addButton.setDisable(false);
            }
            else{
                addButton.setDisable(true);
            }
        }
        else if(employee.getSelectedToggle() != null && employee.getSelectedToggle().equals(managementButton)){
            if(!nameField.getText().isEmpty() && department.getSelectedToggle() != null &&
                    dateField.getValue() != null && !salaryField.getText().isEmpty() &&
                    management.getSelectedToggle() != null){
                addButton.setDisable(false);
            }
            else{
                addButton.setDisable(true);
            }
        }
    }

    @FXML
    /**
     * Checks required fields and enables the button that is required to add an employee to the container
     * with key event as parameter
     */
    void enableAddKey(KeyEvent event) {
        enableAdd();
    }

    @FXML
    /**
     * Checks required fields and enables the button that is required to add an employee to the container
     * with mouse event as parameter
     */
    void enableAddMouse(MouseEvent event) {
        enableAdd();
    }

    /**
     * Helper method to check if required fields are filled and enable button to remove employees from the container
     */
    private void enableRemove(){
        if(!nameField.getText().isEmpty() && dateField.getValue() != null && department.getSelectedToggle() != null){
            removeButton.setDisable(false);
        }
        else{
            removeButton.setDisable(true);
        }
    }

    @FXML
    /**
     * Method to check if name field, department field, and date field are not null so that the add or remove button
     * can be enable
     */
    void enableAddRemove(){
        enableAdd();
        enableRemove();
    }

    @FXML
    /**
     * Adds employee to the container
     *
     * @param mouseEvent
     */
    public void addEmployee(ActionEvent event) {
    }

    @FXML
    /**
     * Removes employee from the container
     *
     * @param mouseEvent
     */
    public void removeEmployee(ActionEvent event) {
    }

    @FXML
    /**
     * Sets hours for a parttime employee
     *
     * @param mouseEvent
     */
    public void setHours(ActionEvent event) {

    }

    @FXML
    /**
     * Imports database from a text file to the container
     *
     * @param actionEvent
     */
    public void importData(ActionEvent event) {
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
    public void print(ActionEvent event) {
    }

    @FXML
    /**
     * Prints employees from the container by date hired
     *
     * @param actionEvent
     */
    public void printByDate(ActionEvent event) {
    }

    @FXML
    /**
     * Prints employees from the container by department
     *
     * @param actionEvent
     */
    public void printByDepartment(ActionEvent event) {
    }
}
