package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application2 {
    public static void main(String[] args) {


        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        Connection con = null;

        try {

            // 사용할 드라이버 등록
            Class.forName(driver);

            // DriverManger를 통해서 Connection 객체 생성
            con = DriverManager.getConnection(url,user,password);

            // 연결객체 생성되었는지 확인. (객체의 주소값이 반환되는것 확인)
            System.out.println("con = " + con);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 자원 반납
            if( con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}