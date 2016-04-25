package com.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO {
	
	private Connection conn;
	private Integer pageNo;

	private Integer pageSize;
	public BaseDAO(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection(){
		return conn;
	}

	public void save(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
	}
	
	public void save12(String query, int i,int j ,int k) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
	
		
		pstmt.setInt(1, i);
		pstmt.setInt(2, j);
		pstmt.setInt(3, k);
	
		pstmt.executeUpdate();
	}
	
	public void save(String query, Integer i,int j) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
	
		pstmt.setInt(1, i);
		pstmt.setInt(2, j);
		pstmt.executeUpdate();
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void save1(String query, int i,int j) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
	
		pstmt.setInt(1, i);
		pstmt.setInt(2, j);
		pstmt.executeUpdate();
	}
	public Integer saveWithID(String query, Object[] vals) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		
		if(rs.next()){
			return rs.getInt(1);
		}else{
			return null;
		}
	}
	
	public Integer getCount(String query, Object[] objects) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		System.out.println("in base dao for card verification");
		
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}
	
	public Integer getCount12(String query, Object[] objects) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		int count = 1;
		PreparedStatement pstmt = conn.prepareStatement(query);
		if(objects!=null){
			for(Object o: objects){
				pstmt.setObject(count, o);
				count++;
			}
		}
		
		System.out.println("in base dao for card verification");
		
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}
	
	public int getC(String query, Object[] objects) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(objects!=null){
			for(Object o: objects){
				pstmt.setObject(count, o);
				count++;
			}
		}
		pstmt.executeQuery();
		ResultSet rs = pstmt.executeQuery();
		int b_id=0;
		while(rs.next()){
			b_id=rs.getInt("branchId");
			
		}
		return b_id;
	}
	
	public int getCount1(String query, Object[] objects) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		System.out.println("In baseDao");
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		//System.out.println("result set length "+rs.last());
		int result=0;
		while(rs.next()){
			result = rs.getInt("count(*)");
			System.out.println("result set count "+result);
		}
		if(result==1){
			
			return 1;
		}else{
			System.out.println("result set length ");
			return 0;
		}
	}
	
	public boolean getCt(String query, Object[] objects) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		System.out.println("In baseDao getCt");
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		//System.out.println("result set length "+rs.last());
		int result=0;
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	/*public ResultSet getC(String query, Object[] objects) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			return rs;
		}else{
			return null;
		}
	}*/
	
	public List<?> readAll(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		Connection conn = getConnection();
		if (pageNo != null && pageNo >0) {
			query+=" LIMIT "+(pageNo - 1)*10+", 10";
		}
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		
		return extractData(rs);
	}
	

	

	public abstract List<?> extractData(ResultSet rs) throws SQLException;
	
	public List<?> readFirstLevel(String query, Object[] vals) throws SQLException, ClassNotFoundException{
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		int count = 1;
		
		if(vals!=null){
			for(Object o: vals){
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		
		return extractDataFirstLevel(rs);
	}

	public abstract List<?> extractDataFirstLevel(ResultSet rs) throws SQLException;
}
