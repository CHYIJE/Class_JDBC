package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Employee;

public class MySQLJdbcExample {

	Employee employee;

	public static void main(String[] args) {

		// 준비물
		//
		String url = "jdbc:mysql://localhost:3306/mydb3?serverTimezone=Asia/Seoul";
		String user = "root"; // 사용서비스에서 절대 루트 계정 사용 금지
		String password = "asd123";

		// 필요 데이터 타입
		// JDBC API 레벨(자바 개발자들이 개념화 시켜 놓은 클래스들이다.)
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// 1. MYSQL 구현체를 사용하겠다는 설정을 해아한다.
		// JDBS드라이버 로드 (MySQL 구현 클래스를 로드)
		try {
			// 1. MySQL 구현체를 사용하겠다는 설정을 해야합니다.
			// JDBC 드라이버 로드 (MySQL 구현 클래스를 로드)
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. 데이터 베이스 연결 설정
			connection = DriverManager.getConnection(url, user, password);

			// 3. SQL 실행
			statement = connection.createStatement();
			// 딱 2가지는 기억하자! 쿼리를 실행 시키는 메서드
			resultSet = statement.executeQuery("SELECT * FROM tb_employees"); // select 실행시 사용한다.
			// statement.executeUpdate(password); --> Insert, Update, delete 사용

			// 구문 분석 -- 파싱

			// 4. 결과 처리
//			while(resultSet.next()) {
//				
//				System.out.println("USER ID : " + resultSet.getInt("employee_id"));
//				System.out.println("USER NAME : " + resultSet.getString("name"));
//				System.out.println("department : " + resultSet.getString("department"));
//				System.out.println("---------------------");
//					
//			}

			List<Employee> list = new ArrayList<>();
			while (resultSet.next()) {
				Employee employee = new Employee(resultSet.getInt("employee_id"), resultSet.getString("name"),
						resultSet.getString("department"), resultSet.getInt("salary"));
				list.add(employee);
			}

			for (Employee employee : list) {
				System.out.println(employee.getEmployee_id() + " " + employee.getName() + " " + employee.getDepartment()
						+ " " + employee.getSalary());
				System.out.println(employee.toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end of main

} // end of class
