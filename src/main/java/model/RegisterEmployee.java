package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterEmployee {

    private MotorOracle motor;
    private boolean accountCreated;

    public RegisterEmployee() {
        motor = new MotorOracle();
    }

    public void registerUser(String nombre, String apellidos, String correo, String telefono, String contrasena, String store) {
        motor.connect();
        String sql = "SELECT MAX(EMPLOYEE_ID) FROM EMPLOYEES";
        ResultSet resultSet = motor.executeQuery(sql);

        int id = 0;
        try {
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT EMAIL FROM EMPLOYEES WHERE EMAIL = " + "'" + correo + "'";

        resultSet = motor.executeQuery(sql);

        try {
            if (resultSet.next()) {
                System.out.println("account creation failed ");
                motor.disconnect();
            } else {
                accountCreated = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        id++;

        sql = "INSERT INTO EMPLOYEES VALUES (" + id + "," + "'" + nombre + "'" + "," + "'" + apellidos + "'" + "," + "'" + correo + "'" + "," + "'" + contrasena + "'" + "," + "'" + telefono + "'" + "," + "'" + store + "'" + ")";

        motor.execute(sql);

        motor.disconnect();
    }

    public boolean isAccountCreated() {
        return accountCreated;
    }
}
