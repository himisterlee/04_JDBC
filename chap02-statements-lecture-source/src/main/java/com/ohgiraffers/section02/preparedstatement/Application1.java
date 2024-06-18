package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        /*
        * PreparedStatement
        *
        * Statement 상속받은 인터페이스
        * 더 효율적으로 작업이 진행된다.
        * 완성된 SQL 문과 미완성된 SQL문을 모두 사용 할 수 있다.
        *
        * 위치홀더(placeholder) : ?
        * */

        // 커넥션 생성
        Connection con = getConnection();

        // preparedStatement 레퍼런스 변수 생성
        PreparedStatement pstmt = null;

        // ResultSet 레퍼런스 변수 생성
        ResultSet rset = null;


        try {

            // statement와 다르게 preparedStatement 객체를 생성할때 쿼리문을 넣어준다.
            pstmt = con.prepareStatement("select emp_id, emp_name from employee");

            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString("emp_id") + ", "
                + rset.getString("emp_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }


    }
}
