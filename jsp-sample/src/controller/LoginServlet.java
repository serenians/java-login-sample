package controller;

import data.entity.User;
import service.LoginService;
import service.model.ResponseObject;

import javax.servlet.RequestDispatcher;
import java.io.IOException;

public class LoginServlet extends javax.servlet.http.HttpServlet {

    private final String ResponseMessageKey = "ResponseMessage";
    private final String ResponseDataKey = "ResponseData";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Login Post");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        try{
            LoginService loginService = new LoginService();
            ResponseObject<User> loginResult = loginService.Login(username, password);
            if(loginResult.getSuccess() && loginResult.getData() != null){
//                RequestDispatcher requestDispatcher =  request.getRequestDispatcher("index.jsp");
                request.setAttribute(ResponseMessageKey,"User Logged in successfully.");
                User loggedInUser= loginResult.getData();
                request.setAttribute(ResponseDataKey, loggedInUser);
                request.getSession().setAttribute("loggedInUser",loggedInUser);

                response.sendRedirect("/index.jsp");
//                requestDispatcher.forward(request, response);
            }
            else{
                request.setAttribute(ResponseMessageKey,"User log in failed.");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            request.setAttribute(ResponseMessageKey,"Error while trying to log in.");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("Login get");
    }
}
