package controller;

import data.entity.User;
import service.UserService;
import service.model.ResponseObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        UserService userService = new UserService();

        user.setUserId(Integer.parseInt(request.getParameter("UserId").toString()));
        user.setUsername(request.getParameter("UserName"));
        user.setFirstName(request.getParameter("FirstName"));
        user.setLastName(request.getParameter("LastName"));

        try {
            ResponseObject updateResponse =  userService.UpdateUser(user);
            if(updateResponse.getSuccess()){
                ResponseObject<User> updatedUser = userService.GetUserById(user.getUserId());
                request.getSession().setAttribute("loggedInUser",updatedUser.getData());
            }
            process(request, response,updateResponse.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response,"");
    }

    private void process(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        User loggedInUser =  (User) request.getSession().getAttribute("loggedInUser");
        if(loggedInUser ==null){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("Message",message .length() > 0 ? message : "Session Logged Out");
            requestDispatcher.forward(request, response);
        }
        else{

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user/profile.jsp");
            request.setAttribute("CurrentUser",loggedInUser);
            request.setAttribute("Message", message.length() > 0 ? message: "");
            requestDispatcher.forward(request, response);
        }
    }
}
