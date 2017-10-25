package controller.admin;

import data.entity.User;
import service.UserService;
import service.model.ResponseObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

@WebServlet(name = "UserServlet")
public class UserGetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //check user type
        UserService userService = new UserService();
        User loggedInUser =  (User) request.getSession().getAttribute("loggedInUser");
        if(loggedInUser ==null){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("Message", "Session Logged Out");
            requestDispatcher.forward(request, response);
        }
        else{
            ResponseObject<Set<User>> userRecords = null;
            try {
                userRecords = userService.GetAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(userRecords.getSuccess()){
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user/list.jsp");
                request.setAttribute("CurrentUser",loggedInUser);
                request.setAttribute("UserList", userRecords.getData());
                request.setAttribute("Message", "");
                requestDispatcher.forward(request, response);
            }
            else{

            }

        }
    }
}
