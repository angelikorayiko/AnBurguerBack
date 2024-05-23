/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Order;
import model.InsertOrder;

/**
 *
 * @author espec
 */
public class ProductAction {

    //@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("SUBACTION");
//        String[] arrayAction = action.split("\\.");
        switch (action) {
            case "SEND_ORDER":
                cadDestino = requestOrder(request, response);
                break;
        }
        return cadDestino;
    }

    private String requestOrder(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "";
        String productNames = request.getParameter("PRODUCTNAME");
        String quantity = request.getParameter("QUANTITY");

        InsertOrder insOrder = new InsertOrder();
        String json = insOrder.insertOrder(productNames, quantity);

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"RESULT\":\"OK\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strRet;
    }
}