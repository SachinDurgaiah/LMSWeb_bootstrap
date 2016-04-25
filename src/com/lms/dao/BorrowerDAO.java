package com.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.BookLoans;
import com.lms.entity.Borrower;
import com.lms.entity.LibraryBranch;
import com.lms.entity.Publisher;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class BorrowerDAO extends BaseDAO{

	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAllBorrower(int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		System.out.println("In the borrower dao");
		return (List<Borrower>) readAll("select * from tbl_borrower", null);
	}
	
	public List<Borrower> readAllBorrower() throws ClassNotFoundException, SQLException{
		
		System.out.println("In the borrower dao");
		return (List<Borrower>) readAll("select * from tbl_borrower", null);
	}
	
@SuppressWarnings("unchecked")
public List<Borrower> readAllBorrower(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
	setPageNo(pageNo);
	String name = "%"+searchString+"%";
		System.out.println("In the borrower1 dao");
		return (List<Borrower>) readAll("select * from tbl_borrower where name like ? or address like ?", new Object[] {name,name});
	}
	
public List<Borrower> readAllBorrowerByName(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
	setPageNo(pageNo);
	String name = "%"+searchString+"%";
		System.out.println("In the borrower1 dao");
		return (List<Borrower>) readAll("select * from tbl_borrower where name like ? ", new Object[] {name});
	}

public List<Borrower> readAllBorrowerByAddress(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
	setPageNo(pageNo);
	String name = "%"+searchString+"%";
		System.out.println("In the borrower1 dao");
		return (List<Borrower>) readAll("select * from tbl_borrower where address like ? ", new Object[] {name});
	}

	public Integer getCount() throws ClassNotFoundException, SQLException{
		return getCount("select count(*) from tbl_borrower",null);
	}
	
	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException{
		save("insert into tbl_borrower (name, address, phone) values (?, ?, ?)", new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}
	

	public int CheckCard(Integer cardNo) throws ClassNotFoundException, SQLException {
		System.out.println("In borrower Dao "+cardNo);
		return getCount("select count(*) from tbl_borrower where cardNo = ?",new Object[] {cardNo});
		
	}


	public void deleteBorrower(Integer cardNo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("delete from tbl_borrower where cardNo = ?", new Object[] {cardNo});
		
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("update tbl_borrower set name = ?, address=?, phone=? where cardNo = ?", new Object[] {borrower.getName(),borrower.getAddress(),borrower.getPhone(),borrower.getCardNo()});
		
	}



	

	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		System.out.println("In the extract borrower dao");
		List<Borrower> br = new ArrayList<Borrower>();
		while(rs.next()){
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
		
			br.add(b);
		}
		return br;
	}

	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}
	public Borrower readBorrowerByID(Integer cardNo) throws ClassNotFoundException, SQLException {
		
		System.out.println("In the card verification");
		List<Borrower> borr = (List<Borrower>) readAll("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
		if(borr!=null && borr.size() >0){
			return borr.get(0);
		}
		return null;
	}

}
