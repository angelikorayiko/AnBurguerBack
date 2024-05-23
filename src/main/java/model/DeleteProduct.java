package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Products;

public class DeleteProduct {

    private MotorOracle motor;

    public DeleteProduct() {
        motor = new MotorOracle();
    }

    public void deleteProduct(String product_id) {
        motor.connect();
        String sql = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = " + "'" + product_id + "'";
        motor.executeQuery(sql);

        motor.disconnect();

    }
}
