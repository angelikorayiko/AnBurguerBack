package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateProduct {

    private MotorOracle motor;
    private boolean productUpdated;

    public UpdateProduct() {
        motor = new MotorOracle();
    }

    public void updateProduct(String categoryName, String productName, String price, String productUrl) {
        motor.connect();

        // Obtener el ID de la categoría
        String sql = "SELECT CATEGORY_ID FROM CATEGORIES WHERE CATEGORY_NAME = '" + categoryName + "'";
        ResultSet resultSet = motor.executeQuery(sql);
        String categoryString = null;
        try {
            if (resultSet.next()) {
                categoryString = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Verificar si el producto existe
        sql = "SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME = '" + productName + "'";
        resultSet = motor.executeQuery(sql);
        int productId = 0;
        try {
            if (resultSet.next()) {
                productId = resultSet.getInt(1);
            } else {
                System.out.println("Product does not exist, update failed");
                productUpdated = false;
                motor.disconnect();
                return;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // Actualizar el producto
        sql = "UPDATE PRODUCTS SET CATEGORY_ID = '" + categoryString + "', PRICE = '" + price + "', PRODUCTURL = '" + productUrl + "' WHERE PRODUCT_ID = '" + productId + "'";
        motor.executeQuery(sql);

        System.out.println("Product update successful");
        productUpdated = true;

        motor.disconnect();
    }

    public boolean isProductUpdated() {
        return productUpdated;
    }
}

