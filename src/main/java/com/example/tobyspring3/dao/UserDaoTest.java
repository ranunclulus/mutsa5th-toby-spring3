package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
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
