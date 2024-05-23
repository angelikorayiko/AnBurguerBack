package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateProduct {

    private MotorOracle motor;
    private boolean productCreated;

    public CreateProduct() {
        motor = new MotorOracle();
    }

    public void createProduct(String categoryName, String productName, String price,  String productUrl) {
        motor.connect();
        String sql = "SELECT MAX(PRODUCT_ID) FROM PRODUCTS";
        ResultSet resultSet = motor.executeQuery(sql);

        int id = 0;
        try {
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT CATEGORY_ID FROM CATEGORIES WHERE CATEGORY_NAME =" + "'" + categoryName + "'";

        resultSet = motor.executeQuery(sql);
        String categoryString = null;
        try {
            if (resultSet.next()) {
                categoryString = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        sql = "SELECT PRODUCT_NAME FROM PRODUCTS WHERE PRODUCT_NAME = " + "'" + productName + "'";

        resultSet = motor.executeQuery(sql);

        try {
            if (resultSet.next()) {
                System.out.println("Product creation failed ");
                motor.disconnect();
            } else {
                productCreated = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        id++;

        sql = "INSERT INTO PRODUCTS VALUES (" + "'" + id + "'" + "," + "'" + productName + "'" + "," + "'" + price + "'" + "," + "'" + categoryString + "'" + "," + "'" + productUrl + "'" + ")";

        motor.executeQuery(sql);

        motor.disconnect();
    }

    public boolean isProductCreated() {
        return productCreated;
    }
}
