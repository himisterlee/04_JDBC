package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {

        //연결객체
        Connection con = getConnection();

        // 쿼리문을 저장하고, 실행하는 기능을 하는 용도의 인터페이스
        Statement stmt = null;

        // *select* 결과집합을 받아올 용도의 인터페이스
        ResultSet rset = null;


        try {
            // connection을 이용해 statement 객체 생성
            stmt = con.createStatement();

            // 대소문자 상관X, 쿼리문 마지막에 ; 넣으면 안됨.
            //stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            rset = stmt.executeQuery("select emp_id, emp_name from employee");

            while(rset.next()) {
                // .next() : ResultSet의 커서 위치를 하나 내리면서 행이 존재하면 true, 아니면 false

                System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            // 오버로딩으로 통일시킴.
            close(rset);
            close(stmt);
            close(con);

        }

    }
}