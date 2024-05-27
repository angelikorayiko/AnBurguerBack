/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Register;
import model.DAO;
import model.Login;
import objects.Order;
import model.MotorOracle;

/**
 *
 * @author S2-PC109
 */
public class RegisterAction implements IAction {

    private MotorOracle motor;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
//        String[] arrayAction = action.split("\\.");
        switch (action) {
            case "REGISTER":
                cadDestino = registerUser(request, response);
                break;

            case "LOGIN":
                cadDestino = loginUser(request, response);
                break;

        }
        return cadDestino;
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "{\"RESULT\":\"OK\"}";
        Register regUser = new Register();
        String NAME = request.getParameter("NAME");
        String SURNAME = request.getParameter("SURNAME");
        String MAIL = request.getParameter("MAIL");
        String PHONE = request.getParameter("PHONE");
        String PASS = request.getParameter("PASS");

        if ("".equals(NAME) || "".equals(SURNAME) || "".equals(MAIL) || "".equals(PHONE) || "".equals(PASS)) {
            String jsonResponse = "{\"ACCOUNTFAILED\":" + regUser.isAccountCreated() + "}";
            return jsonResponse;
        }
        regUser.registerUser(NAME, SURNAME, MAIL, PHONE, PASS);

        String jsonResponse = "{\"accountCreated\":" + regUser.isAccountCreated() + "}";

        return jsonResponse;

    }

    private String loginUser(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "{\"RESULT\":\"OK\"}";
        Login logUser = new Login();
        String EMAIL = request.getParameter("EMAIL");
        String PASSWORD = request.getParameter("PASS");

        logUser.loginUser(EMAIL, PASSWORD);

        String jsonResponse = "{\"accountLogged\":" + logUser.isUserLogged() + ", \"isEmployee\":" + logUser.isEmployee() + "}";

        return jsonResponse;

    }

}