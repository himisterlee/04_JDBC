package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.DepartmentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        /*
        * Department 테이블의 부서코드(아이디)를 입력받아서
        * 부서코드(아이디), 부서명, 지역코드를
        * DepartmentDTO 객체에 담고,
        * DepartmentDTO를 출력하세요
        * */


        Connection con = getConnection();

        PreparedStatement pstm = null;

        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 부서코드를 입력해주세요 : ");
        String dept_id = sc.nextLine();

        String query = "select * from department where dept_id = ?";

        DepartmentDTO selectedDep = null;


        try {
            pstm = con.prepareStatement(query);

            pstm.setString(1, dept_id);

            rset = pstm.executeQuery();

            if(rset.next()) {

                selectedDep = new DepartmentDTO();

                selectedDep.setDeptId(rset.getString("DEPT_ID"));
                selectedDep.setDeptTitle(rset.getString("DEPT_TITLE"));
                selectedDep.setLocationId(rset.getString("LOCATION_ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstm);
            close(con);
        }

        System.out.println("selectedDep = " + selectedDep);

    }
}
