package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.BoardDTO;

public class BoardDAO {
	/*
	private static final String SELECT_ALL = "SELECT * FROM BOARD ORDER BY BID DESC";
	private static final String SELECT_ALL_TITLE = "SELECT * FROM BOARD WHERE TITLE LIKE ? ORDER BY BID DESC";
	private static final String SELECT_ALL_WRITER = "SELECT * FROM BOARD WHERE WRITER=? ORDER BY BID DESC";
	private static final String SELECT_ONE = "SELECT * FROM BOARD WHERE BID=?";
	 */
	private static final String SELECT_ALL = "SELECT B.*,M.NAME FROM BOARD B LEFT JOIN MEMBER M ON B.WRITER=M.MID ORDER BY B.BID DESC";
	private static final String SELECT_ALL_TITLE = "SELECT B.*,M.NAME FROM BOARD B LEFT JOIN MEMBER M ON B.WRITER=M.MID WHERE B.TITLE LIKE ? ORDER BY B.BID DESC";
	private static final String SELECT_ALL_WRITER = "SELECT B.*,M.NAME FROM BOARD B LEFT JOIN MEMBER M ON B.WRITER=M.MID WHERE M.NAME=? ORDER BY B.BID DESC";
	private static final String SELECT_ONE = "SELECT B.*,M.NAME FROM BOARD B LEFT JOIN MEMBER M ON B.WRITER=M.MID WHERE B.BID=?";

	private static final String INSERT = "INSERT INTO BOARD VALUES((SELECT NVL(MAX(BID),1000) FROM BOARD)+1,?,?,?)";
	private static final String UPDATE = "UPDATE BOARD SET TITLE=? WHERE BID=?";
	private static final String UPDATE_DELMEM = "UPDATE BOARD SET WRITER=NULL WHERE WRITER=?";
	private static final String DELETE = "DELETE FROM BOARD WHERE BID=?";

	public ArrayList<BoardDTO> selectAll(BoardDTO boardDTO){
		ArrayList<BoardDTO> datas = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			if(boardDTO.getCondition().equals("ALL")) {
				pstmt = conn.prepareStatement(SELECT_ALL);
			}
			else if(boardDTO.getCondition().equals("TITLE")) {
				pstmt = conn.prepareStatement(SELECT_ALL_TITLE);
				pstmt.setString(1, "%"+boardDTO.getKeyword()+"%");
			}
			else if(boardDTO.getCondition().equals("WRITER")) {
				pstmt = conn.prepareStatement(SELECT_ALL_WRITER);
				pstmt.setString(1, boardDTO.getKeyword());
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO data = new BoardDTO();
				data.setBid(rs.getInt("BID"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setContent(rs.getString("CONTENT"));
				data.setWriterName(rs.getString("NAME"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return datas;
	}
	public BoardDTO selectOne(BoardDTO boardDTO){
		System.out.println("[로그] BoardDAO selectOne : "+boardDTO);
		BoardDTO data = null;
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SELECT_ONE);
			pstmt.setInt(1, boardDTO.getBid());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new BoardDTO();
				data.setBid(rs.getInt("BID"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setContent(rs.getString("CONTENT"));
				data.setWriterName(rs.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		System.out.println("[로그] BoardDAO selectOne : "+data);
		return data;
	}

	public boolean insert(BoardDTO boardDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, boardDTO.getTitle());
			pstmt.setString(2, boardDTO.getWriter());
			pstmt.setString(3, boardDTO.getContent());
			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	public boolean update(BoardDTO boardDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			if(boardDTO.getCondition().equals("TITLE")) {
				pstmt = conn.prepareStatement(UPDATE);
				pstmt.setString(1, boardDTO.getTitle());
				pstmt.setInt(2, boardDTO.getBid());
			}
			else if(boardDTO.getCondition().equals("DELMEM")) {
				pstmt = conn.prepareStatement(UPDATE_DELMEM);
				pstmt.setString(1, boardDTO.getWriter());
			}
			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
	public boolean delete(BoardDTO boardDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, boardDTO.getBid());
			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(conn, pstmt);
		return true;
	}
}
