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

        String query = MessageFormat.format("select userid, username, lastlogindate from user where username = '{0}' and password = '{1}'", username, password).toString();

        ResultSet resultSet = context.executeQuery(query);

        int count = 0;
        User user = null;
        while(resultSet.next()){
            user = new User();
            user.setUserId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));
            user.setLastLoginDate(resultSet.getDate(3));

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

