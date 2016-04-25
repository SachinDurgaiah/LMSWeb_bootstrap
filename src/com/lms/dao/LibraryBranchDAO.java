package com.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.entity.Author;
import com.lms.entity.Book;
import com.lms.entity.Borrower;
import com.lms.entity.LibraryBranch;
import com.lms.entity.Publisher;
import com.lms.service.ConnectionUtil;
 
public class LibraryBranchDAO extends BaseDAO {

	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void Update_library(LibraryBranch branch) throws ClassNotFoundException, SQLException{
		save("update tbl_library_branch set branchName= ?,branchAddress = ? where branchId = ?", new Object[] {branch.getBranchName(),branch.getBranchAddress(), branch.getBranchId()});
	}
	
	public Integer getCount() throws ClassNotFoundException, SQLException{
		return getCount("select count(*) from tbl_borrower",null);
	}
	
	@SuppressWarnings("unchecked")
	public List<LibraryBranch> readAllBranches(int pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return (List<LibraryBranch>) readAll("select * from tbl_library_branch", null);
	}
	
	public List<LibraryBranch> readAllBranches() throws ClassNotFoundException, SQLException{
		
		return (List<LibraryBranch>) readAll("select * from tbl_library_branch", null);
	}
	
	
	@Override
	public List<?> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> branch = new ArrayList<LibraryBranch>();
		
		while(rs.next()){
			LibraryBranch b = new LibraryBranch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			
			branch.add(b);
		}
		return branch;
	}
	
	
	
	
	
	@Override
	public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void addLirbaryBranch(LibraryBranch branch) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
			save("insert into tbl_library_branch (branchName,branchAddress) values (?,?)", new Object[] {branch.getBranchName(),branch.getBranchAddress()});
		
		
	}

	public void deleteLirbaryBranch(Integer branchId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("delete from tbl_library_branch where branchId = ?", new Object[] {branchId});
		
	}

	public void updateLirbaryBranch(LibraryBranch branch) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("update tbl_library_branch set branchName = ?,branchAddress=? where branchId = ?", new Object[] {branch.getBranchName(),branch.getBranchAddress(),branch.getBranchId()});
	}


	
	public LibraryBranch readAuthorsByID(Integer branchId) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("unchecked")
		List<LibraryBranch> branch = (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchId = ?", new Object[] {branchId});
		if(branch!=null && branch.size() >0){
			return branch.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<LibraryBranch> getAllBranch(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		searchString = "%"+searchString+"%";
			System.out.println("In the borrower1 dao");
			return (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchName like ? or branchAddress like ?", new Object[] {searchString,searchString});
		}
	
	public List<LibraryBranch> getAllBranchByName(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		searchString = "%"+searchString+"%";
			System.out.println("In the borrower1 dao");
			return (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchName like ?", new Object[] {searchString});
		}
	
	public List<LibraryBranch> getAllBranchByAddress(String searchString, Integer pageNo) throws ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		searchString = "%"+searchString+"%";
			System.out.println("In the borrower1 dao");
			return (List<LibraryBranch>) readAll("select * from tbl_library_branch where branchAddress like ?", new Object[] {searchString});
		}
	
	
	
}
