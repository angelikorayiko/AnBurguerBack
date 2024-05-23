package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    private MotorOracle motor;
    private boolean userLogged;
    private boolean isEmployee;

    public Login() {
        motor = new MotorOracle();
    }

    public void loginUser(String correo, String contrasena) {
        motor.connect();
        String sql = "SELECT EMAIL, PASSWORD FROM CLIENTS WHERE EMAIL = '" + correo + "' AND PASSWORD = '" + contrasena + "'";
        ResultSet resultSet = motor.executeQuery(sql);

        try {
            if (resultSet.next()) {
                System.out.println("Login successful");
                userLogged = true;
            } else {
                System.out.println("Invalid email or password");
                userLogged = false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            motor.disconnect();
        }

        motor.connect();
        sql = "SELECT EMAIL, PASSWORD FROM EMPLOYEES WHERE EMAIL = '" + correo + "' AND PASSWORD = '" + contrasena + "'";
        resultSet = motor.executeQuery(sql);
        try {
            if (resultSet.next()) {
                isEmployee = true;
            } else {
                isEmployee = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            motor.disconnect();
        }

    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public boolean isUserLogged() {
        return userLogged;
    }
}
