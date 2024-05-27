package org.example.service;

import org.example.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.setting.DbConnector.getConnection;

public class UsersDaoImpl implements UsersDao {

    private static final Connection connection = getConnection();

    /**
     *
     * @param user
     */
    @Override
    public void add(User user) {
        try {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO SUSERS(user_id, user_guid, user_name) VALUES (?,?,?)");
            ps.setInt(1,user.getUserId());
            ps.setString(2,user.getUserGuid());
            ps.setString(3, user.getUserName());
            ps.executeUpdate();
            System.out.println("Add user: "+ user.getUserName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> printAll() {
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT su.user_id, su.user_guid, su.user_name FROM SUSERS su"
            );
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserGuid(resultSet.getString("user_guid"));
                user.setUserName(resultSet.getString("user_name"));
                list.add(user);
                System.out.println("Print all: "+
                        resultSet.getInt("user_id")+ "," +
                        resultSet.getString("user_guid")+","+
                        resultSet.getString("user_name")
                        );
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        try {
            PreparedStatement ps = connection.prepareStatement("TRUNCATE table SUSERS");
            ps.executeUpdate();
            System.out.println("Delete all: ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
