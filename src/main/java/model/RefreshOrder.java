package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.OrderFD;
import objects.Products;

public class RefreshOrder {

    private MotorOracle motor;
    private ArrayList<OrderFD> OrderList;

    public RefreshOrder() {
        motor = new MotorOracle();
        OrderList = new ArrayList<>();
    }

    public String convertToJson(ArrayList<OrderFD> OrderList) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(OrderList);
        return json;
    }

    public ArrayList<OrderFD> getOrderList() {
        return OrderList;
    }

    public String RefreshOrder() {
        motor.connect();
        String sql = "SELECT ORDER_ID, STATUS,STORE_ID,EMPLOYEE_ID,CLIENT_ID FROM ORDERS";
        ResultSet resultSet = motor.executeQuery(sql);

        ArrayList<OrderFD> OrderList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                String Order_id = resultSet.getString("ORDER_ID");
                String Store_id = resultSet.getString("STORE_ID");
                String Client_id = resultSet.getString("CLIENT_ID");

                OrderFD orderFD = new OrderFD(Order_id, Store_id, Client_id);
                OrderList.add(orderFD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        motor.disconnect();

        String json = convertToJson(OrderList);
        return json;
    }
}
