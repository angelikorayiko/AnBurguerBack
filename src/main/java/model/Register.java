package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {

    private MotorOracle motor;
    private boolean accountCreated;

    public Register() {
        motor = new MotorOracle();
    }

    public void registerUser(String nombre, String apellidos, String correo, String telefono, String contrasena) {
        motor.connect();
        String sql = "SELECT MAX(CLIENT_ID) FROM CLIENTS";
        ResultSet resultSet = motor.executeQuery(sql);

        int id = 0;
        try {
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT EMAIL FROM CLIENTS WHERE EMAIL = " + "'" + correo + "'";

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

        sql = "INSERT INTO CLIENTS VALUES (" + id + "," + "'" + nombre + "'" + "," + "'" + apellidos + "'" + "," + "'" + correo + "'" + "," + "'" + contrasena + "'" + "," + "'" + telefono + "'" + ")";

        motor.executeQuery(sql);

        motor.disconnect();
    }

    public boolean isAccountCreated() {
        return accountCreated;
    }
}

/*    public static String toArrayJSon(ArrayList<Register> peliculas) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(peliculas);

        return resp;
    }*/
