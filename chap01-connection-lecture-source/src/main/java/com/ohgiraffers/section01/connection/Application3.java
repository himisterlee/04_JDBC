package com.ohgiraffers.section01.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application3 {
    public static void main(String[] args) {


        Properties prop = new Properties();

        Connection con = null;

        try {
            // Properties 파일에 담긴 정보 가져오기
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

            // 파일 잘 읽어오는지 확인
            System.out.println("prop = " + prop);

            // key-value로 원하는 값 찾아오기
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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