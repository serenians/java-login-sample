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

@WebServlet(name = "UserCreateServlet")
public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser =  (User) request.getSession().getAttribute("loggedInUser");
        if(loggedInUser ==null){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("Message","Session Logged Out");
            requestDispatcher.forward(request, response);
        }
        else{

            User user = new User();
            user.setUsername(request.getParameter("UserName"));
            user.setPassword(request.getParameter("Password"));
            user.setFirstName(request.getParameter("FirstName"));
            user.setLastName(request.getParameter("LastName"));

            UserService userService = new UserService();

            try {
                ResponseObject userSaveResponse = userService.AddUser(user);
                if(userSaveResponse.getSuccess()){
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user/list.jsp");
                    requestDispatcher.forward(request, response);
                }
                else{

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser =  (User) request.getSession().getAttribute("loggedInUser");
        if(loggedInUser ==null){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("Message","Session Logged Out");
            requestDispatcher.forward(request, response);
        }
        else{

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user/create.jsp");
            request.setAttribute("CurrentUser",loggedInUser);
            request.setAttribute("Message",  "");
            requestDispatcher.forward(request, response);
        }
    }
}
