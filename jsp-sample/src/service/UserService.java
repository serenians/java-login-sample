package service;

import data.entity.User;
import service.model.ResponseObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserService extends BaseService {

    public ResponseObject<Set<User>> GetAllUsers() throws SQLException, ClassNotFoundException {
        ResponseObject<Set<User>> result = null;

        String query = "select userid, username, lastlogindate, firstname, lastname from user";
        System.out.println(query);
        ResultSet resultSet = context.executeQuery(query);

        Set<User> users = new HashSet<User>();
        while(resultSet.next()){
            User user = new User();
            user.setUserId(resultSet.getInt("UserId"));
            user.setUsername(resultSet.getString("UserName"));
            user.setLastLoginDate(resultSet.getDate("LastLoginDate"));
            user.setFirstName(resultSet.getString("FirstName"));
            user.setLastName(resultSet.getString("LastName"));
            users.add(user);
        }

        result = new ResponseObject<Set<User>>(true, "User authenticated successfully.", users);

        return result;
    }

    public ResponseObject<User> GetUserById(int id) throws SQLException, ClassNotFoundException {
        ResponseObject<User> result = null;

        String query = "select userid, username, lastlogindate, firstname, lastname from user where userid='"+id+"'";
        System.out.println(query);
        ResultSet resultSet = context.executeQuery(query);

        User user = null;
        while(resultSet.next()){
            user = new User();
            user.setUserId(resultSet.getInt("UserId"));
            user.setUsername(resultSet.getString("UserName"));
            user.setLastLoginDate(resultSet.getDate("LastLoginDate"));
            user.setFirstName(resultSet.getString("FirstName"));
            user.setLastName(resultSet.getString("LastName"));
        }

        result = new ResponseObject<User>(true, "User Found successfully.", user);

        return result;
    }
    public ResponseObject<Object> UpdateUser(User user) throws SQLException, ClassNotFoundException {
        ResponseObject<Object> result =null;
        String query = "update user set username='"+user.getUsername()+"', FirstName='"+user.getFirstName()+"', LastName ='"+user.getLastName()+"'  where userid = '"+user.getUserId()+"' ";

        int c = context.executeUpdate(query);
        if(c ==1)
        {
            result = new ResponseObject<>(true,"User information updated successfully.");
        }
        else {
            result = new ResponseObject<>(false, "User information update failed.");
        }
        return result;
    }
    public ResponseObject<Object> AddUser(User user) throws SQLException, ClassNotFoundException {
        ResponseObject<Object> result =null;
        PreparedStatement preparedStatement = context.getPreparedStatement("insert into user (username, password, firstname, lastname, active)values (?,?,?,?,?)");
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3,user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setBoolean(5, true);

        int c = context.executeUpdate();
        if(c ==1)
        {
            result = new ResponseObject<>(true,"User information added successfully.");
        }
        else {
            result = new ResponseObject<>(false, "User information update failed.");
        }
        return result;
    }
    public ResponseObject<Object> DeleteUser(int id) throws SQLException, ClassNotFoundException {
        ResponseObject<Object> result = null;

        String query = "delete  from user where userid='"+id+"'";
        System.out.println(query);
        int c = context.executeUpdate(query);

        if(c==1) {
            result = new ResponseObject<Object>(true, "User deleted successfully.", null);
        }
        else{
            result = new ResponseObject<>(false,"User delete failed.");
        }
        return result;
    }
}
