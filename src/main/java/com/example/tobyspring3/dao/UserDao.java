package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;
import static java.lang.System.setOut;


public abstract class UserDao {

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException; /* {

        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                dbHost, dbUser, dbPassword
        );
        return conn;
   }*/
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into User(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();
        pstmt.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from User where id = ?");
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery(); // 내가 요청한 결과값이 rs에 담아옴
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // 안 닫아 주면 계속 연결돼 있음 -> 연결만 늘어나서 서비스 장애가 생길 수 있음
        pstmt.close();
        conn.close();
        rs.close();
        return user;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new NUserDao();
        User user = new User();
        user.setId("2");
        user.setName("huisu");
        user.setPassword("2459810");
        User selectuser = userDao.get("2");
        System.out.println(selectuser.getId());;
        System.out.println(selectuser.getName());
        System.out.println(selectuser.getPassword());
    }
}
