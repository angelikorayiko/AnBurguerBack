package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Employees;

public class DeleteUser {

    private MotorOracle motor;

    public DeleteUser() {
        motor = new MotorOracle();
    }

    public void deleteUser(String employee_id) {
        motor.connect();
        String sql = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = " + "'" + employee_id + "'";
        motor.executeQuery(sql);

        motor.disconnect();

    }


}
