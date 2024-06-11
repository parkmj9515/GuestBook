package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import guestbook.vo.GuestVo;

public class GuestbookOracle implements GuestbookDao {
    private String dbuser;
    private String dbpass;

    // 생성자
    public GuestbookOracle(String dbuser, String dbpass) {
        this.dbuser = dbuser;
        this.dbpass = dbpass;
    }

    // 데이터베이스 접속 정보 -> 컨텍스트 파라미터로부터 받아옴
    // Connection 공통 메서드
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

    @Override
    public List<GuestVo> getList() { // SELECT
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<GuestVo> list = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM guestbook ORDER BY reg_date DESC";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long no = rs.getLong("no");
                String name = rs.getString("name");
                Long password = rs.getLong("password");
                String content = rs.getString("content");
                Date regDate = rs.getDate("reg_date");

                GuestVo vo = new GuestVo(no, name, password, content, regDate);
                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // SQLException 발생 시 메시지를 출력하고 빈 리스트를 반환하거나, 예외를 다시 던지는 등의 적절한 처리
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean insert(GuestVo vo) { // INSERT
        Connection conn = null;
        PreparedStatement pstmt = null;
        int insertedCount = 0;

        try {
            conn = getConnection();
            String sql = "INSERT INTO guestbook (no, name, password, content, reg_date) "
                    + "VALUES (seq_guestbook_no.nextval, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getName());
            pstmt.setLong(2, vo.getPassword());
            pstmt.setString(3, vo.getContent());
            pstmt.setDate(4, new java.sql.Date(vo.getRegDate().getTime()));
            insertedCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1 == insertedCount;
    }

    @Override
    public boolean delete(Long no) { // DELETE
        Connection conn = null;
        PreparedStatement pstmt = null;
        int deletedCount = 0;

        try {
            conn = getConnection();
            String sql = "DELETE FROM guestbook WHERE no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            deletedCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1 == deletedCount;
    }
}