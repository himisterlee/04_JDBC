package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;
import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application5 {
    public static void main(String[] args) {

        // 연결객체 만들기
        Connection con = getConnection();

        // preparedStatement 레퍼런스 변수 생성
        PreparedStatement pstmt = null;

        // ResultSet 레퍼런스 변수 생성
        ResultSet rset = null;

        // 조회할 employee의 이름의 성을 받아서 찾기
        Scanner sc = new Scanner(System.in);

        System.out.print("조회할 이름의 성을 입력하세요 : ");

        String empName = sc.nextLine();

        // concat(?, '%') => ? 로 시작하는 것
//        String query = "select * from employee where emp_name like concat(?, '%')";

        // EmployeeDTO 변수 생성
        EmployeeDTO row = null;

        // 리스트 변수생성
        List<EmployeeDTO> empList = null;

        // properties 객체생성
        Properties prop = new Properties();

        try {

            // xml 파일의 경로 지정
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query.xml"));

            // query 에 들어갈 엔트리 키 값 입력(xml 파일의 엔트리 키 값)
            String query = prop.getProperty("selectEmpByFamilyName");


            pstmt = con.prepareStatement(query);

            // 첫번째 물음표, 입력받을 이름의 성 변수값
            pstmt.setString(1, empName);

            rset = pstmt.executeQuery();

            // ArrayList 객체생성
            empList = new ArrayList<>();

            // 반복문으로 ArrayList 안에 값 저장
            while (rset.next()) {

                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getDouble("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        // 향상된 for 문으로 ArrayList 에 목록 출력하기
        for(EmployeeDTO emp : empList) {
            System.out.println(emp);
        }


    }




}
