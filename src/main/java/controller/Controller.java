package controller;

import controller.action.RegisterAction;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Login;
import model.Register;
import model.*;
import controller.action.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Order;
import model.MotorOracle;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();
        String action = request.getParameter("ACTION");

        switch (action) {

            case "REGISTER":
                String strResp = new RegisterAction().execute(request, response);
                out.print(strResp);
                break;

            case "LOGIN":
                strResp = new RegisterAction().execute(request, response);
                out.print(strResp);
                break;

            case "INTRANET":
                strResp = new IntranetActionManager().execute(request, response);
                out.print(strResp);
                break;

            case "SEND_ORDER":
                strResp = new ProductAction().execute(request, response);
                out.print(strResp);
                break;

            case "UPDATE_PRODUCT":
                strResp = new UpdateProductAction().execute(request, response);
                out.print(strResp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
