package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotorOracle {
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    private static final String URL = "jdbc:oracle:thin:@anburguer-database.c4mykgibfvue.us-east-1.rds.amazonaws.com:1521:orcl";
    private static final String USER = "admin";
    private static final String PASS = "123456789";

    public void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            st = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int execute(String sql) {
        int resp = 0;
        try {
            resp = st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public int executeUpdate(String sql) {
        int resp = 0;
        try {
            resp = st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public ResultSet executeQuery(String sql) {
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkIfProductExists(String productId) {
        String query = "SELECT COUNT(*) FROM PRODUCTS WHERE PRODUCT_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void updateProduct(String productId, String categoryName, String productName, String price, String productUrl) {
        String updateQuery = "UPDATE PRODUCTS SET CATEGORY_NAME = ?, PRODUCT_NAME = ?, PRICE = ?, PRODUCTURL = ? WHERE PRODUCT_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            pstmt.setString(1, categoryName);
            pstmt.setString(2, productName);
            pstmt.setString(3, price);
            pstmt.setString(4, productUrl);
            pstmt.setString(5, productId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
