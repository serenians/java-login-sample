package controller.admin;

import data.entity.User;
import net.sf.json.JSONObject;
import service.UserService;
import service.model.ResponseObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedInUser =  (User) request.getSession().getAttribute("loggedInUser");
        if(loggedInUser ==null){
            request.setAttribute("Message","Session Logged Out");
            response.sendRedirect("/login.jsp");
        }
        else {
            Integer id = Integer.parseInt(request.getParameter("id"));
            UserService userService = new UserService();
            ResponseObject deleteResult = null;
            String message = "";
            try {
                deleteResult = userService.DeleteUser(id);
                message = deleteResult.getMessage();
            } catch (SQLException e) {
                e.printStackTrace();
                message = e.getMessage();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                message = e.getMessage();
            }
            boolean isSuccess = deleteResult != null && deleteResult.getSuccess();
            JSONObject json = new JSONObject();

            json.accumulate("IsSuccess",isSuccess);
            json.accumulate("Message",message);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
