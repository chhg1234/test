package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.MemberDTO;

public class MemberDAO {
	private static final String SELECT_ONE = "SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
	private static final String SELECT_ONE_CHECK = "SELECT * FROM MEMBER WHERE MID=?";

	private static final String INSERT = "INSERT INTO MEMBER VALUES(?,?,?,'USER')";
	private static final String UPDATE = "UPDATE MEMBER SET NAME=? WHERE MID=?";
	private static final String DELETE = "DELETE FROM MEMBER WHERE MID=?";

	private ArrayList<MemberDTO> selectAll(MemberDTO memberDTO){
		return null;
	}
	public MemberDTO selectOne(MemberDTO memberDTO){
		MemberDTO data = null;
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			if(memberDTO.getCondition().equals("LOGIN")) {
				pstmt = conn.prepareStatement(SELECT_ONE);
				pstmt.setString(1, memberDTO.getMid());
				pstmt.setString(2, memberDTO.getMpw());
			}
			else if(memberDTO.getCondition().equals("JOIN")) {
				pstmt = conn.prepareStatement(SELECT_ONE_CHECK);
				pstmt.setString(1, memberDTO.getMid());
			}
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberDTO();
				data.setMid(rs.getString("MID"));
				data.setMpw(rs.getString("MPW"));
				data.setName(rs.getString("NAME"));
				data.setMrole(rs.getString("MROLE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(conn, pstmt);
		return data;
	}

	public boolean insert(MemberDTO memberDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, memberDTO.getMid());
			pstmt.setString(2, memberDTO.getMpw());
			pstmt.setString(3, memberDTO.getName());
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
	public boolean update(MemberDTO memberDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getMid());
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
	public boolean delete(MemberDTO memberDTO){
		Connection conn = JDBCUtil.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, memberDTO.getMid());
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
