package ch04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransactionExample2 {

	public static void main(String[] args) {
		// 드라이버 -> mysql 개발자들이 자바 코드로 작성한 클래스의 묶음(.jar)
		// ver 8.0
		String url = "jdbc:mysql://localhost:3306/m_board?serverTimezone=Asia/Seoul";
		String id = "root";
		String password = "asd123";

		// 구현체를 사용하기 위해서
		// 클래스 Class <-- 최상위 Object 안에 있음
		// 동적 바인딩 처리
		try {
			// mysql 드라이버(구현 클래스 ) 메모리에 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// try catch resource 문법
			try (Connection conn = DriverManager.getConnection(url, id, password)) {
				conn.setAutoCommit(false); // 수동 커밋 모드 설정
				// 댓글 인설트
				String sqlInert = "INSERT INTO reply(userId, boardId, content, createDate)\r\n"
						+ "	values(?, ?, ?, now())";

				PreparedStatement psmt1 = conn.prepareStatement(sqlInert);
				psmt1.setString(1, "2");
				psmt1.setString(2, "3");
				psmt1.setString(3, "태현아 공부 안해?");
				psmt1.executeUpdate();

				String sqlUpdate = "UPDATE board SET title = ? where id = ?";
				PreparedStatement psmt2 = conn.prepareStatement(sqlUpdate);
				psmt2.setString(1, "김남철의 뜨거운 코딩");
				psmt2.setString(2, "2");
				psmt2.executeUpdate();

				if (true) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e) {
//				conn.rollback();
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // end of main

} // end of class
