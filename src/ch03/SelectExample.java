package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Scanner sc = new Scanner(System.in);
			

			connection = DriverManager.getConnection(url, user, password);

			String query = "Select * from employee where name = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

//			preparedStatement.setString(1, input);
//
//			resultSet = preparedStatement.executeQuery();
			String input = sc.next();
			preparedStatement.setString(1, input);
			
			resultSet = preparedStatement.executeQuery();
			
			while (true) {
				while (resultSet.next()) {
				
					if (input.equals(resultSet.getString(input))) {
						
						System.out.println("생존번호 : " + resultSet.getInt("id") + " || " + "이름 : "
								+ resultSet.getString("name") + " || " + "부서 : " + resultSet.getString("department")
								+ " || " + "급여 : " + resultSet.getInt("salary"));
					} 
						
				} 
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}