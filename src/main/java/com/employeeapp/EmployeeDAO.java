package com.employeeapp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class EmployeeDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/employeedb?useSSL=false&serverTimezone=UTC";
    private final String jdbcUsername = "employeeuser";
    private final String jdbcPassword = "employeepass";

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employees (first_name, last_name, email, phone_number, department, salary) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employees WHERE id = ?";
    private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, department = ?, salary = ? WHERE id = ?";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    public void insertEmployee(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getDepartment());
            preparedStatement.setDouble(6, employee.getSalary());

            preparedStatement.executeUpdate();
        }
    }

    public List<Employee> selectAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("department"),
                    rs.getDouble("salary")
                ));
            }
        }
        return employees;
    }

    public void updateEmployee(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getDepartment());
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setInt(7, employee.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_SQL)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}