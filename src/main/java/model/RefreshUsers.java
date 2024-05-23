package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Employees;

public class RefreshUsers {

    private MotorOracle motor;
    private ArrayList<Employees> employeesList; // Lista de empleados

    public RefreshUsers() {
        motor = new MotorOracle();
        employeesList = new ArrayList<>();
    }

    public String convertToJson(ArrayList<Employees> employeesList) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(employeesList);
        return json;
    }

    public ArrayList<Employees> getEmployeesList() {
        return employeesList;
    }

    public String refreshUser() {
        motor.connect();
        String sql = "SELECT EMPLOYEE_ID, FIRSTNAME, SURNAME, EMAIL, STORE_ID FROM EMPLOYEES";
        ResultSet resultSet = motor.executeQuery(sql);

        ArrayList<Employees> employeesList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                String employeeId = resultSet.getString("EMPLOYEE_ID");
                String firstName = resultSet.getString("FIRSTNAME");
                String surName = resultSet.getString("SURNAME");
                String email = resultSet.getString("EMAIL");
                String storeId = resultSet.getString("STORE_ID");

                Employees employee = new Employees(employeeId, firstName, surName, email, storeId);
                employeesList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        motor.disconnect();

        String json = convertToJson(employeesList);
        return json;
    }
}