package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Order;

public class InsertOrder {

    private MotorOracle motor;
    private boolean isOrderPost = false;

    public InsertOrder() {
        motor = new MotorOracle();

    }

    public String insertOrder(String productNames, String quantities) {
        motor = new MotorOracle();
        motor.connect();
        ArrayList<Order> orderList = new ArrayList<>();
        Order order = new Order(productNames, quantities);
        String employeeId = null;
        //String clientId = request.getParameter("clientId");
        String sql = "SELECT MAX(ORDER_ID) FROM ORDERS";
        ResultSet resultSet = motor.executeQuery(sql);
        int id = 0;
        try {
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id++;
        sql = "SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME  = '" + productNames + "'";

        resultSet = motor.executeQuery(sql);
        String productId = "";
        try {
            if (resultSet.next()) {
                productId = resultSet.getString("PRODUCT_ID");
            } else {
                productId = "1";
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        sql = "INSERT INTO ORDERS VALUES('" + id + "','','" + 1 + "','','" + 1 + "')";
        resultSet = motor.executeQuery(sql);

        sql = "INSERT INTO ORDERS_DETAILS VALUES('" + id + "','" + productId + "','" + quantities + "')";
        resultSet = motor.executeQuery(sql);

        isOrderPost = true;
        return null;
    }
}