package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Products;

public class CompleteOrder {

    private MotorOracle motor;

    public CompleteOrder() {
        motor = new MotorOracle();
    }

    public void completeOrder(String order_id) {
        motor.connect();
        String sql = "DELETE FROM ORDERS WHERE ORDER_ID = " + "'" + order_id + "'";
        motor.executeQuery(sql);

        motor.disconnect();

    }
}