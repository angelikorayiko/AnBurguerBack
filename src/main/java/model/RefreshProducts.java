package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Products;

public class RefreshProducts {

    private MotorOracle motor;
    private ArrayList<Products> productsList; // Lista de empleados

    public RefreshProducts() {
        motor = new MotorOracle();
        productsList = new ArrayList<>();
    }

    public String convertToJson(ArrayList<Products> productsList) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(productsList);
        return json;
    }

    public ArrayList<Products> getProductsList() {
        return productsList;
    }

    public String refreshProducts() {
        motor.connect();
        String sql = "SELECT PRODUCT_ID, PRODUCT_NAME, CATEGORIES.CATEGORY_ID, PRICE, PRODUCTURL, CATEGORIES.CATEGORY_NAME FROM PRODUCTS INNER JOIN CATEGORIES ON PRODUCTS.CATEGORY_ID = CATEGORIES.CATEGORY_ID";
        ResultSet resultSet = motor.executeQuery(sql);

        ArrayList<Products> productsList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                String productId = resultSet.getString("PRODUCT_ID");
                String name = resultSet.getString("PRODUCT_NAME");
                String categoryId = resultSet.getString("CATEGORY_ID");
                String price = resultSet.getString("PRICE");
                String productUrl = resultSet.getString("PRODUCTURL");
                String categoryName = resultSet.getString("CATEGORY_NAME");

                Products products = new Products(productId, name, categoryId, price, productUrl, categoryName);
                productsList.add(products);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        motor.disconnect();

        String json = convertToJson(productsList);
        return json;
    }
}
