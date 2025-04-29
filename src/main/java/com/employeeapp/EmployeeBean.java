package com.employeeapp;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {

    private Employee employee; // For form inputs
    private Employee selectedEmployee; // For table row selection
    private List<Employee> employees;

    @Inject
    private EmployeeDAO employeeDAO;

    public Employee getEmployee() {
        if (employee == null) {
            employee = new Employee();
        }
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<Employee> getEmployees() {
        if (employees == null) {
            try {
                employees = employeeDAO.selectAllEmployees();
            } catch (SQLException e) {
                handleException(e);
            }
        }
        return employees;
    }

    public String saveEmployee() {
        try {
            employeeDAO.insertEmployee(employee);
            clearForm();
            addMessage("Employee added successfully!");
            return null;
        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    public String updateEmployee() {
        try {
            employeeDAO.updateEmployee(employee);
            clearForm();
            addMessage("Employee updated successfully!");
            return null;
        } catch (SQLException e) {
            handleException(e);
            return null;
        }
    }

    public void deleteEmployee(Employee emp) {
        try {
            employeeDAO.deleteEmployee(emp.getId());
            clearForm();
            addMessage("Employee deleted successfully!");
        } catch (SQLException e) {
            handleException(e);
        }
    }

    public void deleteSelectedEmployee() {
        if (selectedEmployee != null) {
            deleteEmployee(selectedEmployee);
        } else {
            addMessage("Please select an employee to delete.");
        }
    }

    public void clearForm() {
        this.employee = new Employee();
        this.selectedEmployee = null;
        this.employees = null;
    }

    private void addMessage(String summary) {
        FacesContext.getCurrentInstance()
            .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }

    private void handleException(Exception e) {
        FacesContext.getCurrentInstance()
            .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
        e.printStackTrace();
    }
}
