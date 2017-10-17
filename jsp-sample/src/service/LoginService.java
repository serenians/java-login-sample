package service;

import data.entity.User;
import service.model.ResponseObject;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.xml.ws.Response;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;

public class LoginService extends BaseService {
    public ResponseObject<User> Login(String username, String password) throws SQLException, ClassNotFoundException {
        ResponseObject<User> result = null;

//        String query = "select UserId, UserName, LastLoginDate from public.\"User\" where UserName = '"+username+"' and Password = '"+password+"'";
        String query = "SELECT * FROM public.\"User\"";
        System.out.println(query);
        ResultSet resultSet = context.executeQuery(query);

        int count = 0;
        User user = null;
        while(resultSet.next()){
            user = new User();
            user.setUserId(resultSet.getInt("UserId"));
            user.setUsername(resultSet.getString("UserName"));
            user.setLastLoginDate(resultSet.getDate("LastLoginDate"));

            count ++;
        }

        if(count == 1){
            result = new ResponseObject<>(true, "User authenticated successfully.", user);
        }
        else{
            result = new ResponseObject<>(false, "User authentication failed.");
        }

        return result;
    }
}

