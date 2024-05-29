package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MotorOracle;

public class UpdateProductAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter("CATEGORYNAME");
        String productName = request.getParameter("PRODUCTNAME");
        String price = request.getParameter("PRICE");
        String productUrl = request.getParameter("PRODUCTURL");

        MotorOracle motor = new MotorOracle();
        motor.connect();

        String sql = "UPDATE PRODUCTS SET PRICE='" + price + "', PRODUCT_URL='" + productUrl + "' WHERE PRODUCT_NAME='" + productName + "' AND CATEGORY_NAME='" + categoryName + "'";

        int rowsAffected = motor.execute(sql);

        motor.disconnect();

        if (rowsAffected > 0) {
            return "{\"ProductUpdated\": true}";
        } else {
            return "{\"ProductUpdated\": false}";
        }
    }
}
