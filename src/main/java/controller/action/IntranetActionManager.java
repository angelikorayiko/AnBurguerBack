/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegisterEmployee;
import model.CreateCategory;
import model.CreateProduct;
import model.DeleteProduct;
import model.DeleteUser;
import model.RefreshOrder;
import model.RefreshProducts;
import model.RefreshUsers;
import objects.Employees;

/**
 *
 * @author S2-PC109
 */
public class IntranetActionManager implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("SUB_ACTION");
//        String[] arrayAction = action.split("\\.");
        switch (action) {
            case "REGISTER":
                cadDestino = registerUser(request, response);
                break;

            case "CREATECATEGORY":
                cadDestino = createCategory(request, response);
                break;

            case "REFRESHUSERS":
                cadDestino = refreshUser(request, response);
                break;

            case "DELETEUSER":
                deleteUser(request, response);
                cadDestino = refreshUser(request, response);
                break;

            case "CREATEPRODUCT":
                cadDestino = createProduct(request, response);
                break;

            case "REFRESHPRODUCTS":
                cadDestino = refreshProduct(request, response);
                break;

            case "DELETEPRODUCT":
                deleteProduct(request, response);
                cadDestino = refreshProduct(request, response);
                break;
            case "REFRESHORDER":
                //refreshOrder(request, response);
                cadDestino = refreshOrder(request, response);
        }
        return cadDestino;
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "{\"RESULT\":\"OK\"}";
        RegisterEmployee regUser = new RegisterEmployee();
        String NAME = request.getParameter("NAME");
        String SURNAME = request.getParameter("SURNAME");
        String MAIL = request.getParameter("MAIL");
        String PHONE = request.getParameter("PHONE");
        String PASS = request.getParameter("PASS");
        String STORE = request.getParameter("STORE");
        regUser.registerUser(NAME, SURNAME, MAIL, PHONE, PASS, STORE);

        String jsonResponse = "{\"accountCreated\":" + regUser.isAccountCreated() + "}";

        return jsonResponse;

    }

    public String createCategory(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "{\"RESULT\":\"OK\"}";
        CreateCategory creCategory = new CreateCategory();
        String categoryName = request.getParameter("categoryName");
        creCategory.createCategory(categoryName);

        String jsonResponse = "{\"CategoryCreated\":" + creCategory.isCategoryCreated() + "}";

        return jsonResponse;
    }

    public String refreshUser(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "";
        RefreshUsers refUser = new RefreshUsers();
        String json = refUser.refreshUser();

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strRet;

    }

    public String deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "";
        DeleteUser delUser = new DeleteUser();
        String employee_id = request.getParameter("employeeID");
        delUser.deleteUser(employee_id);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return strRet;

    }

    public String createProduct(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "{\"RESULT\":\"OK\"}";
        CreateProduct creProduct = new CreateProduct();
        String categoryName = request.getParameter("CATEGORYNAME");
        String productName = request.getParameter("PRODUCTNAME");
        String price = request.getParameter("PRICE");
        String productUrl = request.getParameter("PRODUCTURL");
        creProduct.createProduct(categoryName, productName, price, productUrl);

        String jsonResponse = "{\"ProductCreated\":" + creProduct.isProductCreated() + "}";

        return jsonResponse;
    }

    public String refreshProduct(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "";
        RefreshProducts refProducts = new RefreshProducts();
        String json = refProducts.refreshProducts();

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strRet;

    }

    public String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "";
        DeleteProduct delProduct = new DeleteProduct();
        String product_id = request.getParameter("productID");
        delProduct.deleteProduct(product_id);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return strRet;

    }

    public String refreshOrder(HttpServletRequest request, HttpServletResponse response) {
        String strRet = "";
        RefreshOrder refOrder = new RefreshOrder();
        String json = refOrder.RefreshOrder();
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strRet;

    }
}
