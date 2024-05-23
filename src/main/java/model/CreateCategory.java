package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateCategory {

    private MotorOracle motor;
    private boolean categoryCreated;

    public CreateCategory() {
        motor = new MotorOracle();
    }

    public void createCategory(String categoryName) {
        motor.connect();
        String sql = "SELECT MAX(CATEGORY_ID) FROM CATEGORIES";
        ResultSet resultSet = motor.executeQuery(sql);

        int id = 0;
        try {
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "SELECT CATEGORY_NAME FROM CATEGORIES WHERE CATEGORY_NAME = " + "'" + categoryName + "'";

        resultSet = motor.executeQuery(sql);

        try {
            if (resultSet.next()) {
                System.out.println("Category creation failed ");
                motor.disconnect();
            } else {
                categoryCreated = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        id++;

        sql = "INSERT INTO CATEGORIES VALUES (" + "'" + categoryName + "'" + "," + "'" + id + "'" + ")";

        motor.executeQuery(sql);

        motor.disconnect();
    }

    public boolean isCategoryCreated() {
        return categoryCreated;
    }
}
