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

@WebServlet(name = "UserEditServlet")
public class UserEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser =  (User) request.getSession().getAttribute("loggedInUser");
        if(loggedInUser ==null){
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("Message","Session Logged Out");
            requestDispatcher.forward(request, response);
        }
        else{

            User user = new User();
            user.setUserId(Integer.parseInt(request.getParameter("UserId")));
            user.setUsername(request.getParameter("UserName"));
            user.setFirstName(request.getParameter("FirstName"));
            user.setLastName(request.getParameter("LastName"));

            UserService userService = new UserService();

            try {
                ResponseObject userSaveResponse = userService.UpdateUser(user);
                if(userSaveResponse.getSuccess()){
                    request.setAttribute("Message","User updated successfully.");
                    response.sendRedirect("/Admin/User/Get");
//                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Admin/User/Get");
//                    requestDispatcher.forward(request, response);
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
            int id = Integer.parseInt(request.getParameter("id"));
            UserService userService = new UserService();
            ResponseObject<User> user = null;
            try {
                user = userService.GetUserById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/user/edit.jsp");
            request.setAttribute("User", user.getData());
            request.setAttribute("CurrentUser",loggedInUser);
            request.setAttribute("Message",  "");
            requestDispatcher.forward(request, response);
        }
    }
}
